package net.pitan76.mcpitanlib.midohra.component.item;

import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.pitan76.mcpitanlib.api.util.TextUtil;

public class CustomNameComponentType extends ItemComponentType<Text> {

    public CustomNameComponentType() {
        super("display.Name");
    }

    @Override
    public void put(ItemStack stack, Text value) {
        stack.setCustomName(value);
    }

    @Override
    public Text get(ItemStack stack) {
        if (!has(stack)) return TextUtil.empty();
        return stack.getName();
    }

    @Override
    public boolean has(ItemStack stack) {
        return stack.hasCustomName();
    }

    public String getAsString(ItemStack stack) {
        return get(stack).getString();
    }

    public void put(ItemStack stack, String name) {
        put(stack, TextUtil.literal(name));
    }
}
