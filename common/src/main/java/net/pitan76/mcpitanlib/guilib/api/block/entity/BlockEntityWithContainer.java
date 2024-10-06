package net.pitan76.mcpitanlib.guilib.api.block.entity;

import net.minecraft.block.entity.BlockEntityType;
import net.pitan76.mcpitanlib.api.event.block.TileCreateEvent;
import net.pitan76.mcpitanlib.api.event.nbt.ReadNbtArgs;
import net.pitan76.mcpitanlib.api.event.nbt.WriteNbtArgs;
import net.pitan76.mcpitanlib.api.gui.inventory.IInventory;
import net.pitan76.mcpitanlib.api.gui.inventory.sided.ChestStyleSidedInventory;
import net.pitan76.mcpitanlib.api.tile.CompatBlockEntity;
import net.pitan76.mcpitanlib.api.util.InventoryUtil;
import net.pitan76.mcpitanlib.api.util.ItemStackUtil;
import net.pitan76.mcpitanlib.api.util.collection.ItemStackList;

public abstract class BlockEntityWithContainer extends CompatBlockEntity implements IInventory, ChestStyleSidedInventory {

    private final ItemStackList inventory = ItemStackList.ofSize(getDefaultInvSize(), ItemStackUtil.empty());

    public BlockEntityWithContainer(BlockEntityType<?> type, TileCreateEvent e) {
        super(type, e);
    }

    public abstract int getDefaultInvSize();

    @Override
    public void writeNbt(WriteNbtArgs args) {
        super.writeNbt(args);
        InventoryUtil.writeNbt(args, inventory);
    }

    @Override
    public void readNbt(ReadNbtArgs args) {
        super.readNbt(args);
        InventoryUtil.readNbt(args, inventory);
    }

    @Override
    public ItemStackList getItems() {
        return inventory;
    }
}
