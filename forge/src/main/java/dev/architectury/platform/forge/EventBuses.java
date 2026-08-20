package dev.architectury.platform.forge;

import net.minecraftforge.eventbus.api.IEventBus;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

/**
 * 依存mod側がこのクラスを直接呼ぶため、脱Architectury API後も同じ名前で残している。
 * 中身はArchitecturyへ委譲せず自前で持つ。
 *
 * TODO: 依存mod側の移行が済んだら消す
 *
 * @deprecated Architectury API時代の互換用。新規に使わないこと。
 */
@Deprecated
public class EventBuses {
    private static final Map<String, IEventBus> BUSES = new ConcurrentHashMap<String, IEventBus>();
    private static final Map<String, List<Consumer<IEventBus>>> LISTENERS = new ConcurrentHashMap<String, List<Consumer<IEventBus>>>();

    public static void registerModEventBus(String modId, IEventBus bus) {
        BUSES.put(modId, bus);

        List<Consumer<IEventBus>> listeners = LISTENERS.remove(modId);
        if (listeners == null) return;

        for (Consumer<IEventBus> listener : listeners) {
            listener.accept(bus);
        }
    }

    public static void onRegistered(String modId, Consumer<IEventBus> busConsumer) {
        IEventBus bus = BUSES.get(modId);
        if (bus != null) {
            busConsumer.accept(bus);
            return;
        }

        List<Consumer<IEventBus>> listeners = LISTENERS.get(modId);
        if (listeners == null) {
            listeners = new CopyOnWriteArrayList<Consumer<IEventBus>>();
            LISTENERS.put(modId, listeners);
        }

        listeners.add(busConsumer);
    }

    public static Optional<IEventBus> getModEventBus(String modId) {
        return Optional.ofNullable(BUSES.get(modId));
    }
}
