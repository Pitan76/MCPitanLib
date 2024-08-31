package net.pitan76.mcpitanlib.api.datafixer;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ItemStackFixer {

    public static void nbt2componentIfItemEquals(String itemId, String nbtKey, String componentId) {

    }

    public static void nbt2componentIfItemMatches(Set<String> itemId, String nbtKey, String componentId) {

    }

    public static void nbt2component(String nbtKey, String componentId) {

    }

    @Deprecated
    public static Map<String, Map<String, String>> getNbt2componentMapIfItemEqualMap() {
        return new HashMap<>();
    }

    @Deprecated
    public static Map<Set<String>, Map<String, String>> getNbt2componentMapIfitemMatchesMap() {
        return new HashMap<>();
    }

    @Deprecated
    public static Map<String, String> getNbt2componentMap() {
        return new HashMap<>();
    }
}
