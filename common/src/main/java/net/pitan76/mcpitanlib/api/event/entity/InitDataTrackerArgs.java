package net.pitan76.mcpitanlib.api.event.entity;

import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;

public class InitDataTrackerArgs {
    public DataTracker.Builder builder;

    public InitDataTrackerArgs(DataTracker.Builder builder) {
        this.builder = builder;
    }

    public DataTracker.Builder getBuilder() {
        return builder;
    }

    public <T> DataTracker.Builder add(TrackedData<T> data, T value) {
        return builder.add(data, value);
    }
}
