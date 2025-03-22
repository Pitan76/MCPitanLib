package net.pitan76.mcpitanlib.api.event.entity;

import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import org.jetbrains.annotations.Nullable;

public class InitDataTrackerArgs {
    public DataTracker.Builder builder;
    public DataTracker tracker;

    public InitDataTrackerArgs(DataTracker.Builder builder) {
        this.builder = builder;
    }

    public InitDataTrackerArgs(DataTracker tracker) {
        this.tracker = tracker;
    }

    public InitDataTrackerArgs(DataTracker.Builder builder, @Nullable DataTracker tracker) {
        this.builder = builder;
        this.tracker = tracker;
    }

    public DataTracker.Builder getBuilder() {
        return builder;
    }

    public <T> DataTracker.Builder add(TrackedData<T> data, T value) {
        return builder.add(data, value);
    }

    public <T> void set(TrackedData<T> data, T value) {
        tracker.set(data, value);
    }

    public <T> void addTracking(TrackedData<T> data, T value) {
        if (builder != null) {
            add(data, value);
            return;
        }

        if (tracker != null) {
            set(data, value);
            return;
        }
    }
}
