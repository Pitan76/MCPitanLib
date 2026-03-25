package net.pitan76.mcpitanlib.api.event.v2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderedEvent<T> extends AbstractEvent<T> {

    public volatile Map<T, Integer> listeners = new HashMap<>();
    public volatile int maxPriority = 0;

    /**
     * Check if the event has any listeners
     * @return Whether the event has any listeners
     */
    public boolean isEmpty() {
        return listeners.isEmpty();
    }

    /**
     * Check if a listener is registered
     * @param listener The listener to check
     * @return Whether the listener is registered
     */
    public boolean isExists(T listener) {
        return listeners.containsKey(listener);
    }

    /**
     * Alias for isExists
     * @param listener The listener to check
     * @return Whether the listener is registered
     */
    public boolean has(T listener) {
        return isExists(listener);
    }

    /**
     * Check if a listener has a priority
     * @param listener The listener to check
     * @return Whether the listener has a priority
     */
    public boolean hasPriority(T listener) {
        if (!isExists(listener)) return false;
        return listeners.get(listener) != null && listeners.get(listener) != 0;
    }

    /**
     * Set the priority of a listener
     * @param listener The listener to set the priority of
     * @param priority The priority to set
     */
    public void setPriority(T listener, int priority) {
        if (!isExists(listener)) {
            listeners.put(listener, priority);
            return;
        }
        listeners.replace(listener, priority);
    }

    /**
     * Register a listener with a priority
     * @param listener The listener to register
     * @param priority The priority of the listener
     */
    public void register(T listener, int priority) {
        this.listeners.put(listener, priority);
        if (priority > maxPriority) {
            maxPriority = priority;
        }
    }

    /**
     * Register a listener (no priority)
     * @param listener The listener to register
     * @throws IllegalArgumentException If the listener is already registered
     */
    @Override
    public void register(T listener) {
        if (isExists(listener)) throw new IllegalArgumentException("Listener already exists");
        this.listeners.put(listener, 0);
    }

    /**
     * Register a listener (no priority)
     * @param listener The listener to register
     */
    public void registerNonSafe(T listener) {
        if (isExists(listener)) return;
        register(listener);
    }

    /**
     * Unregister a listener
     * @param listener The listener to unregister
     */
    @Override
    public void unregister(T listener) {
        if (!isExists(listener)) return;
        this.listeners.remove(listener);
    }

    /**
     * Get all listeners
     * @return A list of all listeners
     */
    @Override
    public List<T> getListenersAsList() {
        return listeners.keySet().stream().toList();
    }

    /**
     * get the priority of a listener
     * @param listener The listener to get the priority of
     * @return The priority of the listener
     */
    public int getPriority(T listener) {
        return listeners.get(listener);
    }

    /**
     * Get the highest priority
     * @return The highest priority
     */
    public int getMaxPriority() {
        return maxPriority;
    }

    /**
     * Get the next priority
     * @return The next priority
     */
    public int nextPriority() {
        return maxPriority + 1;
    }

    @Deprecated
    public T getListener(int index) {
        return getListenersAsList().get(index);
    }

    /**
     * Get all listeners with a specific priority
     * @param priority The priority to get listeners for
     * @return A list of listeners with the specified priority
     */
    public List<T> getListenersAsList(int priority) {
        List<T> listeners = new ArrayList<>();
        for (Map.Entry<T, Integer> entry : this.listeners.entrySet()) {
            if (entry.getValue() == priority) {
                listeners.add(entry.getKey());
            }
        }
        return listeners;
    }

    @Deprecated
    @SuppressWarnings("unchecked")
    public T[] getListeners(int priority) {
        return (T[]) getListenersAsList(priority).toArray();
    }

    public Map<T, Integer> getListeners() {
        return listeners;
    }
}
