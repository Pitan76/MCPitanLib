package net.pitan76.mcpitanlib.api.event.block;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.LootParams;
import net.pitan76.mcpitanlib.api.event.BaseEvent;

public class DroppedStacksArgs extends BaseEvent {
    public BlockState state;
    public LootParams.Builder builder;

    public DroppedStacksArgs(BlockState state, LootParams.Builder builder) {
        this.state = state;
        this.builder = builder;
    }

    public BlockState getState() {
        return state;
    }

    @Deprecated
    public LootParams.Builder getBuilder() {
        return builder;
    }

    public BlockEntity getBlockEntity() {
        return builder.getParameter(LootContextParams.BLOCK_ENTITY);
    }
}
