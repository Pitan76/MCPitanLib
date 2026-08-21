package net.pitan76.mcpitanlib.api.state.property;

import net.minecraft.world.level.block.state.properties.ChestType;
import net.pitan76.mcpitanlib.api.util.block.properties.CompatChestType;
import net.pitan76.mcpitanlib.midohra.block.BlockState;

import java.util.function.Predicate;

public class ChestTypeProperty extends EnumProperty<ChestType> {

    public ChestTypeProperty(String name, Class<ChestType> type) {
        super(name, type);
    }

    public ChestTypeProperty(String name, Class<ChestType> type, Predicate<ChestType> filter) {
        super(name, type, filter);
    }

    public ChestTypeProperty(net.minecraft.world.level.block.state.properties.EnumProperty<ChestType> property) {
        super(property);
    }

    public static ChestTypeProperty ofRaw(net.minecraft.world.level.block.state.properties.EnumProperty<ChestType> property) {
        return new ChestTypeProperty(property);
    }

    public BlockState with(BlockState state, CompatChestType value) {
        return super.with(state, value.getChestType());
    }

    public CompatChestType getCompat(BlockState state) {
        return CompatChestType.of(super.get(state));
    }
}
