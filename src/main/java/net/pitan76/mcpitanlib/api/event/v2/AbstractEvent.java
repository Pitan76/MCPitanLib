package net.pitan76.mcpitanlib.api.event.v2;

import java.util.List;

public abstract class AbstractEvent<T> {

    public abstract void register(T listener);

    public abstract void unregister(T listener);

    public abstract T getListener(int index);

    public abstract List<T> getListenersAsList();

    public int getListenerCount() {
        return getListenersAsList().size();
    }
}
