package net.pitan76.mcpitanlib.midohra.component.item;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Rarity;
import net.pitan76.mcpitanlib.api.util.CompatRarity;

public class RarityComponentType extends ItemComponentType<Rarity> {

    public RarityComponentType() {
        super(DataComponentTypes.RARITY);
    }

    @Override
    public void put(ItemStack stack, Rarity value) {
        stack.set(DataComponentTypes.RARITY, value);
    }

    @Override
    public Rarity get(ItemStack stack) {
        return stack.getRarity();
    }

    public void put(ItemStack stack, CompatRarity rarity) {
        put(stack, rarity.get());
    }

    public CompatRarity getCompatRarity(ItemStack stack) {
        return CompatRarity.of(get(stack));
    }
}
