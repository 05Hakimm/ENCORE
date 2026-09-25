package com.hakimo.encore.block;

import com.hakimo.encore.ENCORE;
import com.hakimo.encore.player.ModAttachments;
import com.hakimo.encore.player.PlayerBackupManager;
import com.hakimo.encore.run.Difficulty;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class PortalBlock extends Block implements EntityBlock {

    // Coordonnées temporaires de la "zone de run" placeholder, en attendant la vraie dimension.
    private static final double TEMP_RUN_X = 100_000;
    private static final double TEMP_RUN_Y = 250;
    private static final double TEMP_RUN_Z = 0;

    private final Difficulty difficulty;

    public PortalBlock(BlockBehaviour.Properties properties, Difficulty difficulty) {
        super(properties);
        this.difficulty = difficulty;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new PortalBlockEntity(pos, state);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (!level.isClientSide && player instanceof ServerPlayer serverPlayer) {
            if (serverPlayer.hasAttached(ModAttachments.PORTAL_BACKUP)) {
                PlayerBackupManager.restore(serverPlayer);
                ENCORE.LOGGER.info("{} est revenu de la zone de run.", player.getName().getString());
            } else {
                PlayerBackupManager.backupAndTeleport(serverPlayer, TEMP_RUN_X, TEMP_RUN_Y, TEMP_RUN_Z);
                ENCORE.LOGGER.info("{} est entré dans une run de difficulté {}.", player.getName().getString(), difficulty);
            }
        }
        return InteractionResult.SUCCESS;
    }
}