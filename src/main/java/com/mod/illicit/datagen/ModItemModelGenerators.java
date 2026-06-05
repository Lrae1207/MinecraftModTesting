package com.mod.illicit.datagen;

import com.mod.illicit.general.ModdedItems;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ItemModelOutput;
import net.minecraft.client.data.models.model.ModelInstance;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.resources.Identifier;
import java.util.function.BiConsumer;

public class ModItemModelGenerators extends ItemModelGenerators {
    public ModItemModelGenerators(ItemModelOutput pItemModelOutput, BiConsumer<Identifier, ModelInstance> pModelOutput) {
        super(pItemModelOutput, pModelOutput);
    }

    @Override
    public void run() {
        generateFlatItem(ModdedItems.EMPTY_CAN.get(), ModelTemplates.FLAT_ITEM);
        generateFlatItem(ModdedItems.BEER.get(), ModelTemplates.FLAT_ITEM);
        generateFlatItem(ModdedItems.CANNABIS_LEAF.get(), ModelTemplates.FLAT_ITEM);
        generateFlatItem(ModdedItems.DRIED_CANNABIS_LEAF.get(), ModelTemplates.FLAT_ITEM);
        generateFlatItem(ModdedItems.CANNABIS_SEEDS.get(), ModelTemplates.FLAT_ITEM);
        generateFlatItem(ModdedItems.GRAPE_SEEDS.get(), ModelTemplates.FLAT_ITEM);
        generateFlatItem(ModdedItems.GRAPES.get(), ModelTemplates.FLAT_ITEM);
        generateFlatItem(ModdedItems.HOPS.get(), ModelTemplates.FLAT_ITEM);
        generateFlatItem(ModdedItems.BOWL.get(), ModelTemplates.FLAT_ITEM);
        generateFlatItem(ModdedItems.PACKED_BOWL.get(), ModelTemplates.FLAT_ITEM);
        generateFlatItem(ModdedItems.WINE_BOTTLE.get(), ModelTemplates.FLAT_ITEM);

        if (this.itemModelOutput instanceof ModModelProvider.ModItemInfoCollector collector)
            collector.generateDefaultBlockModels();
    }
}