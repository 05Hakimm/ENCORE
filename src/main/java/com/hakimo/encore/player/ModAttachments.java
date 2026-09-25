package com.hakimo.encore.player;

import com.hakimo.encore.ENCORE;

import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;

public class ModAttachments {

    public static final AttachmentType<PlayerBackup> PORTAL_BACKUP = AttachmentRegistry.<PlayerBackup>builder()
            .persistent(PlayerBackup.CODEC)
            .copyOnDeath()
            .buildAndRegister(ENCORE.id("portal_backup"));

    public static void initialize() {
    }
}