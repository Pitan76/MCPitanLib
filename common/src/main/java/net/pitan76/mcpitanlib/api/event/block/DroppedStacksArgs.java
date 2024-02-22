package net.pitan76.mcpitanlib.api.event.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;
import net.pitan76.mcpitanlib.api.event.BaseEvent;

public class DroppedStacksArgs extends BaseEvent {
    public BlockState state;
    public LootContext.Builder builder;

    public DroppedStacksArgs(BlockState state, LootContext.Builder builder) {
        this.state = state;
        this.builder = builder;
    }

    public BlockState getState() {
        return state;
    }

    @Deprecated
    public LootContext.Builder getBuilder() {
        return builder;
    }

    public BlockEntity getBlockEntity() {
        return builder.get(LootContextParameters.BLOCK_ENTITY);
    }
}
