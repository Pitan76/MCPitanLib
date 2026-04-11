package net.pitan76.mcpitanlib.api.event.block;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.pitan76.mcpitanlib.api.event.BaseEvent;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.api.util.SoundEventUtil;
import net.pitan76.mcpitanlib.api.util.WorldUtil;

import java.util.Optional;

public class EntityCollisionEvent extends BaseEvent {

    public BlockState state;
    public Level world;
    public BlockPos pos;
    public Entity entity;
    public InsideBlockEffectApplier handler;
    public boolean bl = false;

    public EntityCollisionEvent(BlockState state, Level world, BlockPos pos, Entity entity) {
        this.state = state;
        this.world = world;
        this.pos = pos;
        this.entity = entity;
    }

    public EntityCollisionEvent(BlockState state, Level world, BlockPos pos, Entity entity, InsideBlockEffectApplier handler) {
        this.state = state;
        this.world = world;
        this.pos = pos;
        this.entity = entity;
        this.handler = handler;
    }

    public EntityCollisionEvent(BlockState state, Level world, BlockPos pos, Entity entity, InsideBlockEffectApplier handler, boolean bl) {
        this.state = state;
        this.world = world;
        this.pos = pos;
        this.entity = entity;
        this.handler = handler;
        this.bl = bl;
    }

    public boolean isClient() {
        return WorldUtil.isClient(world);
    }

    public BlockPos getEntityPos() {
        return entity.blockPosition();
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

    public Level getWorld() {
        return world;
    }

    public void playSound(SoundEvent event, SoundSource category, float volume, float pitch) {
        WorldUtil.playSound(world, null, entity.blockPosition(), event, category, volume, pitch);
    }

    public void playSound(SoundEvent event, float volume, float pitch) {
        playSound(event, SoundSource.BLOCKS, volume, pitch);
    }

    public void playSound(SoundEvent event) {
        playSound(event, 1f, 1f);
    }

    public void playSound(SoundEvent event, SoundSource category) {
        playSound(event, category, 1f, 1f);
    }

    public void playSound(CompatIdentifier id, SoundSource category, float volume, float pitch) {
        playSound(SoundEventUtil.getSoundEvent(id), category, volume, pitch);
    }

    public boolean hasPlayerEntity() {
        return entity instanceof Player;
    }

    public Optional<Player> getPlayerEntity() {
        if (!hasPlayerEntity()) return Optional.empty();
        return Optional.of((Player) entity);
    }

    public BlockEntity getBlockEntity() {
        return WorldUtil.getBlockEntity(getWorld(), getBlockPos());
    }

    public InsideBlockEffectApplier getHandler() {
        return handler;
    }
}
