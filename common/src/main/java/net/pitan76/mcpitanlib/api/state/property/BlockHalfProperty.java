package net.pitan76.mcpitanlib.api.state.property;

import net.minecraft.world.level.block.state.properties.Half;
import net.pitan76.mcpitanlib.api.util.block.properties.CompatBlockHalf;
import net.pitan76.mcpitanlib.midohra.block.BlockState;

import java.util.function.Predicate;

public class BlockHalfProperty extends EnumProperty<Half> {

    public BlockHalfProperty(String name, Class<Half> type) {
        super(name, type);
    }

    public BlockHalfProperty(String name, Class<Half> type, Predicate<Half> filter) {
        super(name, type, filter);
    }

    public BlockHalfProperty(net.minecraft.world.level.block.state.properties.EnumProperty<Half> property) {
        super(property);
    }

    public static BlockHalfProperty ofRaw(net.minecraft.world.level.block.state.properties.EnumProperty<Half> property) {
        return new BlockHalfProperty(property);
    }

    public BlockState with(BlockState state, CompatBlockHalf value) {
        return super.with(state, value.getBlockHalf());
    }

    public CompatBlockHalf getCompat(BlockState state) {
        return CompatBlockHalf.of(super.get(state));
    }
}
