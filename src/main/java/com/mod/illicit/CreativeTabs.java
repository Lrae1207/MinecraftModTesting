package com.mod.illicit;

import com.mod.illicit.general.ModdedItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class CreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Illicit.MODID);
    public static RegistryObject<CreativeModeTab> ILLICIT_TAB;

    // Must initialize after ModdedItems instance is initialized
    public CreativeTabs(FMLJavaModLoadingContext context, ModdedItems items) {
        var modBusGroup = context.getModBusGroup();

        ILLICIT_TAB = CREATIVE_MODE_TABS.register("illicit_substances", () -> CreativeModeTab.builder()
                .withTabsBefore(CreativeModeTabs.COMBAT)
                .title(Component.translatable("creativetab.illicit.illicit_substances"))
                .icon(() -> items.BEER.get().getDefaultInstance()) // we only need an example item for the tab's icon
                .displayItems((parameters, output) -> {
                    output.accept(items.BEER.get()); // for every item in the mod, accept it
                    output.accept(items.CANNABIS_LEAF.get());
                    output.accept(items.DRIED_CANNABIS_LEAF.get());
                    output.accept(items.CANNABIS_SEEDS.get());
                    output.accept(items.DRYING_RACK.get());
                    output.accept(items.BONG.get());
                    output.accept(items.BOWL.get());
                    output.accept(items.PACKED_BOWL.get());
                    output.accept(items.EMPTY_CAN.get());
                    output.accept(items.DISTILLER.get());
                    output.accept(items.GRAPES.get());
                    output.accept(items.GRAPE_SEEDS.get());
                    output.accept(items.HOPS.get());
                }).build());

        CREATIVE_MODE_TABS.register(modBusGroup);
    }
}
