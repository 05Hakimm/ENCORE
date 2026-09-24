package com.hakimo.encore.block;

import com.hakimo.encore.ENCORE;
import com.hakimo.encore.run.Difficulty;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class ModBlocks {

    public static final Block PORTAL_GREEN = registerPortal("portal_green", Difficulty.EASY);
    public static final Block PORTAL_BLUE = registerPortal("portal_blue", Difficulty.NORMAL);
    public static final Block PORTAL_PURPLE = registerPortal("portal_purple", Difficulty.HARD);
    public static final Block PORTAL_RED = registerPortal("portal_red", Difficulty.VERY_HARD);
    public static final Block PORTAL_BLACK = registerPortal("portal_black", Difficulty.ENDGAME);

    private static Block registerPortal(String path, Difficulty difficulty) {
        return register(
                new PortalBlock(
                        BlockBehaviour.Properties.of()
                                .sound(SoundType.GLASS)
                                .strength(-1.0f, 3600000.0f),
                        difficulty
                ),
                path,
                true
        );
    }

    private static Block register(Block block, String path, boolean shouldRegisterItem) {
        ResourceLocation id = ENCORE.id(path);
        if (shouldRegisterItem) {
            BlockItem blockItem = new BlockItem(block, new Item.Properties());
            Registry.register(BuiltInRegistries.ITEM, id, blockItem);
        }
        return Registry.register(BuiltInRegistries.BLOCK, id, block);
    }

    public static void initialize() {
    }
}