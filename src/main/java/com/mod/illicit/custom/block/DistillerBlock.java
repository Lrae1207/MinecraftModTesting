package com.mod.illicit.custom.block;


import com.mod.illicit.general.YawDirectionalBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jspecify.annotations.Nullable;


public class DistillerBlock extends YawDirectionalBlock {
    public DistillerBlock(Properties properties) {
            super(properties);
    }

    @Override
    public InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (!level.isClientSide()) {
            //Minecraft.getInstance().setScreen(new DistillerScreen());
        }
        return InteractionResult.SUCCESS;
    }
}
