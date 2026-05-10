package net.pitan76.mcpitanlib.guilib.api.container.v2;

import net.minecraft.network.FriendlyByteBuf;
import net.pitan76.mcpitanlib.api.gui.args.CreateMenuEvent;
import net.pitan76.mcpitanlib.api.tile.CompatBlockEntity;
import net.pitan76.mcpitanlib.guilib.api.ISimpleScreenInfo;
import net.pitan76.mcpitanlib.midohra.network.CompatPacketByteBuf;
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

    public abstract void receiveSync(IByteBuf buf);

    @Override
    public void receiveSync(FriendlyByteBuf buf) {
        receiveSync((IByteBuf) new CompatPacketByteBuf(buf));
    }
}
