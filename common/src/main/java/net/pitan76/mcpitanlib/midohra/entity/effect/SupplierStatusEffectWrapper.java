package net.pitan76.mcpitanlib.midohra.entity.effect;

import net.minecraft.world.effect.MobEffect;

import java.util.function.Supplier;

/**
 * 登録が後から解決される場合に使う。Forge / NeoForge では初期化時点でまだ実体が無い。
 */
public class SupplierStatusEffectWrapper extends StatusEffectWrapper {

    private final Supplier<MobEffect> supplier;

    protected SupplierStatusEffectWrapper(Supplier<MobEffect> supplier) {
        this.supplier = supplier;
    }

    public static SupplierStatusEffectWrapper of(Supplier<MobEffect> supplier) {
        return new SupplierStatusEffectWrapper(supplier);
    }

    @Override
    public MobEffect get() {
        return supplier.get();
    }
}
