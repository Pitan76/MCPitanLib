package net.pitan76.mcpitanlib.guilib.api.container.v2;

import net.minecraft.network.PacketByteBuf;
import net.pitan76.mcpitanlib.api.gui.args.CreateMenuEvent;
import net.pitan76.mcpitanlib.api.tile.CompatBlockEntity;
import net.pitan76.mcpitanlib.guilib.api.ISimpleScreenInfo;
import net.pitan76.mcpitanlib.midohra.network.IByteBuf;
import net.pitan76.mcpitanlib.midohra.screen.ScreenHandlerTypeWrapper;

public abstract class ExtendedBlockEntityContainerGui<T extends CompatBlockEntity> extends net.pitan76.mcpitanlib.guilib.api.container.ExtendedBlockEntityContainerGui<T> implements ISimpleScreenInfo {

    public ExtendedBlockEntityContainerGui(ScreenHandlerTypeWrapper type, CreateMenuEvent e, IByteBuf buf) {
        super(type.get(), e, buf.toCompat());
    }

    public ExtendedBlockEntityContainerGui(ScreenHandlerTypeWrapper type, CreateMenuEvent e, net.pitan76.mcpitanlib.midohra.network.PacketByteBuf buf) {
        super(type.get(), e, buf.toCompat());
    }

    protected ExtendedBlockEntityContainerGui(ScreenHandlerTypeWrapper type, CreateMenuEvent e) {
        super(type.get(), e);
    }

    public abstract void receiveSync(net.pitan76.mcpitanlib.midohra.network.PacketByteBuf buf);

    @Override
    public void receiveSync(PacketByteBuf buf) {
        receiveSync(net.pitan76.mcpitanlib.midohra.network.PacketByteBuf.of(buf));
    }
}
