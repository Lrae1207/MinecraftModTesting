package com.mod.illicit.general;

import com.mod.illicit.Illicit;
import com.mod.illicit.custom.blockEntity.DistillerBlockEntity;
import com.mod.illicit.custom.menu.DistillerMenu;
import com.mod.illicit.custom.menu.DistillerScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;

public class ModdedMenus {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(Registries.MENU, Illicit.MODID);

    public static final RegistryObject<MenuType<DistillerMenu>> DISTILLER_MENU =
            MENUS.register("distiller", () -> IForgeMenuType.create(DistillerMenu::new));

    public ModdedMenus(FMLJavaModLoadingContext context) {
        var modBusGroup = context.getModBusGroup();
        MENUS.register(modBusGroup);
    }
}
