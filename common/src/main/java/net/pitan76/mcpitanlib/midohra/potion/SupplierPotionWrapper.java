package net.pitan76.mcpitanlib.midohra.potion;

import net.minecraft.potion.Potion;

import java.util.function.Supplier;

/**
 * 登録が後から解決される場合に使う。Forge / NeoForge では初期化時点でまだ実体が無い。
 */
public class SupplierPotionWrapper extends PotionWrapper {

    private final Supplier<Potion> supplier;

    protected SupplierPotionWrapper(Supplier<Potion> supplier) {
        this.supplier = supplier;
    }

    public static SupplierPotionWrapper of(Supplier<Potion> supplier) {
        return new SupplierPotionWrapper(supplier);
    }

    @Override
    public Potion get() {
        return supplier.get();
    }
}
