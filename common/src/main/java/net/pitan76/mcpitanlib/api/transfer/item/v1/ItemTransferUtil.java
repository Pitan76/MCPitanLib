package net.pitan76.mcpitanlib.api.transfer.item.v1;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import org.jetbrains.annotations.Nullable;

/**
 * アイテムの搬送を行うユーティリティ。
 * Fabricは {@code Storage<ItemVariant>}、NeoForgeは {@code ResourceHandler<ItemResource>} に橋渡しされる。
 */
public class ItemTransferUtil {

    /**
     * 指定した位置のインベントリへアイテムを挿入する。他MODの機械も対象になる。
     * @param world ワールド
     * @param pos 挿入先の位置
     * @param side 挿入先から見た面 (nullで面を指定しない)
     * @param stack 挿入するアイテム
     * @param simulate trueの場合は実際には挿入しない
     * @return 挿入された数
     */
    @ExpectPlatform
    public static int insertTo(World world, BlockPos pos, @Nullable Direction side, ItemStack stack, boolean simulate) {
        throw new AssertionError();
    }

    /**
     * 指定した位置のインベントリへアイテムを挿入する。
     * @return 挿入された数
     */
    public static int insertTo(World world, BlockPos pos, @Nullable Direction side, ItemStack stack) {
        return insertTo(world, pos, side, stack, false);
    }

    /**
     * 隣接するインベントリへアイテムを搬出する。
     * @param world ワールド
     * @param pos 搬出元の位置
     * @param direction 搬出する方向
     * @param stack 搬出するアイテム
     * @param simulate trueの場合は実際には搬出しない
     * @return 搬出された数
     */
    public static int moveToNeighbor(World world, BlockPos pos, Direction direction, ItemStack stack, boolean simulate) {
        return insertTo(world, pos.offset(direction), direction.getOpposite(), stack, simulate);
    }

    /**
     * 隣接するインベントリへアイテムを搬出する。
     * @return 搬出された数
     */
    public static int moveToNeighbor(World world, BlockPos pos, Direction direction, ItemStack stack) {
        return moveToNeighbor(world, pos, direction, stack, false);
    }

    /**
     * 隣接するインベントリへアイテムを全て搬出できるかどうか。
     */
    public static boolean canMoveToNeighbor(World world, BlockPos pos, Direction direction, ItemStack stack) {
        return moveToNeighbor(world, pos, direction, stack, true) >= stack.getCount();
    }

    /**
     * 指定した位置のインベントリを取得する。
     * @param side 面 (nullで面を指定しない)
     * @return インベントリ (存在しない場合はnull)
     */
    @ExpectPlatform
    @Nullable
    public static IItemHandler getItemHandler(World world, BlockPos pos, @Nullable Direction side) {
        throw new AssertionError();
    }

    /**
     * Containerを実装したBlockEntityを、見えるインベントリとして公開する。
     * FabricはContainerを自動で拾うため何もしない。
     */
    @ExpectPlatform
    public static void registerInventory(BlockEntityType<?> type) {
        throw new AssertionError();
    }

    @Nullable
    public static IItemHandler getItemHandler(World world, BlockPos pos) {
        return getItemHandler(world, pos, null);
    }

    @Nullable
    public static IItemHandler getItemHandler(BlockEntity blockEntity, @Nullable Direction side) {
        if (blockEntity == null || blockEntity.getWorld() == null) return null;
        return getItemHandler(blockEntity.getWorld(), blockEntity.getPos(), side);
    }
}
