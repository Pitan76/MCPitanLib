package net.pitan76.mcpitanlib.guilib.api.block.entity.v2;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.event.block.TileCreateEvent;
import net.pitan76.mcpitanlib.api.event.container.factory.DisplayNameArgs;
import net.pitan76.mcpitanlib.api.gui.SimpleScreenHandler;
import net.pitan76.mcpitanlib.api.gui.args.CreateMenuEvent;
import net.pitan76.mcpitanlib.api.gui.v3.ExtendedScreenHandlerFactory;
import net.pitan76.mcpitanlib.api.text.TextComponent;
import net.pitan76.mcpitanlib.midohra.block.entity.BlockEntityTypeWrapper;
import net.pitan76.mcpitanlib.midohra.network.CompatPacketByteBuf;

public abstract class ExtendedBlockEntityWithContainer extends net.pitan76.mcpitanlib.guilib.api.block.entity.ExtendedBlockEntityWithContainer implements ExtendedScreenHandlerFactory {
    public ExtendedBlockEntityWithContainer(BlockEntityType<?> type, TileCreateEvent e) {
        super(type, e);
    }

    public ExtendedBlockEntityWithContainer(BlockEntityTypeWrapper type, TileCreateEvent e) {
        super(type.get(), e);
    }

    @Override
    public void sync(Player player, FriendlyByteBuf buf) {
        sync(player, CompatPacketByteBuf.of(buf));
    }

    public abstract void sync(Player player, CompatPacketByteBuf buf);

    @Override
    public SimpleScreenHandler createMenu(CreateMenuEvent event) {
        return null;
    }

    @Override
    public TextComponent getDisplayText(DisplayNameArgs args) {
        if (getMidohraBlockState().isEmpty()) return TextComponent.of("unknown");
        return new TextComponent(getMidohraBlockState().getName());
    }
}
