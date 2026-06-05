package com.mod.illicit.general;

import com.mod.illicit.Illicit;
import com.mod.illicit.custom.menu.DistillerScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(
        modid = Illicit.MODID,
        value = Dist.CLIENT,
        bus = Mod.EventBusSubscriber.Bus.MOD
)
public class ModdedClient {

    public ModdedClient() {

    }

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        MenuScreens.register(
                ModdedMenus.DISTILLER_MENU.get(),
                DistillerScreen::new
        );
    }
}