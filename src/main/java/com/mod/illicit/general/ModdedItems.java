package com.mod.illicit.general;

import com.mod.illicit.Illicit;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModdedItems {
    // essentially an item list
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Illicit.MODID);

    // every item added needs to have a registry object initialized here
    // every item must have its Id explicitly set with setId(ITEMS.key(NAME_OF_ITEM_HERE))
    public static final RegistryObject<Item> EMPTY_CAN = ITEMS.register("empty_can",
            () -> new Item(new Item.Properties()
                    .setId(ITEMS.key("empty_can"))
                    .stacksTo(16)
            )
    );

    public static final RegistryObject<Item> BEER = ITEMS.register("beer",
            () -> new Item(new Item.Properties()
                    .setId(ITEMS.key("beer")))
    ); // properties can be initialized here

    public static final RegistryObject<Item> CANNABIS_LEAF = ITEMS.register("cannabis_leaf",
            () -> new Item(new Item.Properties()
                    .setId(ITEMS.key("cannabis_leaf")))
    );

    public static final RegistryObject<Item> DRIED_CANNABIS_LEAF = ITEMS.register("dried_cannabis_leaf",
            () -> new Item(new Item.Properties()
                    .setId(ITEMS.key("dried_cannabis_leaf")))
    );

    public static final RegistryObject<Item> CANNABIS_SEEDS = ITEMS.register("cannabis_seeds",
            () -> new BlockItem(ModdedBlocks.CANNABIS.get(), new Item.Properties()
                    .setId(ITEMS.key("cannabis_seeds"))
            )
    );

    public static final RegistryObject<Item> GRAPE_SEEDS = ITEMS.register("grape_seeds",
            () -> new BlockItem(ModdedBlocks.GRAPE_VINE.get(), new Item.Properties()
                    .setId(ITEMS.key("grape_seeds"))
            )
    );

    public static final RegistryObject<Item> GRAPES = ITEMS.register("grapes",
            () -> new Item(new Item.Properties()
                    .setId(ITEMS.key("grapes")))
    );

    public static final RegistryObject<Item> HOPS = ITEMS.register("hops",
            () -> new BlockItem(ModdedBlocks.HOPS_VINE.get(), new Item.Properties()
                    .setId(ITEMS.key("hops"))
            )
    );

    public static final RegistryObject<Item> DISTILLER = ITEMS.register("distiller",
            () -> new BlockItem(ModdedBlocks.DISTILLER.get(),
                    new Item.Properties()
                            .setId(ITEMS.key("distiller"))
            )
    );

    public static final RegistryObject<Item> CELLAR = ITEMS.register("cellar",
            () -> new BlockItem(ModdedBlocks.CELLAR.get(),
                    new Item.Properties()
                            .setId(ITEMS.key("cellar"))
            )
    );

    public static final RegistryObject<Item> DRYING_RACK = ITEMS.register("drying_rack",
            () -> new BlockItem(ModdedBlocks.DRYING_RACK.get(), new Item.Properties()
                    .setId(ITEMS.key("drying_rack"))
            )
    );

    public static final RegistryObject<Item> BONG = ITEMS.register("bong",
            () -> new BlockItem(ModdedBlocks.BONG.get(), new Item.Properties()
                    .setId(ITEMS.key("bong"))
            )
    );

    public static final RegistryObject<Item> BOWL = ITEMS.register("bowl",
            () -> new Item(new Item.Properties()
                    .setId(ITEMS.key("bowl")))
    );

    public static final RegistryObject<Item> PACKED_BOWL = ITEMS.register("packed_bowl",
            () -> new Item(new Item.Properties()
                    .setId(ITEMS.key("packed_bowl")))
    );

    public ModdedItems(FMLJavaModLoadingContext context) {
        var modBusGroup = context.getModBusGroup();
        ITEMS.register(modBusGroup);
    }
}