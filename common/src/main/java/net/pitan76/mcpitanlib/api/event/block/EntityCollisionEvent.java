package net.pitan76.mcpitanlib.api.event.block;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.event.BaseEvent;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.api.util.SoundEventUtil;
import net.pitan76.mcpitanlib.api.util.WorldUtil;

import java.util.Optional;

public class EntityCollisionEvent extends BaseEvent {

    public BlockState state;
    public World world;
    public BlockPos pos;
    public Entity entity;

    public EntityCollisionEvent(BlockState state, World world, BlockPos pos, Entity entity) {
        this.state = state;
        this.world = world;
        this.pos = pos;
        this.entity = entity;
    }

    public boolean isClient() {
        return WorldUtil.isClient(world);
    }

    public BlockPos getEntityPos() {
        return entity.getBlockPos();
    }

    public BlockPos getBlockPos() {
        return pos;
    }

    public BlockState getState() {
        return state;
    }

    public Entity getEntity() {
        return entity;
    }

    public World getWorld() {
        return world;
    }

    public void playSound(SoundEvent event, SoundCategory category, float volume, float pitch) {
        WorldUtil.playSound(world, null, entity.getBlockPos(), event, category, volume, pitch);
    }

    public void playSound(SoundEvent event, float volume, float pitch) {
        playSound(event, SoundCategory.BLOCKS, volume, pitch);
    }

    public void playSound(SoundEvent event) {
        playSound(event, 1f, 1f);
    }

    public void playSound(SoundEvent event, SoundCategory category) {
        playSound(event, category, 1f, 1f);
    }

    public void playSound(CompatIdentifier id, SoundCategory category, float volume, float pitch) {
        playSound(SoundEventUtil.getSoundEvent(id), category, volume, pitch);
    }

    public boolean hasPlayerEntity() {
        return entity instanceof PlayerEntity;
    }

    public Optional<PlayerEntity> getPlayerEntity() {
        if (!hasPlayerEntity()) return Optional.empty();
        return Optional.of((PlayerEntity) entity);
    }
}
