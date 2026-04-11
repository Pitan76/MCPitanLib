package net.pitan76.mcpitanlib.api.item;

import net.pitan76.mcpitanlib.api.item.v2.CompatItem;
import net.pitan76.mcpitanlib.api.item.v2.CompatibleItemSettings;
import net.pitan76.mcpitanlib.api.item.v2.ItemSettingsBuilder;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;

public class FoodItem extends CompatItem {
    public FoodItem(int hunger, float saturation, CompatIdentifier id) {
        this(new ItemSettingsBuilder().foodComponent(CompatFoodComponent.create().setHunger(hunger).setSaturation(saturation)).build(id));
    }

    public FoodItem(CompatFoodComponent component, CompatIdentifier id) {
        this(new ItemSettingsBuilder().foodComponent(component).build(id));
    }

    public FoodItem(CompatFoodComponent component, ItemSettingsBuilder builder, CompatIdentifier id) {
        this(builder.foodComponent(component).build(id));
    }

    public FoodItem(CompatFoodComponent component, CompatibleItemSettings settings) {
        this(settings.food(component));
    }

    public FoodItem(CompatibleItemSettings settings) {
        super(settings);
    }
}
