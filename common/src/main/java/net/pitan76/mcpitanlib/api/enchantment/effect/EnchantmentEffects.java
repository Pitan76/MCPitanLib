package net.pitan76.mcpitanlib.api.enchantment.effect;

import net.minecraft.util.Identifier;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.core.registry.Registry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * エンチャント効果のハンドラ置き場。
 */
public class EnchantmentEffects {

    public static final CompatIdentifier CUSTOM_TYPE_ID = CompatIdentifier.of("mcpitanlib", "custom");

    private static final Map<Identifier, EnchantmentEffectHandler> handlers = new ConcurrentHashMap<>();
    private static boolean registered = false;

    /**
     * MCPitanLibの初期化時に呼ばれる。
     */
    public static void init() {
        if (registered) return;
        registered = true;

        Registry.registryEnchantmentEntityEffectType(CUSTOM_TYPE_ID.toMinecraft(), () -> CustomEnchantmentEffect.CODEC);
    }

    public static void register(CompatIdentifier id, EnchantmentEffectHandler handler) {
        init();
        handlers.put(id.toMinecraft(), handler);
    }

    public static EnchantmentEffectHandler get(Identifier id) {
        return handlers.get(id);
    }

    public static EnchantmentEffectHandler get(CompatIdentifier id) {
        return get(id.toMinecraft());
    }
}
