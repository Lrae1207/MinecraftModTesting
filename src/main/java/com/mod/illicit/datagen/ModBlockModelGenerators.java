package com.mod.illicit.datagen;

import com.mod.illicit.custom.block.GrapeVineBlock;
import com.mod.illicit.custom.block.HopsVineBlock;
import com.mod.illicit.general.ModdedBlocks;
import com.mod.illicit.general.ModdedItems;
import com.mod.illicit.custom.block.BongBlock;
import com.mod.illicit.custom.block.CannabisBlock;
import com.mod.illicit.general.ModdedBlocks;
import com.mod.illicit.general.YawDirectionalBlock;
import com.mojang.math.Quadrant;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelOutput;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.*;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.renderer.block.dispatch.Variant;
import net.minecraft.resources.Identifier;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class ModBlockModelGenerators extends BlockModelGenerators {
    public ModBlockModelGenerators(Consumer<BlockModelDefinitionGenerator> pBlockStateOutput, ItemModelOutput pItemModelOutput, BiConsumer<Identifier, ModelInstance> pModelOutput) {
        super(pBlockStateOutput, pItemModelOutput, pModelOutput);
    }

    private void createCropCrossBlock(Block block, IntegerProperty ageProperty) {
        this.blockStateOutput.accept(
                MultiVariantGenerator.dispatch(block).with(
                        PropertyDispatch.initial(ageProperty).generate(age -> {
                            Identifier modelId = ModelLocationUtils.getModelLocation(block, "_stage" + age);

                            TextureMapping textures = new TextureMapping().put(TextureSlot.CROSS, TextureMapping.getBlockTexture(block, "_stage" + age));

                            ModelTemplates.CROSS.create(modelId, textures, this.modelOutput);

                            Variant variant = new Variant(modelId);
                            return new MultiVariant(WeightedList.of(variant));
                        })
                )
        );
    }

    private void createBong () {
        Block block = ModdedBlocks.BONG.get();

        this.blockStateOutput.accept(
                MultiVariantGenerator.dispatch(block).with(
                        PropertyDispatch.initial(BongBlock.STAGE).generate(stage -> {
                            Identifier modelId = ModelLocationUtils.getModelLocation(block, "" + stage);
                            TextureMapping textures = new TextureMapping().put(TextureSlot.ALL, TextureMapping.getBlockTexture(block, "" + stage));
                            ModelTemplates.CUBE_ALL.create(modelId, textures, this.modelOutput);
                            Variant variant = new Variant(modelId);
                            return new MultiVariant(WeightedList.of(variant));
                        })
                )
        );

        this.itemModelOutput.accept(ModdedItems.BONG.get(), ItemModelUtils.plainModel(ModelLocationUtils.getModelLocation(block, "0")));
    }


    @Override
    public void run() {
        createTrivialCube(ModdedBlocks.DISTILLER.get());
        createTrivialCube(ModdedBlocks.CELLAR.get());
        createTrivialCube(ModdedBlocks.DRYING_RACK.get());
        createCropCrossBlock(ModdedBlocks.CANNABIS.get(), CannabisBlock.AGE);
        createCropCrossBlock(ModdedBlocks.HOPS_VINE.get(), HopsVineBlock.AGE);
        createCropCrossBlock(ModdedBlocks.GRAPE_VINE.get(), GrapeVineBlock.AGE);
        createBong();
    }
}