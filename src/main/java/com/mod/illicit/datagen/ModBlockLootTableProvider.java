package com.mod.illicit.datagen;

import com.mod.illicit.general.ModdedBlocks;
import com.mod.illicit.general.ModdedItems;
import com.mod.illicit.custom.block.CannabisBlock;
import com.mod.illicit.custom.block.GrapeVineBlock;
import com.mod.illicit.custom.block.HopsVineBlock;
import net.minecraft.advancements.criterion.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {

    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        dropSelf(ModdedBlocks.BONG.get());
        dropSelf(ModdedBlocks.DRYING_RACK.get());
        dropSelf(ModdedBlocks.DISTILLER.get());
        dropSelf(ModdedBlocks.CELLAR.get());

        this.add(ModdedBlocks.CANNABIS.get(),
                block -> createCropDrops(
                        ModdedBlocks.CANNABIS.get(),
                        ModdedItems.CANNABIS_SEEDS.get(),
                        ModdedItems.CANNABIS_LEAF.get(),
                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModdedBlocks.CANNABIS.get())
                            .setProperties(
                                    StatePropertiesPredicate.Builder.properties()
                                            .hasProperty(CannabisBlock.AGE, CannabisBlock.HARVEST_AGE)
                            )
                )
        );

        this.add(ModdedBlocks.HOPS_VINE.get(),
                block -> createCropDrops(
                        ModdedBlocks.HOPS_VINE.get(),
                        ModdedItems.HOPS.get(),
                        ModdedItems.HOPS.get(),
                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModdedBlocks.HOPS_VINE.get())
                                .setProperties(
                                        StatePropertiesPredicate.Builder.properties()
                                                .hasProperty(HopsVineBlock.AGE, HopsVineBlock.MAX_AGE)
                                )
                )
        );

        this.add(ModdedBlocks.GRAPE_VINE.get(),
                block -> createCropDrops(
                        ModdedBlocks.GRAPE_VINE.get(),
                        ModdedItems.GRAPE_SEEDS.get(),
                        ModdedItems.GRAPES.get(),
                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModdedBlocks.GRAPE_VINE.get())
                                .setProperties(
                                        StatePropertiesPredicate.Builder.properties()
                                                .hasProperty(GrapeVineBlock.AGE, GrapeVineBlock.MAX_AGE)
                                )
                )
        );
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModdedBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}