package com.mod.illicit.custom.block;


import com.mod.illicit.custom.blockEntity.DistillerBlockEntity;
import com.mod.illicit.custom.menu.DistillerScreen;
import com.mod.illicit.general.ModdedBlockEntities;
import com.mod.illicit.general.YawDirectionalBlock;
import com.mojang.serialization.MapCodec;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.FurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jspecify.annotations.Nullable;


public class DistillerBlock extends BaseEntityBlock {
    public static final MapCodec<DistillerBlock> CODEC = simpleCodec(DistillerBlock::new);

    public DistillerBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }


    @Override
    public InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        BlockEntity blockEntity = level.getBlockEntity(pos);

        if(level.getBlockEntity(pos) instanceof DistillerBlockEntity distillerBlockEntity) {
            if (!level.isClientSide()) {
                ((ServerPlayer) player).openMenu(new SimpleMenuProvider(distillerBlockEntity, Component.literal("Distiller")), pos);
            }
        }
        return InteractionResult.SUCCESS;
    }



    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new DistillerBlockEntity(pos, state);
    }

    @Override
    public void destroy(LevelAccessor level, BlockPos pos, BlockState state) {
        super.destroy(level, pos, state);

        if(level.getBlockEntity(pos) instanceof DistillerBlockEntity distillerBlockEntity) {
            distillerBlockEntity.drops();
            //level.updateNeighbourForOutputSignal(pos, this);
        }

        super.destroy(level, pos, state);    }
}
