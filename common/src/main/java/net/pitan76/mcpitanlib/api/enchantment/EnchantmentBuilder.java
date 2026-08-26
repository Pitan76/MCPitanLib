package net.pitan76.mcpitanlib.api.enchantment;

import net.pitan76.mcpitanlib.api.CommonModInitializer;
import net.pitan76.mcpitanlib.api.datapack.VirtualDatapack;
import net.pitan76.mcpitanlib.api.registry.v2.CompatRegistryV2;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.midohra.enchantment.EnchantmentWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * エンチャントを追加する。
 * <p>
 * 1.21以降エンチャントはデータパックレジストリに移り、コードから直接登録できなくなった。
 * このbuilderは内容をJSONに書き出して {@link VirtualDatapack} に載せ、
 * データパックに置いたのと同じように読ませる。
 * レジストリに直接登録できるバージョンでは、そのまま登録する実装に差し替わる。
 */
public class EnchantmentBuilder {

    public CompatIdentifier id;

    /**
     * 表示名の翻訳キー。未指定なら {@code enchantment.<namespace>.<path>}。
     */
    public String translationKey;

    /**
     * 付与できるアイテム。タグは {@code #minecraft:enchantable/weapon} のように書く。
     */
    public String supportedItems = "#minecraft:enchantable/durability";

    /**
     * エンチャントテーブルで優先的に出るアイテム。未指定なら supportedItems と同じ扱い。
     */
    public String primaryItems;

    public int weight = 5;
    public int maxLevel = 1;
    public int anvilCost = 1;

    public int minCostBase = 1;
    public int minCostPerLevel = 10;
    public int maxCostBase = 21;
    public int maxCostPerLevel = 10;

    /**
     * 効果が働く装備スロット。{@code mainhand} / {@code offhand} / {@code armor} など。
     */
    public final List<String> slots = new ArrayList<>();

    /**
     * 同時に付けられないエンチャント。タグまたはIDのリスト。
     */
    public String exclusiveSet;

    /**
     * effectsの中身をそのまま書きたい場合に使う。JSONオブジェクトの中身 (波括弧なし) を渡す。
     */
    public String rawEffects;

    public EnchantmentBuilder(CompatIdentifier id) {
        this.id = id;
    }

    public static EnchantmentBuilder of(CompatIdentifier id) {
        return new EnchantmentBuilder(id);
    }

    public static EnchantmentBuilder of(String id) {
        return new EnchantmentBuilder(CompatIdentifier.of(id));
    }

    public EnchantmentBuilder translationKey(String translationKey) {
        this.translationKey = translationKey;
        return this;
    }

    public EnchantmentBuilder supportedItems(String supportedItems) {
        this.supportedItems = supportedItems;
        return this;
    }

    public EnchantmentBuilder primaryItems(String primaryItems) {
        this.primaryItems = primaryItems;
        return this;
    }

    /**
     * エンチャントテーブルでの出やすさ。バニラは1(希少)〜10(頻出)。
     */
    public EnchantmentBuilder weight(int weight) {
        this.weight = weight;
        return this;
    }

    public EnchantmentBuilder maxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
        return this;
    }

    /**
     * 金床で合成するときの追加コスト。
     */
    public EnchantmentBuilder anvilCost(int anvilCost) {
        this.anvilCost = anvilCost;
        return this;
    }

    /**
     * エンチャントテーブルで出現する経験値レベルの下限。
     */
    public EnchantmentBuilder minCost(int base, int perLevelAboveFirst) {
        this.minCostBase = base;
        this.minCostPerLevel = perLevelAboveFirst;
        return this;
    }

    /**
     * エンチャントテーブルで出現する経験値レベルの上限。
     */
    public EnchantmentBuilder maxCost(int base, int perLevelAboveFirst) {
        this.maxCostBase = base;
        this.maxCostPerLevel = perLevelAboveFirst;
        return this;
    }

    public EnchantmentBuilder slot(String slot) {
        slots.add(slot);
        return this;
    }

    public EnchantmentBuilder mainhand() {
        return slot("mainhand");
    }

    public EnchantmentBuilder armor() {
        return slot("armor");
    }

    public EnchantmentBuilder exclusiveSet(String exclusiveSet) {
        this.exclusiveSet = exclusiveSet;
        return this;
    }

    public EnchantmentBuilder rawEffects(String rawEffects) {
        this.rawEffects = rawEffects;
        return this;
    }

    public String getTranslationKey() {
        if (translationKey != null) return translationKey;

        return "enchantment." + id.getNamespace() + "." + id.getPath();
    }

    /**
     * エンチャント本にしか付けない場合はエンチャントテーブルに出す必要が無いので、
     * weightを0にして宝物扱いにする。
     */
    public EnchantmentBuilder treasureOnly() {
        this.weight = 1;

        return this;
    }

    public String toJson() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        sb.append("  \"description\": {\"translate\": \"").append(getTranslationKey()).append("\"},\n");
        sb.append("  \"supported_items\": ").append(toJsonValue(supportedItems)).append(",\n");
        if (primaryItems != null)
            sb.append("  \"primary_items\": ").append(toJsonValue(primaryItems)).append(",\n");
        sb.append("  \"weight\": ").append(weight).append(",\n");
        sb.append("  \"max_level\": ").append(maxLevel).append(",\n");
        sb.append("  \"min_cost\": {\"base\": ").append(minCostBase).append(", \"per_level_above_first\": ").append(minCostPerLevel).append("},\n");
        sb.append("  \"max_cost\": {\"base\": ").append(maxCostBase).append(", \"per_level_above_first\": ").append(maxCostPerLevel).append("},\n");
        sb.append("  \"anvil_cost\": ").append(anvilCost).append(",\n");
        if (exclusiveSet != null)
            sb.append("  \"exclusive_set\": ").append(toJsonValue(exclusiveSet)).append(",\n");
        if (rawEffects != null)
            sb.append("  \"effects\": {").append(rawEffects).append("},\n");

        sb.append("  \"slots\": [");
        List<String> used = slots.isEmpty() ? List.of("mainhand") : slots;
        for (int i = 0; i < used.size(); i++) {
            if (i > 0) sb.append(", ");
            sb.append('"').append(used.get(i)).append('"');
        }
        sb.append("]\n");
        sb.append("}\n");

        return sb.toString();
    }

    /**
     * タグ (#付き) と単一IDはそのまま文字列、カンマ区切りは配列として書き出す。
     */
    private static String toJsonValue(String value) {
        if (!value.contains(",")) return "\"" + value + "\"";

        String[] split = value.split(",");
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < split.length; i++) {
            if (i > 0) sb.append(", ");
            sb.append('"').append(split[i].trim()).append('"');
        }
        sb.append(']');

        return sb.toString();
    }

    public EnchantmentWrapper build(CompatRegistryV2 registry) {
        return registry.registerEnchantment(this);
    }

    public EnchantmentWrapper build(CommonModInitializer initializer) {
        return build(initializer.registry);
    }
}
