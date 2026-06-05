package com.mod.illicit.custom.itemSlot;

import net.minecraft.world.item.ItemStack;

import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class DistillerFuelSlot extends SlotItemHandler {
    private final Level level;

    public DistillerFuelSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition, Level level) {
        super(itemHandler, index, xPosition, yPosition);
        this.level = level;
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        System.out.println(level.fuelValues().burnDuration(stack));
        return level.fuelValues().burnDuration(stack) > 0;
    }

    @Override
    public int getMaxStackSize() {
        return 64;
    }
}
