package com.mod.illicit.custom.block;

import com.mod.illicit.general.ModdedItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class CannabisBlock extends CropBlock {
    public static final int MAX_AGE = 4;
    public static final int HARVEST_AGE = 3;
    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, MAX_AGE);

    public CannabisBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return ModdedItems.CANNABIS_SEEDS.get();
    }

    @Override
    public IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return (state.is(Blocks.GRASS_BLOCK) || state.is(Blocks.DIRT) || state.is(Blocks.COARSE_DIRT)) && !(state.is(Blocks.FARMLAND));
    }

    @Override
    protected  void  createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }
}