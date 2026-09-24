package com.hakimo.encore.client;

import com.hakimo.encore.block.PortalBlockEntity;
import com.hakimo.encore.run.Difficulty;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BeaconRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.util.FastColor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

public class PortalBlockEntityRenderer implements BlockEntityRenderer<PortalBlockEntity> {

    public PortalBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(PortalBlockEntity blockEntity, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        Level level = blockEntity.getLevel();
        if (level == null) return;

        Block block = blockEntity.getBlockState().getBlock();
        if (!(block instanceof com.hakimo.encore.block.PortalBlock portalBlock)) return;

        Difficulty difficulty = portalBlock.getDifficulty();
        int color = FastColor.ARGB32.color(difficulty.getRed(), difficulty.getGreen(), difficulty.getBlue());

        BeaconRenderer.renderBeaconBeam(
                poseStack, bufferSource, BeaconRenderer.BEAM_LOCATION,
                partialTick, 1.0f, level.getGameTime(),
                0, 256, color, 0.2f, 0.25f
        );
    }
}