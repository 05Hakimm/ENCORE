package com.hakimo.encore.block;

import com.hakimo.encore.ENCORE;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class ModBlocks {

    public static final Block PORTAL_BLOCK = register(
            new PortalBlock(BlockBehaviour.Properties.of().sound(SoundType.GLASS).strength(-1.0f, 3600000.0f)), "portal_block", true
    );

    private static Block register(Block block, String path, boolean shouldRegisterItem) {
        ResourceLocation id = ENCORE.id(path);

        if (shouldRegisterItem) {
            BlockItem blockItem = new BlockItem(block, new Item.Properties());
            Registry.register(BuiltInRegistries.ITEM, id, blockItem);
        }

        return Registry.register(BuiltInRegistries.BLOCK, id, block);
    }

    // Méthode "vide" : l'appeler force le JVM à charger cette classe,
    // donc à exécuter le champ static PORTAL_BLOCK ci-dessus (= l'enregistrer).
    public static void initialize() {
    }
}