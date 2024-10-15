package net.pitan76.mcpitanlib.api.event.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.context.LootWorldContext;
import net.pitan76.mcpitanlib.api.event.BaseEvent;

public class DroppedStacksArgs extends BaseEvent {
    public BlockState state;
    public LootWorldContext.Builder builder;

    public DroppedStacksArgs(BlockState state, LootWorldContext.Builder builder) {
        this.state = state;
        this.builder = builder;
    }

    public BlockState getState() {
        return state;
    }

    @Deprecated
    public LootWorldContext.Builder getBuilder() {
        return builder;
    }

    public BlockEntity getBlockEntity() {
        return builder.get(LootContextParameters.BLOCK_ENTITY);
    }
}
