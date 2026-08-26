package net.pitan76.mcpitanlib.api.enchantment.effect;

import net.minecraft.util.Identifier;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * エンチャント効果のハンドラ置き場。
 */
public class EnchantmentEffects {

    public static final CompatIdentifier CUSTOM_TYPE_ID = CompatIdentifier.of("mcpitanlib", "custom");

    private static final Map<Identifier, EnchantmentEffectHandler> handlers = new ConcurrentHashMap<>();

    /**
     * このバージョンではエンチャントがコードから登録できるため、
     * ハンドラは {@code ExtendEnchantment} が直接呼ぶ。ここでは何もしない。
     */
    public static void init() {
    }

    public static void register(CompatIdentifier id, EnchantmentEffectHandler handler) {
        handlers.put(id.toMinecraft(), handler);
    }

    public static EnchantmentEffectHandler get(Identifier id) {
        return handlers.get(id);
    }

    public static EnchantmentEffectHandler get(CompatIdentifier id) {
        return get(id.toMinecraft());
    }
}
