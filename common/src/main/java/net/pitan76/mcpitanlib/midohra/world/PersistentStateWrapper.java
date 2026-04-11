package net.pitan76.mcpitanlib.midohra.world;

public class PersistentStateWrapper {
    private final net.minecraft.world.PersistentState raw;

    protected PersistentStateWrapper(net.minecraft.world.PersistentState raw) {
        this.raw = raw;
    }

    public static PersistentStateWrapper of(net.minecraft.world.PersistentState persistentState) {
        return new PersistentStateWrapper(persistentState);
    }

    public net.minecraft.world.PersistentState getRaw() {
        return raw;
    }

    @Override
    public int hashCode() {
        return getRaw() != null ? getRaw().hashCode() : 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        PersistentStateWrapper that = (PersistentStateWrapper) obj;

        return getRaw() != null ? getRaw().equals(that.getRaw()) : that.getRaw() == null;
    }

    public void markDirty() {
        getRaw().markDirty();
    }

    public void setDirty(boolean dirty) {
        getRaw().setDirty(dirty);
    }

    public boolean isDirty() {
        return getRaw().isDirty();
    }
}
