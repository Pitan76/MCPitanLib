package net.pitan76.mcpitanlib.guilib.api.screen;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.pitan76.mcpitanlib.api.block.v3.CompatBlockEntity;
import net.pitan76.mcpitanlib.api.text.TextComponent;
import net.pitan76.mcpitanlib.api.util.inventory.CompatPlayerInventory;
import net.pitan76.mcpitanlib.guilib.api.container.BlockEntityContainerGui;

public class BlockEntityContainerGuiScreen<T extends BlockEntityContainerGui<B>, B extends CompatBlockEntity> extends ContainerGuiScreen<T> {
    public BlockEntityContainerGuiScreen(T handler, Inventory inventory, Component title) {
        super(handler, inventory, title);
    }

    public BlockEntityContainerGuiScreen(T handler, CompatPlayerInventory inventory, TextComponent title) {
        this(handler, inventory.getRaw(), title.getText());
    }

    public B getBlockEntity() {
        return handler.getBlockEntity();
    }
}
