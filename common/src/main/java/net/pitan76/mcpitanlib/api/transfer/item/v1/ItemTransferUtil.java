package net.pitan76.mcpitanlib.api.transfer.item.v1;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
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
    public static int insertTo(Level world, BlockPos pos, @Nullable Direction side, ItemStack stack, boolean simulate) {
        throw new AssertionError();
    }

    /**
     * 指定した位置のインベントリへアイテムを挿入する。
     * @return 挿入された数
     */
    public static int insertTo(Level world, BlockPos pos, @Nullable Direction side, ItemStack stack) {
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
    public static int moveToNeighbor(Level world, BlockPos pos, Direction direction, ItemStack stack, boolean simulate) {
        return insertTo(world, pos.relative(direction), direction.getOpposite(), stack, simulate);
    }

    /**
     * 隣接するインベントリへアイテムを搬出する。
     * @return 搬出された数
     */
    public static int moveToNeighbor(Level world, BlockPos pos, Direction direction, ItemStack stack) {
        return moveToNeighbor(world, pos, direction, stack, false);
    }

    /**
     * 隣接するインベントリへアイテムを全て搬出できるかどうか。
     */
    public static boolean canMoveToNeighbor(Level world, BlockPos pos, Direction direction, ItemStack stack) {
        return moveToNeighbor(world, pos, direction, stack, true) >= stack.getCount();
    }
}
