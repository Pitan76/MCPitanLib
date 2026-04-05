package net.pitan76.mcpitanlib.api.state.property;

import net.minecraft.block.enums.BlockHalf;
import net.pitan76.mcpitanlib.api.util.block.properties.CompatBlockHalf;
import net.pitan76.mcpitanlib.midohra.block.BlockState;

import java.util.function.Predicate;

public class BlockHalfProperty extends EnumProperty<BlockHalf> {

    public BlockHalfProperty(String name, Class<BlockHalf> type) {
        super(name, type);
    }

    public BlockHalfProperty(String name, Class<BlockHalf> type, Predicate<BlockHalf> filter) {
        super(name, type, filter);
    }

    public BlockHalfProperty(net.minecraft.state.property.EnumProperty<BlockHalf> property) {
        super(property);
    }

    public static BlockHalfProperty ofRaw(net.minecraft.state.property.EnumProperty<BlockHalf> property) {
        return new BlockHalfProperty(property);
    }

    public BlockState with(BlockState state, CompatBlockHalf value) {
        return super.with(state, value.getBlockHalf());
    }

    public CompatBlockHalf getCompat(BlockState state) {
        return CompatBlockHalf.of(super.get(state));
    }
}
