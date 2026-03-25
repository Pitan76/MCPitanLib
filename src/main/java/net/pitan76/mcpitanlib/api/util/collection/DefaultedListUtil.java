package net.pitan76.mcpitanlib.api.util.collection;

import net.minecraft.core.NonNullList;

public class DefaultedListUtil {

    public static <E> NonNullList<E> of() {
        return NonNullList.create();
    }

    public static <E> NonNullList<E> ofSize(int size, E defaultElement) {
        return NonNullList.withSize(size, defaultElement);
    }

}
