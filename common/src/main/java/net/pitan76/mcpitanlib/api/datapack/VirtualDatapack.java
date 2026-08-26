package net.pitan76.mcpitanlib.api.datapack;

import net.minecraft.resources.Identifier;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * コードから組み立てたJSONを、データパックに置いたのと同じように読ませるための置き場。
 * <p>
 * 1.21以降、エンチャントのようにデータパックレジストリへ移されたものは
 * コードから直接登録できない。ここに登録したJSONは
 * {@code ResourceFinder} が結果を返すときに差し込まれる。
 * <p>
 * 実ファイルのデータパックが同じIDを持つ場合はそちらが優先される。
 */
public class VirtualDatapack {

    /**
     * ディレクトリ名 (enchantment 等) → リソースID → JSON文字列
     */
    private static final Map<String, Map<Identifier, String>> entries = new ConcurrentHashMap<>();

    /**
     * @param directoryName データパック内のディレクトリ名 (例: {@code enchantment})
     * @param id リソースID (拡張子・ディレクトリを含まない)
     * @param json 内容
     */
    public static void register(String directoryName, Identifier id, String json) {
        entries.computeIfAbsent(directoryName, k -> Collections.synchronizedMap(new LinkedHashMap<>())).put(id, json);
    }

    public static void register(String directoryName, CompatIdentifier id, String json) {
        register(directoryName, id.toMinecraft(), json);
    }

    public static boolean has(String directoryName) {
        Map<Identifier, String> map = entries.get(directoryName);

        return map != null && !map.isEmpty();
    }

    public static Map<Identifier, String> get(String directoryName) {
        Map<Identifier, String> map = entries.get(directoryName);
        if (map == null) return Collections.emptyMap();

        return map;
    }

    public static void clear() {
        entries.clear();
    }
}
