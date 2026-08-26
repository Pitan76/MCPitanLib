package net.pitan76.mcpitanlib.api.enchantment.effect;

/**
 * エンチャントの効果としてJavaのコードを走らせるためのハンドラ。
 */
@FunctionalInterface
public interface EnchantmentEffectHandler {
    void apply(EnchantmentEffectEvent e);
}
