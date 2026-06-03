package com.mod.illicit.general;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;

import javax.annotation.Nullable;

public class YawDirectionalBlock extends HorizontalDirectionalBlock {
    public static final MapCodec<YawDirectionalBlock> CODEC = simpleCodec(YawDirectionalBlock::new);

    public YawDirectionalBlock (Properties pProperties) {
        super(pProperties);
    }

    @Override
    protected  MapCodec<? extends HorizontalDirectionalBlock> codec() {
        return CODEC;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return this.defaultBlockState()
                .setValue(FACING, ctx.getHorizontalDirection());
    }

    @Override
    protected void createBlockStateDefinition (StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING);
    }
}
