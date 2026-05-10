package net.pitan76.mcpitanlib.guilib.api.container;

import net.minecraft.world.inventory.MenuType;
import net.pitan76.mcpitanlib.api.gui.args.CreateMenuEvent;
import net.pitan76.mcpitanlib.api.tile.CompatBlockEntity;
import net.pitan76.mcpitanlib.guilib.api.ISimpleScreenInfo;
import net.pitan76.mcpitanlib.midohra.block.entity.TypedBlockEntityWrapper;
import net.pitan76.mcpitanlib.midohra.screen.ScreenHandlerTypeWrapper;

public abstract class BlockEntityContainerGui<T extends CompatBlockEntity> extends ContainerGui implements ISimpleScreenInfo {

    public T blockEntity;

    protected BlockEntityContainerGui(MenuType<?> type, CreateMenuEvent e) {
        super(type, e);
    }

    protected BlockEntityContainerGui(ScreenHandlerTypeWrapper type, CreateMenuEvent e) {
        super(type, e);
    }

    public T getBlockEntity() {
        return blockEntity;
    }

    public TypedBlockEntityWrapper<T> getBlockEntityWrapper() {
        return TypedBlockEntityWrapper.ofRaw(blockEntity);
    }
}
