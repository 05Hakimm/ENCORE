package com.hakimo.encore.player;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public record PlayerBackup(List<ItemStack> mainItems, List<ItemStack> armorItems, List<ItemStack> offhandItems,
                           int experienceLevel, float experienceProgress, int totalExperience,
                           double x, double y, double z, float yaw, float pitch) {

    public static final Codec<PlayerBackup> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ItemStack.CODEC.listOf().fieldOf("main_items").forGetter(PlayerBackup::mainItems),
            ItemStack.CODEC.listOf().fieldOf("armor_items").forGetter(PlayerBackup::armorItems),
            ItemStack.CODEC.listOf().fieldOf("offhand_items").forGetter(PlayerBackup::offhandItems),
            Codec.INT.fieldOf("experience_level").forGetter(PlayerBackup::experienceLevel),
            Codec.FLOAT.fieldOf("experience_progress").forGetter(PlayerBackup::experienceProgress),
            Codec.INT.fieldOf("total_experience").forGetter(PlayerBackup::totalExperience),
            Codec.DOUBLE.fieldOf("x").forGetter(PlayerBackup::x),
            Codec.DOUBLE.fieldOf("y").forGetter(PlayerBackup::y),
            Codec.DOUBLE.fieldOf("z").forGetter(PlayerBackup::z),
            Codec.FLOAT.fieldOf("yaw").forGetter(PlayerBackup::yaw),
            Codec.FLOAT.fieldOf("pitch").forGetter(PlayerBackup::pitch)
    ).apply(instance, PlayerBackup::new));
}