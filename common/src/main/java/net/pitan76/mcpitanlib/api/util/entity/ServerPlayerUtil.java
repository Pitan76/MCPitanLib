package net.pitan76.mcpitanlib.api.util.entity;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.sound.CompatSoundCategory;
import net.pitan76.mcpitanlib.api.sound.CompatSoundEvent;

import java.util.Collections;
import java.util.Optional;

public class ServerPlayerUtil {
    public static boolean teleport(ServerPlayer serverPlayerEntity, ServerLevel serverWorld, double x, double y, double z, float yaw, float pitch, boolean resetCamera) {
        return serverPlayerEntity.teleportTo(serverWorld, x, y, z, Collections.emptySet(), yaw, pitch, resetCamera);
    }

    public static boolean teleport(ServerPlayer serverPlayerEntity, ServerLevel serverWorld, double x, double y, double z, float yaw, float pitch) {
        return teleport(serverPlayerEntity, serverWorld, x, y, z, yaw, pitch, true);
    }

    public static boolean teleport(ServerPlayer serverPlayerEntity, ServerLevel serverWorld, double x, double y, double z) {
        return teleport(serverPlayerEntity, serverWorld, x, y, z, serverPlayerEntity.getYRot(), serverPlayerEntity.getXRot());
    }
    public static boolean teleport(ServerPlayer serverPlayerEntity, net.pitan76.mcpitanlib.midohra.world.ServerWorld serverWorld, double x, double y, double z, float yaw, float pitch, boolean resetCamera) {
        return teleport(serverPlayerEntity, serverWorld.getRaw(), x, y, z, yaw, pitch, resetCamera);
    }

    public static boolean teleport(ServerPlayer serverPlayerEntity, net.pitan76.mcpitanlib.midohra.world.ServerWorld serverWorld, double x, double y, double z, float yaw, float pitch) {
        return teleport(serverPlayerEntity, serverWorld, x, y, z, yaw, pitch, false);
    }

    public static boolean teleport(ServerPlayer serverPlayerEntity, net.pitan76.mcpitanlib.midohra.world.ServerWorld serverWorld, double x, double y, double z) {
        return teleport(serverPlayerEntity, serverWorld, x, y, z, serverPlayerEntity.getYRot(), serverPlayerEntity.getXRot());
    }

    public static boolean teleport(Player player, net.pitan76.mcpitanlib.midohra.world.ServerWorld serverWorld, double x, double y, double z, float yaw, float pitch, boolean resetCamera) {
        Optional<ServerPlayer> optionalServerPlayer = player.getServerPlayer();
        if (!optionalServerPlayer.isPresent()) return false;

        return teleport(optionalServerPlayer.get(), serverWorld, x, y, z, yaw, pitch, resetCamera);
    }

    public static boolean teleport(Player player, net.pitan76.mcpitanlib.midohra.world.ServerWorld serverWorld, double x, double y, double z, float yaw, float pitch) {
        return teleport(player, serverWorld, x, y, z, yaw, pitch, false);
    }

    public static boolean teleport(Player player, net.pitan76.mcpitanlib.midohra.world.ServerWorld serverWorld, double x, double y, double z) {
        return teleport(player, serverWorld, x, y, z, player.getYaw(), player.getPitch());
    }

    public static boolean teleport(ServerPlayer serverPlayerEntity, double x, double y, double z, boolean particleEffects) {
        return serverPlayerEntity.randomTeleport(x, y, z, particleEffects);
    }

    public static boolean teleport(ServerPlayer serverPlayerEntity, double x, double y, double z) {
        return teleport(serverPlayerEntity, x, y, z, false);
    }

    public static boolean teleport(Player player, double x, double y, double z, boolean particleEffects) {
        Optional<ServerPlayer> optionalServerPlayer = player.getServerPlayer();
        if (!optionalServerPlayer.isPresent()) return false;

        return teleport(optionalServerPlayer.get(), x, y, z, particleEffects);
    }

    public static boolean teleport(Player player, double x, double y, double z) {
        return teleport(player, x, y, z, false);
    }

    public static void playSound(ServerPlayer serverPlayerEntity, CompatSoundEvent soundEvent, CompatSoundCategory category, float volume, float pitch) {
        playSound(serverPlayerEntity, soundEvent, volume, pitch);
    }

    public static void playSound(ServerPlayer serverPlayerEntity, CompatSoundEvent soundEvent, float volume, float pitch) {
        serverPlayerEntity.playSound(soundEvent.get(), volume, pitch);
    }

    public static void playSound(ServerPlayer serverPlayerEntity, CompatSoundEvent soundEvent) {
        serverPlayerEntity.makeSound(soundEvent.get());
    }

    public static void playSound(Player player, CompatSoundEvent soundEvent, CompatSoundCategory category, float volume, float pitch) {
        Optional<ServerPlayer> optionalServerPlayer = player.getServerPlayer();
        if (!optionalServerPlayer.isPresent()) return;

        playSound(optionalServerPlayer.get(), soundEvent, category, volume, pitch);
    }

    public static void playSound(Player player, CompatSoundEvent soundEvent, float volume, float pitch) {
        Optional<ServerPlayer> optionalServerPlayer = player.getServerPlayer();
        if (!optionalServerPlayer.isPresent()) return;

        playSound(optionalServerPlayer.get(), soundEvent, volume, pitch);
    }

    public static void playSound(Player player, CompatSoundEvent soundEvent) {
        Optional<ServerPlayer> optionalServerPlayer = player.getServerPlayer();
        if (!optionalServerPlayer.isPresent()) return;

        playSound(optionalServerPlayer.get(), soundEvent);
    }
}
