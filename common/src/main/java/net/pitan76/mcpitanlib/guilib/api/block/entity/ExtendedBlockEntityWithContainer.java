package net.pitan76.mcpitanlib.guilib.api.block.entity;

import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.text.Text;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.event.block.TileCreateEvent;
import net.pitan76.mcpitanlib.api.event.container.factory.DisplayNameArgs;
import net.pitan76.mcpitanlib.api.event.container.factory.ExtraDataArgs;
import net.pitan76.mcpitanlib.api.event.tile.TileTickEvent;
import net.pitan76.mcpitanlib.api.gui.v2.ExtendedScreenHandlerFactory;
import net.pitan76.mcpitanlib.api.network.PacketByteUtil;
import net.pitan76.mcpitanlib.api.network.v2.ServerNetworking;
import net.pitan76.mcpitanlib.api.tile.ExtendBlockEntityTicker;
import net.pitan76.mcpitanlib.api.util.TextUtil;
import net.pitan76.mcpitanlib.api.util.WorldUtil;
import net.pitan76.mcpitanlib.guilib.api.container.ExtendedBlockEntityContainerGui;

import java.util.List;
import java.util.Objects;

import static net.pitan76.mcpitanlib.guilib.api.NetworkDefines.SYNC_GUI_WITH_TILE;

public abstract class ExtendedBlockEntityWithContainer extends BlockEntityWithContainer implements ExtendedScreenHandlerFactory, ExtendBlockEntityTicker<ExtendedBlockEntityWithContainer> {

    public ExtendedBlockEntityWithContainer(BlockEntityType<?> type, TileCreateEvent e) {
        super(type, e);
    }

    @Override
    public void tick(TileTickEvent<ExtendedBlockEntityWithContainer> e) {
        if (isClient()) return;

        List<Player> players = WorldUtil.getPlayers(Objects.requireNonNull(getWorld()));
        for (Player player : players) {
            if (player.hasNetworkHandler() && player.getCurrentScreenHandler() instanceof ExtendedBlockEntityContainerGui && ((ExtendedBlockEntityContainerGui<?>) player.getCurrentScreenHandler()).blockEntity == this ) {
                PacketByteBuf buf = PacketByteUtil.create();
                sync(player, buf);
                ServerNetworking.send(player, SYNC_GUI_WITH_TILE, buf);
            }
        }
    }

    @Override
    public Text getDisplayName(DisplayNameArgs args) {
        if (getCachedState() == null)
            return TextUtil.of("unknown");

        return getCachedState().getBlock().getName();
    }

    @Override
    public void writeExtraData(ExtraDataArgs args) {
        PacketByteUtil.writeBlockPos(args.buf, getPos());
        sync(args.getCompatPlayer(), args.buf);
    }

    public abstract void sync(Player player, PacketByteBuf buf);
}
