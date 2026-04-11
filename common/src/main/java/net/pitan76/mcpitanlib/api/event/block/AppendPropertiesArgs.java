package net.pitan76.mcpitanlib.api.event.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.Property;
import net.pitan76.mcpitanlib.api.event.BaseEvent;
import net.pitan76.mcpitanlib.api.state.property.IProperty;
import net.pitan76.mcpitanlib.api.util.DirectionBoolPropertyUtil;

public class AppendPropertiesArgs extends BaseEvent {
    public StateDefinition.Builder<Block, BlockState> builder;

    public AppendPropertiesArgs(StateDefinition.Builder<Block, BlockState> builder) {
        this.builder = builder;
    }

    public StateDefinition.Builder<Block, BlockState> getBuilder() {
        return builder;
    }

    public void addProperty(Property<?>... properties) {
        builder.add(properties);
    }

    public void addProperty(IProperty<?>... properties) {
        for (IProperty<?> property : properties) {
            builder.add(property.getProperty());
        }
    }

    public void addAllDirectionBoolProperties() {
        DirectionBoolPropertyUtil.addProperties(this);
    }
}
