package net.pitan76.mcpitanlib.midohra.world;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.server.world.ServerWorld;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.api.util.WorldUtil;
import net.pitan76.mcpitanlib.midohra.block.entity.BlockEntityWrapper;
import net.pitan76.mcpitanlib.midohra.util.math.BlockPos;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class World extends WorldAccess {

    private final net.minecraft.world.World world;

    protected World(net.minecraft.world.World world) {
        super(null);
        this.world = world;
    }

    @Override
    public net.minecraft.world.World getWorld() {
        return world;
    }

    public static World of(net.minecraft.world.World world) {
        return new World(world);
    }

    public net.minecraft.world.World toMinecraft() {
        return getWorld();
    }

    public void addBlockEntity(BlockEntityWrapper blockEntity) {
        addBlockEntity(blockEntity.get());
    }

    public void addBlockEntity(BlockEntity blockEntity) {
        getWorld().addBlockEntity(blockEntity);
    }

    public void removeBlockEntity(BlockPos pos) {
        getWorld().removeBlockEntity(pos.toMinecraft());
    }

    public long getTime() {
        return WorldUtil.getTime(getWorld());
    }

    public long getTopY() {
        return WorldUtil.getTopY(getWorld());
    }

    public long getBottomY() {
        return WorldUtil.getBottomY(getWorld());
    }

    public long getDimensionHeight() {
        return WorldUtil.getDimensionHeight(getWorld());
    }

    public CompatIdentifier getId() {
        return WorldUtil.getCompatWorldId(getWorld());
    }

    public FluidState getRawFluidState(BlockPos pos) {
        return WorldUtil.getFluidState(getWorld(), pos.toMinecraft());
    }

    public Player getPlayerByUUID(UUID uuid) {
        return WorldUtil.getPlayer(getWorld(), uuid);
    }

    public List<Player> getPlayers() {
        return WorldUtil.getPlayers(getWorld());
    }

    public BlockPos getSpawnPos() {
        return BlockPos.of(WorldUtil.getSpawnPos(getWorld()));
    }

    public Optional<World> getWorld(CompatIdentifier id) {
        Optional<ServerWorld> optional = WorldUtil.getWorld(getWorld(), id);
        return optional.map(World::of);
    }
}
