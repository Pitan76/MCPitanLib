package net.pitan76.mcpitanlib.api.enchantment;

import net.pitan76.mcpitanlib.api.CommonModInitializer;
import net.pitan76.mcpitanlib.api.datapack.VirtualDatapack;
import net.pitan76.mcpitanlib.api.enchantment.effect.EnchantmentEffectHandler;
import net.pitan76.mcpitanlib.api.enchantment.effect.EnchantmentEffects;
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
 * <p>
 * 効果はバニラが用意した型しか書けないが、{@link #onPostAttack} などに渡したJavaの処理は
 * {@link EnchantmentEffects} 経由で呼ばれるので、凍結のような任意の処理も書ける。
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

    private final List<String> effectEntries = new ArrayList<>();

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

    /**
     * 攻撃を当てたときに走る処理。凍結や追加の状態異常などはここに書く。
     */
    public EnchantmentBuilder onPostAttack(EnchantmentEffectHandler handler) {
        return onPostAttack("attacker", "victim", handler);
    }

    /**
     * @param enchanted エンチャントが付いた装備を持っている側 (attacker / victim / damaging_entity)
     * @param affected 効果を受ける側
     */
    public EnchantmentBuilder onPostAttack(String enchanted, String affected, EnchantmentEffectHandler handler) {
        String effect = customEffectJson(registerHandler("post_attack_" + effectEntries.size(), handler));
        effectEntries.add("    " + quote("minecraft:post_attack") + ": [{"
                + quote("enchanted") + ": " + quote(enchanted) + ", "
                + quote("affected") + ": " + quote(affected) + ", "
                + quote("effect") + ": " + effect + "}]");

        return this;
    }

    /**
     * 装備している間、一定間隔で走る処理。
     */
    public EnchantmentBuilder onTick(EnchantmentEffectHandler handler) {
        String effect = customEffectJson(registerHandler("tick_" + effectEntries.size(), handler));
        effectEntries.add("    " + quote("minecraft:tick") + ": " + effect);

        return this;
    }

    /**
     * ブロックを壊したときに走る処理。
     */
    public EnchantmentBuilder onHitBlock(EnchantmentEffectHandler handler) {
        String effect = customEffectJson(registerHandler("hit_block_" + effectEntries.size(), handler));
        effectEntries.add("    " + quote("minecraft:hit_block") + ": " + effect);

        return this;
    }

    private CompatIdentifier registerHandler(String suffix, EnchantmentEffectHandler handler) {
        CompatIdentifier handlerId = CompatIdentifier.of(id.getNamespace(), id.getPath() + "/" + suffix);
        EnchantmentEffects.register(handlerId, handler);

        return handlerId;
    }

    private static String customEffectJson(CompatIdentifier handlerId) {
        return "{" + quote("type") + ": " + quote(EnchantmentEffects.CUSTOM_TYPE_ID.toString())
                + ", " + quote("id") + ": " + quote(handlerId.toString()) + "}";
    }

    private static String quote(String value) {
        return '"' + value + '"';
    }

    public String getTranslationKey() {
        if (translationKey != null) return translationKey;

        return "enchantment." + id.getNamespace() + "." + id.getPath();
    }

    public String toJson() {
        List<String> lines = new ArrayList<>();
        lines.add("  " + quote("description") + ": {" + quote("translate") + ": " + quote(getTranslationKey()) + "}");
        lines.add("  " + quote("supported_items") + ": " + toJsonValue(supportedItems));
        if (primaryItems != null)
            lines.add("  " + quote("primary_items") + ": " + toJsonValue(primaryItems));
        lines.add("  " + quote("weight") + ": " + weight);
        lines.add("  " + quote("max_level") + ": " + maxLevel);
        lines.add("  " + quote("min_cost") + ": {" + quote("base") + ": " + minCostBase + ", " + quote("per_level_above_first") + ": " + minCostPerLevel + "}");
        lines.add("  " + quote("max_cost") + ": {" + quote("base") + ": " + maxCostBase + ", " + quote("per_level_above_first") + ": " + maxCostPerLevel + "}");
        lines.add("  " + quote("anvil_cost") + ": " + anvilCost);
        if (exclusiveSet != null)
            lines.add("  " + quote("exclusive_set") + ": " + toJsonValue(exclusiveSet));

        if (rawEffects != null || !effectEntries.isEmpty()) {
            List<String> parts = new ArrayList<>(effectEntries);
            if (rawEffects != null) parts.add("    " + rawEffects);
            lines.add("  " + quote("effects") + ": {" + System.lineSeparator() + String.join("," + System.lineSeparator(), parts) + System.lineSeparator() + "  }");
        }

        List<String> usedSlots = slots.isEmpty() ? List.of("mainhand") : slots;
        StringBuilder slotJson = new StringBuilder("  " + quote("slots") + ": [");
        for (int i = 0; i < usedSlots.size(); i++) {
            if (i > 0) slotJson.append(", ");
            slotJson.append(quote(usedSlots.get(i)));
        }
        slotJson.append(']');
        lines.add(slotJson.toString());

        return "{" + System.lineSeparator() + String.join("," + System.lineSeparator(), lines) + System.lineSeparator() + "}";
    }

    /**
     * タグ (#付き) と単一IDはそのまま文字列、カンマ区切りは配列として書き出す。
     */
    private static String toJsonValue(String value) {
        if (!value.contains(",")) return quote(value);

        String[] split = value.split(",");
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < split.length; i++) {
            if (i > 0) sb.append(", ");
            sb.append(quote(split[i].trim()));
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
