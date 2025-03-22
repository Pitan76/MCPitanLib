package net.pitan76.mcpitanlib.api.event.entity;

import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;

public class InitDataTrackerArgs {
    public DataTracker tracker;

    public InitDataTrackerArgs(DataTracker tracker) {
        this.tracker = tracker;
    }

    public <T> void set(TrackedData<T> data, T value) {
        tracker.set(data, value);
    }

    public <T> void addTracking(TrackedData<T> data, T value) {
        if (tracker != null) {
            set(data, value);
            return;
        }
    }
}
