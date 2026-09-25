package com.hakimo.encore.player;

import com.hakimo.encore.ENCORE;

import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.RelativeMovement;
import net.minecraft.world.entity.player.Inventory;

import java.util.List;
import java.util.Set;

public class PlayerBackupManager {

    public static void registerEvents() {
        ServerPlayerEvents.AFTER_RESPAWN.register((oldPlayer, newPlayer, alive) -> {
            if (newPlayer.hasAttached(ModAttachments.PORTAL_BACKUP)) {
                restore(newPlayer);
                ENCORE.LOGGER.info("{} a été ramené automatiquement après sa mort en run.", newPlayer.getName().getString());
            }
        });
    }

    public static void backupAndTeleport(ServerPlayer player, double x, double y, double z) {
        Inventory inventory = player.getInventory();

        PlayerBackup backup = new PlayerBackup(
                List.copyOf(inventory.items),
                List.copyOf(inventory.armor),
                List.copyOf(inventory.offhand),
                player.experienceLevel,
                player.experienceProgress,
                player.totalExperience,
                player.getX(), player.getY(), player.getZ(),
                player.getYRot(), player.getXRot()
        );

        player.setAttached(ModAttachments.PORTAL_BACKUP, backup);

        inventory.clearContent();
        player.experienceLevel = 0;
        player.experienceProgress = 0.0f;
        player.totalExperience = 0;

        teleport(player, x, y, z, player.getYRot(), player.getXRot());
    }

    public static void restore(ServerPlayer player) {
        if (!player.hasAttached(ModAttachments.PORTAL_BACKUP)) {
            return;
        }

        PlayerBackup backup = player.getAttached(ModAttachments.PORTAL_BACKUP);
        Inventory inventory = player.getInventory();

        for (int i = 0; i < backup.mainItems().size(); i++) {
            inventory.items.set(i, backup.mainItems().get(i));
        }
        for (int i = 0; i < backup.armorItems().size(); i++) {
            inventory.armor.set(i, backup.armorItems().get(i));
        }
        for (int i = 0; i < backup.offhandItems().size(); i++) {
            inventory.offhand.set(i, backup.offhandItems().get(i));
        }

        player.experienceLevel = backup.experienceLevel();
        player.experienceProgress = backup.experienceProgress();
        player.totalExperience = backup.totalExperience();

        player.removeAttached(ModAttachments.PORTAL_BACKUP);

        teleport(player, backup.x(), backup.y(), backup.z(), backup.yaw(), backup.pitch());
    }

    private static void teleport(ServerPlayer player, double x, double y, double z, float yaw, float pitch) {
        player.teleportTo((ServerLevel) player.level(), x, y, z, Set.of(), yaw, pitch);
    }
}