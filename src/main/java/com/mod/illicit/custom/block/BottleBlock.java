package com.mod.illicit.custom.block;

import com.mod.illicit.general.ModdedItems;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TransparentBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;

public class BottleBlock extends TransparentBlock {
    public static final int MIN_BOTTLES = 1;
    public int maxBottles = 4;
    public static final IntegerProperty BOTTLES = IntegerProperty.create("bottles", MIN_BOTTLES, 12);

    public BottleBlock(int maximum, Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(BOTTLES, 1));
        this.maxBottles = maximum;
    }

    public int getMaxBottles () {
        return maxBottles;
    }

    @Override
    protected boolean canBeReplaced(final BlockState state, final BlockPlaceContext context) {
        return !context.isSecondaryUseActive() && context.getItemInHand().is(this.asItem()) && state.getValue(BOTTLES) < maxBottles
                ? true
                : super.canBeReplaced(state, context);
    }

    @Override
    protected InteractionResult useItemOn(ItemStack itemStack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (level.isClientSide()) {
            return InteractionResult.SUCCESS;
        } else {
            int bottles = state.getValue(BOTTLES);

            if ((itemStack.is(this.asItem())) && (bottles < maxBottles)) {
                itemStack.consumeAndReturn(1, player);
                level.playSound(null, pos, SoundEvents.GLASS_PLACE, SoundSource.BLOCKS, 1, 0.75f + bottles / 8.0f);
                level.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1, 0.75f + bottles / 8.0f);
                level.setBlock(pos, state.getBlock().defaultBlockState().setValue(BOTTLES, bottles + 1), 2);
                return InteractionResult.SUCCESS;
            } else if (itemStack.isEmpty()) {
                level.playSound(null, pos, SoundEvents.GLASS_STEP, SoundSource.BLOCKS, 1, 0.75f + bottles / 8.0f);
                level.playSound(null, pos, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1, 0.75f + bottles / 8.0f);
                level.addFreshEntity(new ItemEntity(level, pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5, new ItemStack(this.asItem())));

                if (bottles > MIN_BOTTLES) {
                    level.setBlock(pos, state.getBlock().defaultBlockState().setValue(BOTTLES, bottles - 1), 2);
                } else {
                    level.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
                }

                return InteractionResult.SUCCESS;
            } else {
                return InteractionResult.TRY_WITH_EMPTY_HAND;
            }
        }
    }

    @Override
    protected void createBlockStateDefinition(final StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BOTTLES);
    }
}