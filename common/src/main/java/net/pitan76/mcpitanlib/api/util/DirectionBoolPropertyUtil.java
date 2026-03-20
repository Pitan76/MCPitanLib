package net.pitan76.mcpitanlib.api.util;

import net.pitan76.mcpitanlib.api.event.block.AppendPropertiesArgs;
import net.pitan76.mcpitanlib.api.state.property.CompatProperties;
import net.pitan76.mcpitanlib.midohra.block.BlockState;
import net.pitan76.mcpitanlib.midohra.util.math.BlockPos;
import net.pitan76.mcpitanlib.midohra.util.math.Direction;
import net.pitan76.mcpitanlib.midohra.world.World;

public class DirectionBoolPropertyUtil {
    /**
     * 指定位置のブロック状態に対して、方向に対応する Boolean プロパティを設定してワールドに反映する。
     * 例: Direction.UP => CompatProperties.UP
     * @return プロパティが存在して設定に成功した場合は true、プロパティが存在しない場合やブロック状態が null の場合は false
     */
    public static boolean setProperty(World world, BlockPos pos, Direction dir, boolean value) {
        BlockState state = world.getBlockState(pos);

        if (dir == Direction.UP && state.contains(CompatProperties.UP)) {
            state = state.with(CompatProperties.UP, value);
        } else if (dir == Direction.DOWN && state.contains(CompatProperties.DOWN)) {
            state = state.with(CompatProperties.DOWN, value);
        } else if (dir == Direction.NORTH && state.contains(CompatProperties.NORTH)) {
            state = state.with(CompatProperties.NORTH, value);
        } else if (dir == Direction.SOUTH && state.contains(CompatProperties.SOUTH)) {
            state = state.with(CompatProperties.SOUTH, value);
        } else if (dir == Direction.WEST && state.contains(CompatProperties.WEST)) {
            state = state.with(CompatProperties.WEST, value);
        } else if (dir == Direction.EAST && state.contains(CompatProperties.EAST)) {
            state = state.with(CompatProperties.EAST, value);
        } else {
            return false;
        }

        world.setBlockState(pos, state);
        return true;
    }

    public static boolean hasAll(BlockState state) {
        return state.contains(CompatProperties.UP) && state.contains(CompatProperties.DOWN) && state.contains(CompatProperties.NORTH) &&
                state.contains(CompatProperties.SOUTH) && state.contains(CompatProperties.WEST) && state.contains(CompatProperties.EAST);
    }

    public static BlockState clearAll(BlockState state) {
        return state
                .with(CompatProperties.UP, false)
                .with(CompatProperties.DOWN, false)
                .with(CompatProperties.NORTH, false)
                .with(CompatProperties.EAST, false)
                .with(CompatProperties.SOUTH, false)
                .with(CompatProperties.WEST, false);
    }

    public static void addProperties(AppendPropertiesArgs args) {
        args.addProperty(CompatProperties.UP, CompatProperties.DOWN, CompatProperties.NORTH,
                CompatProperties.EAST, CompatProperties.SOUTH, CompatProperties.WEST);
    }
}
