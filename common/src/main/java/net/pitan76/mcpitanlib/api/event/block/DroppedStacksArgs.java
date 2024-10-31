package net.pitan76.mcpitanlib.api.event.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.loot.context.LootContextParameters;
import net.pitan76.mcpitanlib.api.event.BaseEvent;

public class DroppedStacksArgs extends BaseEvent {
    public BlockState state;
    public LootContextParameterSet.Builder builder;

    public DroppedStacksArgs(BlockState state, LootContextParameterSet.Builder builder) {
        this.state = state;
        this.builder = builder;
    }

    public BlockState getState() {
        return state;
    }

    @Deprecated
    public LootContextParameterSet.Builder getBuilder() {
        return builder;
    }

    public BlockEntity getBlockEntity() {
        return builder.get(LootContextParameters.BLOCK_ENTITY);
    }
}
