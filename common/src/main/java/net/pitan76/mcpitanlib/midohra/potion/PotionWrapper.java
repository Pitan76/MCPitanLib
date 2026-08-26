package net.pitan76.mcpitanlib.midohra.potion;

import net.minecraft.potion.Potion;
import net.minecraft.registry.Registries;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.midohra.item.ItemStack;

public class PotionWrapper {

    private final Potion potion;

    protected PotionWrapper() {
        this.potion = null;
    }

    protected PotionWrapper(Potion potion) {
        this.potion = potion;
    }

    public static PotionWrapper of(Potion potion) {
        return new PotionWrapper(potion);
    }

    public static PotionWrapper of() {
        return new PotionWrapper();
    }

    public static PotionWrapper of(CompatIdentifier id) {
        Potion potion = Registries.POTION.get(id.toMinecraft());
        if (potion == null) return of();

        return of(potion);
    }

    public Potion get() {
        return potion;
    }

    public boolean isEmpty() {
        return get() == null;
    }

    public boolean isPresent() {
        return !isEmpty();
    }

    public Potion getEntry() {
        return get();
    }

    public CompatIdentifier getId() {
        return CompatIdentifier.fromMinecraft(Registries.POTION.getId(get()));
    }

    /**
     * 飲むポーションのItemStackを作る。
     */
    public ItemStack createStack() {
        return ItemStack.of(net.pitan76.mcpitanlib.api.potion.PotionUtil.createPotion(get()));
    }

    public ItemStack createSplashStack() {
        return ItemStack.of(net.pitan76.mcpitanlib.api.potion.PotionUtil.createSplashPotion(get()));
    }

    public ItemStack createLingeringStack() {
        return ItemStack.of(net.pitan76.mcpitanlib.api.potion.PotionUtil.createLingeringPotion(get()));
    }

    public ItemStack createTippedArrowStack() {
        return ItemStack.of(net.pitan76.mcpitanlib.api.potion.PotionUtil.createTippedArrow(get()));
    }

    @Override
    public String toString() {
        return isEmpty() ? "empty" : getId().toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof PotionWrapper)) return false;

        return ((PotionWrapper) obj).get() == get();
    }

    @Override
    public int hashCode() {
        return isEmpty() ? 0 : get().hashCode();
    }
}
