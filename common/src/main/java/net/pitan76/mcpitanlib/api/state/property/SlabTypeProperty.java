package net.pitan76.mcpitanlib.api.state.property;

import net.minecraft.world.level.block.state.properties.SlabType;
import net.pitan76.mcpitanlib.api.util.block.properties.CompatSlabType;
import net.pitan76.mcpitanlib.midohra.block.BlockState;

import java.util.function.Predicate;

public class SlabTypeProperty extends EnumProperty<SlabType> {

    public SlabTypeProperty(String name, Class<SlabType> type) {
        super(name, type);
    }

    public SlabTypeProperty(String name, Class<SlabType> type, Predicate<SlabType> filter) {
        super(name, type, filter);
    }

    public SlabTypeProperty(net.minecraft.world.level.block.state.properties.EnumProperty<SlabType> property) {
        super(property);
    }

    public static SlabTypeProperty ofRaw(net.minecraft.world.level.block.state.properties.EnumProperty<SlabType> property) {
        return new SlabTypeProperty(property);
    }

    public BlockState with(BlockState state, CompatSlabType value) {
        return super.with(state, value.getSlabType());
    }

    public CompatSlabType getCompat(BlockState state) {
        return CompatSlabType.of(super.get(state));
    }
}
