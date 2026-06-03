package com.mod.illicit.general;

import com.mod.illicit.Illicit;
import com.mod.illicit.custom.block.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModdedBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Illicit.MODID);

    public static final RegistryObject<Block> DRYING_RACK = BLOCKS.register("drying_rack",
            () -> new YawDirectionalBlock(
                    BlockBehaviour.Properties.of()
                            .setId(BLOCKS.key("drying_rack"))
                            .mapColor(MapColor.COLOR_BROWN)
                            .noOcclusion()
                            .sound(SoundType.WOOD)
            )
    );

    public static final RegistryObject<Block> BONG = BLOCKS.register("bong",
            () -> new BongBlock(
                    BlockBehaviour.Properties.of()
                            .setId(BLOCKS.key("bong"))
                            .mapColor(MapColor.TERRACOTTA_WHITE)
                            .noOcclusion()
                            .sound(SoundType.GLASS)
            )
    );

    public static final RegistryObject<Block> CANNABIS = BLOCKS.register("cannabis",
            () -> new CannabisBlock(
                    BlockBehaviour.Properties.of()
                            .setId(BLOCKS.key("cannabis"))
                            .mapColor(MapColor.COLOR_GREEN)
                            .noOcclusion()
                            .instabreak()
                            .noCollision()
                            .sound(SoundType.CROP)
            )
    );

    public static final RegistryObject<Block> DISTILLER = BLOCKS.register("distiller",
            () -> new DistillerBlock(
                    BlockBehaviour.Properties.of()
                            .setId(BLOCKS.key("distiller"))
                            .mapColor(MapColor.STONE)
                            .sound(SoundType.STONE)
            )
    );

    public static final RegistryObject<Block> CELLAR = BLOCKS.register("cellar",
            () -> new CellarBlock(
                    BlockBehaviour.Properties.of()
                            .setId(BLOCKS.key("cellar"))
                            .mapColor(MapColor.STONE)
                            .sound(SoundType.WOOD)
            )
    );

    public static final RegistryObject<Block> GRAPE_VINE = BLOCKS.register("grape_vine",
            () -> new GrapeVineBlock(
                    BlockBehaviour.Properties.of()
                            .setId(BLOCKS.key("grape_vine"))
                            .mapColor(MapColor.STONE)
                            .noOcclusion()
                            .instabreak()
                            .noCollision()
                            .sound(SoundType.HARD_CROP)
            )
    );

    public static final RegistryObject<Block> HOPS_VINE = BLOCKS.register("hops_vine",
            () -> new HopsVineBlock(
                    BlockBehaviour.Properties.of()
                            .setId(BLOCKS.key("hops_vine"))
                            .mapColor(MapColor.STONE)
                            .noOcclusion()
                            .instabreak()
                            .noCollision()
                            .randomTicks()
                            .sound(SoundType.CROP)
            )
    );

    public ModdedBlocks(FMLJavaModLoadingContext context) {
        var modBusGroup = context.getModBusGroup();
        BLOCKS.register(modBusGroup);
    }
}