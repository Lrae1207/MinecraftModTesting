package com.mod.illicit.custom.menu;

import com.mod.illicit.custom.blockEntity.DistillerBlockEntity;
import com.mod.illicit.custom.itemSlot.DistillerBottleSlot;
import com.mod.illicit.custom.itemSlot.DistillerFuelSlot;
import com.mod.illicit.custom.itemSlot.DistillerInputSlot;
import com.mod.illicit.custom.itemSlot.DistillerOutputSlot;
import com.mod.illicit.general.ModdedBlocks;
import com.mod.illicit.general.ModdedMenus;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.SlotItemHandler;


public class DistillerMenu extends AbstractContainerMenu {

    private final DistillerBlockEntity blockEntity;
    private final Level level;

    public DistillerMenu(int containerId, Inventory playerInventory, BlockEntity blockEntity) {
        super(ModdedMenus.DISTILLER_MENU.get(), containerId);
        this.blockEntity = (DistillerBlockEntity)blockEntity;
        this.level = playerInventory.player.level();

        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);

        // add slots here
        this.addSlot(new DistillerFuelSlot(this.blockEntity.inventory, 0, 50, 55, playerInventory.player.level())); // fuel slot
        this.addSlot(new DistillerInputSlot(this.blockEntity.inventory, 1, 36, 21)); // input slot 1
        this.addSlot(new DistillerInputSlot(this.blockEntity.inventory, 2, 62, 21)); // input slot 2
        this.addSlot(new DistillerBottleSlot(this.blockEntity.inventory, 3, 99, 39)); // bottle input slot
        this.addSlot(new DistillerOutputSlot(this.blockEntity.inventory, 4, 134, 39)); // output slot
    }

    public DistillerMenu(int containerId, Inventory playerInventory, FriendlyByteBuf extra) {
        this(containerId, playerInventory, playerInventory.player.level().getBlockEntity(extra.readBlockPos()));
    }

    private void addPlayerInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    @Override
    public ItemStack quickMoveStack(Player player, int slotIndex) {
        return null;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()),
                player, ModdedBlocks.DISTILLER.get());
    }
}
