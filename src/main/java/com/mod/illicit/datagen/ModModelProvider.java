package com.mod.illicit.datagen;

import com.mod.illicit.general.ModdedBlocks;
import com.mod.illicit.general.ModdedItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.renderer.item.ClientItem;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class ModModelProvider extends ModelProvider {
    public ModModelProvider(PackOutput pOutput) {
        super(pOutput);
        this.blockStatePathProvider = pOutput.createPathProvider(PackOutput.Target.RESOURCE_PACK, "blockstates");
        this.itemInfoPathProvider = pOutput.createPathProvider(PackOutput.Target.RESOURCE_PACK, "items");
        this.modelPathProvider = pOutput.createPathProvider(PackOutput.Target.RESOURCE_PACK, "models");
    }

    private final PackOutput.PathProvider blockStatePathProvider;
    private final PackOutput.PathProvider itemInfoPathProvider;
    private final PackOutput.PathProvider modelPathProvider;

    @Override
    protected Stream<Block> getKnownBlocks() {
        return ModdedBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get);
    }

    @Override
    protected Stream<Item> getKnownItems() {
        return ModdedItems.ITEMS.getEntries().stream().map(RegistryObject::get);
    }

    @Override
    protected BlockModelGenerators getBlockModelGenerators(BlockStateGeneratorCollector blocks, ItemInfoCollector items, SimpleModelCollector models) {
        return new ModBlockModelGenerators(blocks, items, models);
    }

    @Override
    protected ItemModelGenerators getItemModelGenerators(ItemInfoCollector items, SimpleModelCollector models) {
        return new ModItemModelGenerators(items, models);
    }
    @Override
    public CompletableFuture<?> run(CachedOutput p_376268_) {
        ModItemInfoCollector modelprovider$iteminfocollector = new ModItemInfoCollector(this::getKnownItems);
        BlockStateGeneratorCollector modelprovider$blockstategeneratorcollector = new BlockStateGeneratorCollector(this::getKnownBlocks);
        SimpleModelCollector modelprovider$simplemodelcollector = new SimpleModelCollector();
        getBlockModelGenerators(modelprovider$blockstategeneratorcollector, modelprovider$iteminfocollector, modelprovider$simplemodelcollector).run();
        getItemModelGenerators(modelprovider$iteminfocollector, modelprovider$simplemodelcollector).run();
        modelprovider$blockstategeneratorcollector.validate();
        modelprovider$iteminfocollector.finalizeAndValidate();
        return CompletableFuture.allOf(
                modelprovider$blockstategeneratorcollector.save(p_376268_, this.blockStatePathProvider),
                modelprovider$simplemodelcollector.save(p_376268_, this.modelPathProvider),
                modelprovider$iteminfocollector.save(p_376268_, this.itemInfoPathProvider)
        );
    }

    public static class ModItemInfoCollector extends ItemInfoCollector {
        private final Map<Item, ClientItem> itemInfos = new HashMap<>();
        private final Map<Item, Item> copies = new HashMap<>();
        private final Supplier<Stream<Item>> known;

        public ModItemInfoCollector() {
            this(() -> BuiltInRegistries.ITEM.stream().filter(item -> "enderlite".equals(item.builtInRegistryHolder().key().identifier().getNamespace())));
        }

        public ModItemInfoCollector(Supplier<Stream<Item>> known) {
            this.known = known;
        }

        @Override
        public void accept(Item p_376450_, ItemModel.Unbaked p_378513_) {
            this.register(p_376450_, new ClientItem(p_378513_, ClientItem.Properties.DEFAULT));
        }

        private void register(Item pItem, ClientItem pClientItem) {
            ClientItem clientitem = this.itemInfos.put(pItem, pClientItem);
            if (clientitem != null) {
                throw new IllegalStateException("Duplicate item model definition for " + pItem);
            }
        }

        @Override
        public void copy(Item p_377438_, Item p_376965_) {
            this.copies.put(p_376965_, p_377438_);
        }

        public void generateDefaultBlockModels() {
            ModdedItems.ITEMS.getEntries().stream().map(RegistryObject::get).forEach(p_378629_ -> {
                if (!this.copies.containsKey(p_378629_)) {
                    if (p_378629_ instanceof BlockItem blockitem && !this.itemInfos.containsKey(blockitem)) {
                        Identifier resourcelocation = ModelLocationUtils.getModelLocation(blockitem.getBlock());
                        this.accept(blockitem, ItemModelUtils.plainModel(resourcelocation));
                    }
                }
            });
        }
        public void finalizeAndValidate() {
            this.copies.forEach((p_376289_, p_375718_) -> {
                ClientItem clientitem = this.itemInfos.get(p_375718_);
                if (clientitem == null) {
                    throw new IllegalStateException("Missing donor: " + p_375718_ + " -> " + p_376289_);
                } else {
                    this.register(p_376289_, clientitem);
                }
            });
            List<Identifier> list = known.get()
                    .map(item -> item.builtInRegistryHolder())
                    .filter(p_377225_ -> !this.itemInfos.containsKey(p_377225_.value()))
                    .map(p_378591_ -> p_378591_.key().identifier())
                    .toList();
            if (!list.isEmpty()) {
                throw new IllegalStateException("Missing item model definitions for: " + list);
            }
        }

        public CompletableFuture<?> save(CachedOutput pOutput, PackOutput.PathProvider pPathProvider) {
            return DataProvider.saveAll(
                    pOutput, ClientItem.CODEC, p_377091_ -> pPathProvider.json(p_377091_.builtInRegistryHolder().key().identifier()), this.itemInfos
            );
        }
    }
}