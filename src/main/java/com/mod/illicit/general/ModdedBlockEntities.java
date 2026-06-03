package com.mod.illicit.general;

import com.mod.illicit.Illicit;
import com.mod.illicit.custom.blockEntity.DistillerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModdedBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Illicit.MODID);

    public static final RegistryObject<BlockEntityType<DistillerBlockEntity>> DISTILLER =
            BLOCK_ENTITIES.register("distiller", () -> new BlockEntityType<>(
                    DistillerBlockEntity::new,
                    Set.of(ModdedBlocks.DISTILLER.get())
                )
            );

    public ModdedBlockEntities(FMLJavaModLoadingContext context) {
        var modBusGroup = context.getModBusGroup();
        BLOCK_ENTITIES.register(modBusGroup);
    }
}
