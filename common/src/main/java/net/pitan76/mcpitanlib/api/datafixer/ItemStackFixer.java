package net.pitan76.mcpitanlib.api.datafixer;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ItemStackFixer {
    private static final Map<String, Map<String, String>> nbt2componentMapIfItemEqualMap = new HashMap<>();
    private static final Map<Set<String>, Map<String, String>> nbt2componentMapIfitemMatchesMap = new HashMap<>();
    private static final Map<String, String> nbt2componentMap = new HashMap<>();

    public static void nbt2componentIfItemEquals(String itemId, String nbtKey, String componentId) {
        Map<String, String> nbt2componentMap;
        if (nbt2componentMapIfItemEqualMap.containsKey(itemId)) {
            nbt2componentMap = nbt2componentMapIfItemEqualMap.get(itemId);
        } else {
            nbt2componentMap = new HashMap<>();
            nbt2componentMapIfItemEqualMap.put(itemId, nbt2componentMap);
        }

        nbt2componentMap.put(nbtKey, componentId);
    }

    public static void nbt2componentIfItemMatches(Set<String> itemId, String nbtKey, String componentId) {
        Map<String, String> nbt2componentMap;
        if (nbt2componentMapIfitemMatchesMap.containsKey(itemId)) {
            nbt2componentMap = nbt2componentMapIfitemMatchesMap.get(itemId);
        } else {
            nbt2componentMap = new HashMap<>();
            nbt2componentMapIfitemMatchesMap.put(itemId, nbt2componentMap);
        }

        nbt2componentMap.put(nbtKey, componentId);
    }

    public static void nbt2component(String nbtKey, String componentId) {
        if (nbt2componentMap.containsKey(nbtKey)) return;
        nbt2componentMap.put(nbtKey, componentId);
    }

    public static Map<String, Map<String, String>> getNbt2componentMapIfItemEqualMap() {
        return nbt2componentMapIfItemEqualMap;
    }

    public static Map<Set<String>, Map<String, String>> getNbt2componentMapIfitemMatchesMap() {
        return nbt2componentMapIfitemMatchesMap;
    }

    public static Map<String, String> getNbt2componentMap() {
        return nbt2componentMap;
    }
}
