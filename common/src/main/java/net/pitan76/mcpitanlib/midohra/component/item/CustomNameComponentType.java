package net.pitan76.mcpitanlib.midohra.component.item;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

public class CustomNameComponentType extends ItemComponentType<Text> {

    public CustomNameComponentType() {
        super(DataComponentTypes.CUSTOM_NAME);
    }

    @Override
    public void put(ItemStack stack, Text value) {
        stack.set(DataComponentTypes.CUSTOM_NAME, value);
    }

    @Override
    public Text get(ItemStack stack) {
        if (!has(stack)) return Text.empty();
        return stack.get(DataComponentTypes.CUSTOM_NAME);
    }

    public String getAsString(ItemStack stack) {
        return get(stack).getString();
    }

    public void put(ItemStack stack, String name) {
        put(stack, Text.literal(name));
    }
}
