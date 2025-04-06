package net.pitan76.mcpitanlib.midohra.component.item;

import net.minecraft.item.ItemStack;
import net.minecraft.util.Rarity;
import net.pitan76.mcpitanlib.api.util.CompatRarity;

public class RarityComponentType extends ItemComponentType<Rarity> {

    public RarityComponentType() {
        super("components.minecraft:rarity");
    }

    @Override
    public void put(ItemStack stack, Rarity value) {

    }

    @Override
    public Rarity get(ItemStack stack) {
        return stack.getRarity();
    }

    @Override
    public boolean has(ItemStack stack) {
        return !stack.getRarity().equals(Rarity.COMMON);
    }

    public void put(ItemStack stack, CompatRarity rarity) {

    }

    public CompatRarity getCompatRarity(ItemStack stack) {
        return CompatRarity.of(get(stack));
    }
}
