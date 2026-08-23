package net.pitan76.mcpitanlib.api.transfer.item.v1;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.pitan76.mcpitanlib.midohra.block.entity.BlockEntityTypeWrapper;
import net.pitan76.mcpitanlib.midohra.block.entity.BlockEntityWrapper;
import net.pitan76.mcpitanlib.midohra.util.math.BlockPos;
import net.pitan76.mcpitanlib.midohra.util.math.Direction;
import net.pitan76.mcpitanlib.midohra.world.World;
import org.jetbrains.annotations.Nullable;

/**
 * インベントリのlookup。
 * <p>
 * Fabricの {@code ItemStorage.SIDED}、NeoForgeの {@code Capabilities.Item.BLOCK} を共通の形で扱う。
 */
public class ItemLookup {

    public static final ItemLookup ITEM = new ItemLookup();

    protected ItemLookup() {
    }

    /**
     * 指定した位置のインベントリを探す。他MODの機械も対象になる。
     * @param side 面 (nullで面を指定しない)
     * @return インベントリ (見つからない場合はnull)
     */
    @Nullable
    public IItemHandler find(World world, BlockPos pos, @Nullable Direction side) {
        return ItemTransferUtil.getItemHandler(world.getRaw(), pos.toMinecraft(), side == null ? null : side.toMinecraft());
    }

    @Nullable
    public IItemHandler find(World world, BlockPos pos) {
        return find(world, pos, null);
    }

    @Nullable
    public IItemHandler find(net.minecraft.world.level.Level world, net.minecraft.core.BlockPos pos, @Nullable net.minecraft.core.Direction side) {
        return ItemTransferUtil.getItemHandler(world, pos, side);
    }

    @Nullable
    public IItemHandler find(BlockEntityWrapper blockEntity, @Nullable Direction side) {
        return find(blockEntity.get(), side == null ? null : side.toMinecraft());
    }

    @Nullable
    public IItemHandler find(BlockEntity blockEntity, @Nullable net.minecraft.core.Direction side) {
        return ItemTransferUtil.getItemHandler(blockEntity, side);
    }

    /**
     * Containerを実装したBlockEntityを、他MODから見えるインベントリとして公開する。
     * <p>
     * FabricはContainerを自動で拾うため何もしない。NeoForgeはCapabilityの登録が要るのでここで行う。
     * WorldlyContainerなら面ごとのスロット制限もそのまま反映される。
     */
    public void registerForInventoryBlockEntity(BlockEntityType<?> type) {
        ItemTransferUtil.registerInventory(type);
    }

    public void registerForInventoryBlockEntity(BlockEntityTypeWrapper type) {
        registerForInventoryBlockEntity(type.get());
    }
}
