package net.pitan76.mcpitanlib.midohra.component.item;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;

public class CustomNameComponentType extends ItemComponentType<Component> {

    public CustomNameComponentType() {
        super(DataComponents.CUSTOM_NAME);
    }

    @Override
    public void put(ItemStack stack, Component value) {
        stack.set(DataComponents.CUSTOM_NAME, value);
    }

    @Override
    public Component get(ItemStack stack) {
        if (!has(stack)) return Component.empty();
        return stack.get(DataComponents.CUSTOM_NAME);
    }

    public String getAsString(ItemStack stack) {
        return get(stack).getString();
    }

    public void put(ItemStack stack, String name) {
        put(stack, Component.literal(name));
    }
}
