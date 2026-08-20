package net.pitan76.mcpitanlib.api.registry.result;

public class RegistrySupplier<T> {
    public T value;
    public boolean isRegistered;

    public RegistrySupplier() {
        this.value = null;
        this.isRegistered = false;
    }

    public RegistrySupplier(T value) {
        this.value = value;
        this.isRegistered = true;
    }

    public void set(T value) {
        this.value = value;
        this.isRegistered = true;
    }

    public T get() {
        if (!isRegistered) throw new IllegalStateException("The registry is not registered yet");
        return value;
    }

    public T getOrNull() {
        return isRegistered ? value : null;
    }
}
