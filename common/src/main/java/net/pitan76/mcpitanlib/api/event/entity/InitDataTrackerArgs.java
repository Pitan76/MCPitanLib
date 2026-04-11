package net.pitan76.mcpitanlib.api.event.entity;

import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.network.syncher.EntityDataAccessor;
import org.jetbrains.annotations.Nullable;

public class InitDataTrackerArgs {
    public SynchedEntityData.Builder builder;
    public SynchedEntityData tracker;

    public InitDataTrackerArgs(SynchedEntityData.Builder builder) {
        this.builder = builder;
    }

    public InitDataTrackerArgs(SynchedEntityData tracker) {
        this.tracker = tracker;
    }

    public InitDataTrackerArgs(SynchedEntityData.Builder builder, @Nullable SynchedEntityData tracker) {
        this.builder = builder;
        this.tracker = tracker;
    }

    public SynchedEntityData.Builder getBuilder() {
        return builder;
    }

    public <T> SynchedEntityData.Builder add(EntityDataAccessor<T> data, T value) {
        return builder.define(data, value);
    }

    public <T> void set(EntityDataAccessor<T> data, T value) {
        tracker.set(data, value);
    }

    public <T> void addTracking(EntityDataAccessor<T> data, T value) {
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
