package net.pitan76.mcpitanlib.api.util;

import com.google.common.collect.ImmutableList;
import net.minecraft.world.level.block.state.StateHolder;
import net.minecraft.world.level.block.state.StateDefinition;

public class StateManagerUtil {
    public static <T, S extends StateHolder<T, S>> S getDefaultState(StateDefinition<T, S> manager) {
        return manager.any();
    }

    public <T, S extends StateHolder<T, S>> ImmutableList<S> getStates(StateDefinition<T, S> manager) {
        return manager.getPossibleStates();
    }
}
