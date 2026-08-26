package net.pitan76.mcpitanlib.midohra.entity.effect;

import net.minecraft.entity.effect.StatusEffect;

import java.util.function.Supplier;

/**
 * 登録が後から解決される場合に使う。Forge / NeoForge では初期化時点でまだ実体が無い。
 */
public class SupplierStatusEffectWrapper extends StatusEffectWrapper {

    private final Supplier<StatusEffect> supplier;

    protected SupplierStatusEffectWrapper(Supplier<StatusEffect> supplier) {
        this.supplier = supplier;
    }

    public static SupplierStatusEffectWrapper of(Supplier<StatusEffect> supplier) {
        return new SupplierStatusEffectWrapper(supplier);
    }

    @Override
    public StatusEffect get() {
        return supplier.get();
    }
}
