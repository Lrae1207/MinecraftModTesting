package com.mod.illicit.custom.menu;


import com.mod.illicit.custom.blockEntity.DistillerBlockEntity;
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
import org.jspecify.annotations.Nullable;

public class DistillerMenu extends AbstractContainerMenu {
    public final DistillerBlockEntity blockEntity;
    private final Level level;

    public DistillerMenu(int containerId, Inventory inv, FriendlyByteBuf extraData) {
        this(containerId, inv, inv.player.level().getBlockEntity(extraData.readBlockPos()));
    }

    public DistillerMenu(int containerId, Inventory inv, @Nullable BlockEntity blockEntity) {
        super(ModdedMenus.DISTILLER_MENU.get(), containerId);
        this.blockEntity = (DistillerBlockEntity) blockEntity;
        this.level = inv.player.level();

        addPlayerInventory(inv);
        addPlayerHotbar(inv);

        //this.addSlot(new SlotItemHandler(this.blockEntity.inventory, 0, 0, 0)); // fuel
        //this.addSlot(new SlotItemHandler(this.blockEntity.inventory, 0, 80, 0)); // hops
        //this.addSlot(new SlotItemHandler(this.blockEntity.inventory, 0, 80, 80)); // output
    }

    @Override
    public ItemStack quickMoveStack(Player player, int slotIndex) {
        return null;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos())
            ,player, ModdedBlocks.DISTILLER.get()
        );
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
}
