package com.hakimo.encore.block;

import com.hakimo.encore.ENCORE;
import com.hakimo.encore.run.Difficulty;

import net.minecraft.core.BlockPos;
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
        if (!level.isClientSide) {
            ENCORE.LOGGER.info("Le joueur {} a activé un portail de difficulté {}.", player.getName().getString(), difficulty);
        }
        return InteractionResult.SUCCESS;
    }
}