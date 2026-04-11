package net.pitan76.mcpitanlib.midohra.component.item;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.pitan76.mcpitanlib.api.util.CompatRarity;

public class RarityComponentType extends ItemComponentType<Rarity> {

    public RarityComponentType() {
        super(DataComponents.RARITY);
    }

    @Override
    public void put(ItemStack stack, Rarity value) {
        stack.set(DataComponents.RARITY, value);
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
