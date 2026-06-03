package com.mod.illicit.custom.block;

import com.mod.illicit.general.ModdedItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BongBlock extends Block {
    public static final IntegerProperty STAGE = IntegerProperty.create("stage", 0, 2);
    public static final BooleanProperty LIT = BooleanProperty.create("lit");

    public BongBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(STAGE, 0));
        this.registerDefaultState(this.stateDefinition.any().setValue(LIT, false));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return Block.box(5, 0, 5, 11, 16, 11); // Returns the custom bounding box
    }

    @Override
    protected InteractionResult useItemOn(final ItemStack itemStack, final BlockState state, final Level level, final BlockPos pos, final Player player, final InteractionHand hand, final BlockHitResult hitResult) {
        if (level.isClientSide()) {
            return InteractionResult.SUCCESS;
        } else {
            if (state.getValue(STAGE) == 0) {
                if (itemStack.is(Items.WATER_BUCKET)) {
                    player.awardStat(Stats.ITEM_USED.get(itemStack.getItem()));
                    level.playSound(null, pos, SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS);
                    level.playSound(null, pos, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS);
                    level.setBlock(pos, state.getBlock().defaultBlockState().setValue(STAGE, 1), 2);
                    itemStack.consumeAndReturn(1, player);
                    level.gameEvent(player, GameEvent.BLOCK_CHANGE, pos);
                    return InteractionResult.SUCCESS;
                } else {
                    return InteractionResult.TRY_WITH_EMPTY_HAND;
                }
            } else if (state.getValue(STAGE) == 1) {
                if (itemStack.is(ModdedItems.PACKED_BOWL.get())) {
                    player.awardStat(Stats.ITEM_USED.get(itemStack.getItem()));
                    level.playSound(null, pos, SoundEvents.GLASS_PLACE, SoundSource.BLOCKS);
                    itemStack.consumeAndReturn(1, player);
                    level.setBlock(pos, state.getBlock().defaultBlockState().setValue(STAGE, 2), 2);
                    level.gameEvent(player, GameEvent.BLOCK_CHANGE, pos);
                    return InteractionResult.SUCCESS;
                } else {
                    return InteractionResult.TRY_WITH_EMPTY_HAND;
                }
            } else if (state.getValue(STAGE) == 2) {
                if (itemStack.is(Items.FLINT_AND_STEEL)) {
                    player.awardStat(Stats.ITEM_USED.get(itemStack.getItem()));
                    level.playSound(null, pos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS);
                    itemStack.damageItem(1, level.getServer().overworld(), level.getServer().getPlayerList().getPlayer(player.getUUID()), true, null);
                    level.setBlock(pos, state.getBlock().defaultBlockState().setValue(LIT, true).setValue(STAGE, 2), 2);
                    level.gameEvent(player, GameEvent.BLOCK_CHANGE, pos);
                    return InteractionResult.SUCCESS;
                } else {
                    return InteractionResult.TRY_WITH_EMPTY_HAND;
                }
            } else {
                return InteractionResult.TRY_WITH_EMPTY_HAND;
            }
        }
    }

    @Override
    protected InteractionResult useWithoutItem (final BlockState state, final Level level, final BlockPos pos, final Player player, final BlockHitResult hitResult) {
        if (state.getValue(LIT)) {
            if (!level.isClientSide()) {
                ServerLevel serverLevel = level.getServer().overworld();
                serverLevel.sendParticles(ParticleTypes.CAMPFIRE_COSY_SMOKE, pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5, 4, 0.0, 0.0, 0.0, 0.0);
                level.playSound(null, pos, SoundEvents.FIRE_AMBIENT, SoundSource.BLOCKS);
                level.addFreshEntity(new ItemEntity(level, pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5, new ItemStack(ModdedItems.BOWL.get())));
                level.setBlock(pos, state.getBlock().defaultBlockState().setValue(LIT, false).setValue(STAGE, 0), 2);
                level.gameEvent(player, GameEvent.BLOCK_CHANGE, pos);
            }
            return InteractionResult.SUCCESS;
        } else {
            return InteractionResult.TRY_WITH_EMPTY_HAND;
        }
    }

    @Override
    protected void createBlockStateDefinition (StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(STAGE, LIT);
    }
}
