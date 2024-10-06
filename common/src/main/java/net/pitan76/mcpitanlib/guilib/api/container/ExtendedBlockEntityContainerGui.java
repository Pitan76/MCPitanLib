package net.pitan76.mcpitanlib.guilib.api.container;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.gui.args.CreateMenuEvent;
import net.pitan76.mcpitanlib.api.tile.CompatBlockEntity;
import net.pitan76.mcpitanlib.api.util.WorldUtil;
import net.pitan76.mcpitanlib.guilib.api.ISimpleScreenInfo;

public abstract class ExtendedBlockEntityContainerGui<T extends CompatBlockEntity> extends BlockEntityContainerGui implements ISimpleScreenInfo {

    public T blockEntity;

    public ExtendedBlockEntityContainerGui(ScreenHandlerType<?> type, CreateMenuEvent e, PacketByteBuf buf) {
        this(type, e);

        if (buf == null) return;

        Player player = e.getPlayer();
        World world = player.getWorld();
        BlockPos pos = buf.readBlockPos();

        BlockEntity blockEntity = WorldUtil.getBlockEntity(world, pos);
        if (blockEntity instanceof CompatBlockEntity)
            this.blockEntity = (T) blockEntity;

        receiveSync(buf);
    }

    protected ExtendedBlockEntityContainerGui(ScreenHandlerType<?> type, CreateMenuEvent e) {
        super(type, e);
    }

    public abstract void receiveSync(PacketByteBuf buf);
}
