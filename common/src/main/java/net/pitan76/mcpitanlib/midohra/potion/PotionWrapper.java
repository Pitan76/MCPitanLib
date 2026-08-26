package net.pitan76.mcpitanlib.midohra.potion;

import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Holder;
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
        Potion potion = BuiltInRegistries.POTION.getValue(id.toMinecraft());
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

    public Holder<Potion> getEntry() {
        return BuiltInRegistries.POTION.wrapAsHolder(get());
    }

    public CompatIdentifier getId() {
        return CompatIdentifier.fromMinecraft(BuiltInRegistries.POTION.getKey(get()));
    }

    /**
     * 飲むポーションのItemStackを作る。
     */
    public ItemStack createItemStack() {
        return ItemStack.of(net.pitan76.mcpitanlib.api.potion.PotionUtil.createPotion(getEntry()));
    }

    public ItemStack createSplashStack() {
        return ItemStack.of(net.pitan76.mcpitanlib.api.potion.PotionUtil.createSplashPotion(getEntry()));
    }

    public ItemStack createLingeringStack() {
        return ItemStack.of(net.pitan76.mcpitanlib.api.potion.PotionUtil.createLingeringPotion(getEntry()));
    }

    public ItemStack createTippedArrowStack() {
        return ItemStack.of(net.pitan76.mcpitanlib.api.potion.PotionUtil.createTippedArrow(getEntry()));
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
