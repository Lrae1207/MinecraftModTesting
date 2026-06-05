package com.mod.illicit.custom.itemSlot;

import com.mod.illicit.general.ModdedItems;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class DistillerOutputSlot extends SlotItemHandler {

    public DistillerOutputSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return stack.is(ModdedItems.BEER.get());
    }

    @Override
    public int getMaxStackSize() {
        return 64;
    }
}