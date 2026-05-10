package net.pitan76.mcpitanlib.guilib.api.container;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.gui.args.CreateMenuEvent;
import net.pitan76.mcpitanlib.api.tile.CompatBlockEntity;
import net.pitan76.mcpitanlib.api.util.WorldUtil;
import net.pitan76.mcpitanlib.guilib.api.ISimpleScreenInfo;
import net.pitan76.mcpitanlib.midohra.network.IByteBuf;
import net.pitan76.mcpitanlib.midohra.screen.ScreenHandlerTypeWrapper;

public abstract class ExtendedBlockEntityContainerGui<T extends CompatBlockEntity> extends BlockEntityContainerGui<T> implements ISimpleScreenInfo {

    public ExtendedBlockEntityContainerGui(MenuType<?> type, CreateMenuEvent e, FriendlyByteBuf buf) {
        this(type, e);

        if (buf == null) return;

        Player player = e.getPlayer();
        Level world = player.getWorld();
        BlockPos pos = buf.readBlockPos();

        BlockEntity blockEntity = WorldUtil.getBlockEntity(world, pos);
        if (blockEntity instanceof CompatBlockEntity)
            this.blockEntity = (T) blockEntity;

        receiveSync(buf);
    }

    protected ExtendedBlockEntityContainerGui(MenuType<?> type, CreateMenuEvent e) {
        super(type, e);
    }

    public ExtendedBlockEntityContainerGui(ScreenHandlerTypeWrapper type, CreateMenuEvent e, IByteBuf buf) {
        this(type.get(), e, buf.toCompat());
    }

    public ExtendedBlockEntityContainerGui(ScreenHandlerTypeWrapper type, CreateMenuEvent e, net.pitan76.mcpitanlib.midohra.network.PacketByteBuf buf) {
        this(type.get(), e, buf.toCompat());
    }

    protected ExtendedBlockEntityContainerGui(ScreenHandlerTypeWrapper type, CreateMenuEvent e) {
        this(type.get(), e);
    }

    public abstract void receiveSync(FriendlyByteBuf buf);
}
