package com.mod.illicit.custom.item;

import com.mod.illicit.general.ModdedItems;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class BeerItem extends Item {
    public BeerItem(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        // Run standard drinking behaviors (e.g., eating food, playing sounds)
        ItemStack resultStack = super.finishUsingItem(stack, level, livingEntity);

        if (!level.isClientSide() && livingEntity instanceof Player player) {
            ItemStack itemToGive = new ItemStack(ModdedItems.EMPTY_CAN.get()); // Change to your desired item
            boolean wasAdded = player.getInventory().add(itemToGive);
            player.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 200, 0));

            if (!wasAdded) {
                player.drop(itemToGive, false);
            }
        }

        return resultStack;
    }
}