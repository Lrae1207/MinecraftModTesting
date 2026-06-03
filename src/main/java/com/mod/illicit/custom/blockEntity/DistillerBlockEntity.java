package com.mod.illicit.custom.blockEntity;

import com.mod.illicit.general.ModdedBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.Nullable;

public class DistillerBlockEntity extends BlockEntity implements MenuProvider {
    public DistillerBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModdedBlockEntities.DISTILLER.get(), pPos, pBlockState);
    }


    @Override
    public Component getDisplayName() {
        return null;
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player player) {
        return null;
    }
}
