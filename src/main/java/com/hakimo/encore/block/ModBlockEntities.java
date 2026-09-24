package com.hakimo.encore.block;

import com.hakimo.encore.ENCORE;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class ModBlockEntities {

    public static final BlockEntityType<PortalBlockEntity> PORTAL = Registry.register(
            BuiltInRegistries.BLOCK_ENTITY_TYPE,
            ENCORE.id("portal"),
            BlockEntityType.Builder.of(PortalBlockEntity::new,
                    ModBlocks.PORTAL_GREEN,
                    ModBlocks.PORTAL_BLUE,
                    ModBlocks.PORTAL_PURPLE,
                    ModBlocks.PORTAL_RED,
                    ModBlocks.PORTAL_BLACK
            ).build(null)
    );

    public static void initialize() {
    }
}