package com.mod.illicit.custom.block;

import com.mod.illicit.general.YawDirectionalBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class CellarBlock extends YawDirectionalBlock {
    public static final int MAX_BOTTLE_COUNT = 6;
    public static final IntegerProperty BOTTLE_COUNT = IntegerProperty.create("bottle_count", 0, MAX_BOTTLE_COUNT);

    public CellarBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BOTTLE_COUNT);
        super.createBlockStateDefinition(builder);
    }
}
