package net.pitan76.mcpitanlib.midohra.world;

import net.minecraft.server.MinecraftServer;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.sound.CompatSoundCategory;
import net.pitan76.mcpitanlib.api.sound.CompatSoundEvent;
import net.pitan76.mcpitanlib.midohra.recipe.ServerRecipeManager;
import net.pitan76.mcpitanlib.midohra.server.MCServer;
import net.pitan76.mcpitanlib.midohra.server.PlayerManager;
import net.pitan76.mcpitanlib.midohra.util.math.BlockPos;
import net.pitan76.mcpitanlib.midohra.world.chunk.ServerChunkManager;

public class ServerWorld extends World {
    private final net.minecraft.server.level.ServerLevel world;

    protected ServerWorld(net.minecraft.server.level.ServerLevel world) {
        super(null);
        this.world = world;
    }

    public static ServerWorld of(net.minecraft.server.level.ServerLevel world) {
        return new ServerWorld(world);
    }

    @Override
    public net.minecraft.server.level.ServerLevel getRaw() {
        return world;
    }

    @Override
    public net.minecraft.server.level.ServerLevel toMinecraft() {
        return getRaw();
    }

    public void playSound(Player player, BlockPos pos, CompatSoundEvent sound, CompatSoundCategory category, float volume, float pitch, long seed) {
        getRaw().playSeededSound(player.getEntity(), pos.getX(), pos.getY(), pos.getZ(), sound.getEntry(), category.get(), volume, pitch, seed);
    }

    public void playSoundFromEntity(Player player, Player target, CompatSoundEvent sound, CompatSoundCategory category, float volume, float pitch, long seed) {
        getRaw().playSeededSound(player.getEntity(), target.getEntity(), sound.getEntry(), category.get(), volume, pitch, seed);
    }

    public MinecraftServer getServer() {
        return getRaw().getServer();
    }

    public MCServer getMCServer() {
        return MCServer.of(getServer());
    }

    public PlayerManager getPlayerManager() {
        return PlayerManager.of(this);
    }

    public ServerChunkManager getChunkManager() {
        return ServerChunkManager.of(this);
    }

    @Override
    public ServerRecipeManager getRecipeManager() {
        return ServerRecipeManager.of(this);
    }
}
