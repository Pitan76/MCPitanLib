package net.pitan76.mcpitanlib.api.util.collection;

import net.minecraft.util.collection.DefaultedList;

public class DefaultedListUtil {

    public static <E> DefaultedList<E> of() {
        return DefaultedList.of();
    }

    public static <E> DefaultedList<E> ofSize(int size, E defaultElement) {
        return DefaultedList.ofSize(size, defaultElement);
    }

}
