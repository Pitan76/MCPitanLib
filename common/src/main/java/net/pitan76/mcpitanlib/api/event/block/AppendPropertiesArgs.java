package net.pitan76.mcpitanlib.api.event.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Property;
import net.pitan76.mcpitanlib.api.event.BaseEvent;

public class AppendPropertiesArgs extends BaseEvent {
    public StateManager.Builder<Block, BlockState> builder;

    public AppendPropertiesArgs(StateManager.Builder<Block, BlockState> builder) {
        this.builder = builder;
    }

    public StateManager.Builder<Block, BlockState> getBuilder() {
        return builder;
    }

    public void addProperty(Property<?>... properties) {
        builder.add(properties);
    }
}
