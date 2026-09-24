package com.hakimo.encore.client;

import com.hakimo.encore.block.ModBlockEntities;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;

public class ENCOREClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		BlockEntityRenderers.register(ModBlockEntities.PORTAL, PortalBlockEntityRenderer::new);
	}
}