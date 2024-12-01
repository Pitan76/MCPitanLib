package net.pitan76.mcpitanlib.api.util.entity;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.sound.CompatSoundCategory;
import net.pitan76.mcpitanlib.api.sound.CompatSoundEvent;

import java.util.Collections;
import java.util.Optional;

public class ServerPlayerUtil {
    public static boolean teleport(ServerPlayerEntity serverPlayerEntity, ServerWorld serverWorld, double x, double y, double z, float yaw, float pitch, boolean resetCamera) {
        return serverPlayerEntity.teleport(serverWorld, x, y, z, Collections.emptySet(), yaw, pitch, resetCamera);
    }

    public static boolean teleport(ServerPlayerEntity serverPlayerEntity, ServerWorld serverWorld, double x, double y, double z, float yaw, float pitch) {
        return teleport(serverPlayerEntity, serverWorld, x, y, z, yaw, pitch, true);
    }

    public static boolean teleport(ServerPlayerEntity serverPlayerEntity, ServerWorld serverWorld, double x, double y, double z) {
        return teleport(serverPlayerEntity, serverWorld, x, y, z, serverPlayerEntity.getYaw(), serverPlayerEntity.getPitch());
    }
    public static boolean teleport(ServerPlayerEntity serverPlayerEntity, net.pitan76.mcpitanlib.midohra.world.ServerWorld serverWorld, double x, double y, double z, float yaw, float pitch, boolean resetCamera) {
        return teleport(serverPlayerEntity, serverWorld.getRaw(), x, y, z, yaw, pitch, resetCamera);
    }

    public static boolean teleport(ServerPlayerEntity serverPlayerEntity, net.pitan76.mcpitanlib.midohra.world.ServerWorld serverWorld, double x, double y, double z, float yaw, float pitch) {
        return teleport(serverPlayerEntity, serverWorld, x, y, z, yaw, pitch, false);
    }

    public static boolean teleport(ServerPlayerEntity serverPlayerEntity, net.pitan76.mcpitanlib.midohra.world.ServerWorld serverWorld, double x, double y, double z) {
        return teleport(serverPlayerEntity, serverWorld, x, y, z, serverPlayerEntity.getYaw(), serverPlayerEntity.getPitch());
    }

    public static boolean teleport(Player player, net.pitan76.mcpitanlib.midohra.world.ServerWorld serverWorld, double x, double y, double z, float yaw, float pitch, boolean resetCamera) {
        Optional<ServerPlayerEntity> optionalServerPlayer = player.getServerPlayer();
        if (!optionalServerPlayer.isPresent()) return false;

        return teleport(optionalServerPlayer.get(), serverWorld, x, y, z, yaw, pitch, resetCamera);
    }

    public static boolean teleport(Player player, net.pitan76.mcpitanlib.midohra.world.ServerWorld serverWorld, double x, double y, double z, float yaw, float pitch) {
        return teleport(player, serverWorld, x, y, z, yaw, pitch, false);
    }

    public static boolean teleport(Player player, net.pitan76.mcpitanlib.midohra.world.ServerWorld serverWorld, double x, double y, double z) {
        return teleport(player, serverWorld, x, y, z, player.getYaw(), player.getPitch());
    }

    public static boolean teleport(ServerPlayerEntity serverPlayerEntity, double x, double y, double z, boolean particleEffects) {
        return serverPlayerEntity.teleport(x, y, z, particleEffects);
    }

    public static boolean teleport(ServerPlayerEntity serverPlayerEntity, double x, double y, double z) {
        return teleport(serverPlayerEntity, x, y, z, false);
    }

    public static boolean teleport(Player player, double x, double y, double z, boolean particleEffects) {
        Optional<ServerPlayerEntity> optionalServerPlayer = player.getServerPlayer();
        if (!optionalServerPlayer.isPresent()) return false;

        return teleport(optionalServerPlayer.get(), x, y, z, particleEffects);
    }

    public static boolean teleport(Player player, double x, double y, double z) {
        return teleport(player, x, y, z, false);
    }

    public static void playSound(ServerPlayerEntity serverPlayerEntity, CompatSoundEvent soundEvent, CompatSoundCategory category, float volume, float pitch) {
        serverPlayerEntity.playSoundToPlayer(soundEvent.get(), category.get(), volume, pitch);
    }

    public static void playSound(ServerPlayerEntity serverPlayerEntity, CompatSoundEvent soundEvent, float volume, float pitch) {
        serverPlayerEntity.playSound(soundEvent.get(), volume, pitch);
    }

    public static void playSound(ServerPlayerEntity serverPlayerEntity, CompatSoundEvent soundEvent) {
        serverPlayerEntity.playSound(soundEvent.get());
    }

    public static void playSound(Player player, CompatSoundEvent soundEvent, CompatSoundCategory category, float volume, float pitch) {
        Optional<ServerPlayerEntity> optionalServerPlayer = player.getServerPlayer();
        if (!optionalServerPlayer.isPresent()) return;

        playSound(optionalServerPlayer.get(), soundEvent, category, volume, pitch);
    }

    public static void playSound(Player player, CompatSoundEvent soundEvent, float volume, float pitch) {
        Optional<ServerPlayerEntity> optionalServerPlayer = player.getServerPlayer();
        if (!optionalServerPlayer.isPresent()) return;

        playSound(optionalServerPlayer.get(), soundEvent, volume, pitch);
    }

    public static void playSound(Player player, CompatSoundEvent soundEvent) {
        Optional<ServerPlayerEntity> optionalServerPlayer = player.getServerPlayer();
        if (!optionalServerPlayer.isPresent()) return;

        playSound(optionalServerPlayer.get(), soundEvent);
    }
}
