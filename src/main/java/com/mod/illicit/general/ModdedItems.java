package com.mod.illicit.general;

import com.mod.illicit.Illicit;
import com.mod.illicit.custom.item.BeerItem;
import net.minecraft.core.component.DataComponents;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
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
                    .stacksTo(64)
            )
    );

    // must be CustomConsumable bc of empty can functionality
    public static final RegistryObject<Item> BEER = ITEMS.register("beer",
            () -> new BeerItem(new Item.Properties()
                    .setId(ITEMS.key("beer"))
                    .food(new FoodProperties.Builder()
                                    .alwaysEdible()
                                    .build(),
                            Consumables.defaultFood()
                                    .build()
                    )
                    .component(DataComponents.CONSUMABLE,  Consumable.builder()
                            .sound(SoundEvents.GENERIC_DRINK) // Sets custom chew sound
                            .hasConsumeParticles(false)
                            .build()
                    )
                    .stacksTo(64)
            )
    );

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
                    .setId(ITEMS.key("grapes"))
                    .food(new FoodProperties.Builder()
                                    .nutrition(3)
                                    .saturationModifier(2.0f)
                                    .alwaysEdible()
                                    .build(),
                            Consumables.defaultFood()
                                    .build()
                    )
                    .component(DataComponents.CONSUMABLE,  Consumable.builder()
                            .sound(SoundEvents.GENERIC_EAT) // Sets custom chew sound
                            .hasConsumeParticles(false)
                            .build()
                    )
            )
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

    public static final RegistryObject<Item> WINE_BOTTLE = ITEMS.register("wine_bottle",
            () -> new BlockItem(ModdedBlocks.WINE_BOTTLE.get(), new Item.Properties()
                    .setId(ITEMS.key("wine_bottle"))
            )
    );

    public ModdedItems(FMLJavaModLoadingContext context) {
        var modBusGroup = context.getModBusGroup();
        ITEMS.register(modBusGroup);
    }
}