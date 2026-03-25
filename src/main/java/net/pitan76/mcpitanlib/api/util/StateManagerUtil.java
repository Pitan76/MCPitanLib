package net.pitan76.mcpitanlib.api.util;

import com.google.common.collect.ImmutableList;
import net.minecraft.state.State;
import net.minecraft.state.StateManager;

public class StateManagerUtil {
    public static <T, S extends State<T, S>> S getDefaultState(StateManager<T, S> manager) {
        return manager.getDefaultState();
    }

    public <T, S extends State<T, S>> ImmutableList<S> getStates(StateManager<T, S> manager) {
        return manager.getStates();
    }
}
