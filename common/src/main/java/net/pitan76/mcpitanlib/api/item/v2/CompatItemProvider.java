package net.pitan76.mcpitanlib.api.item.v2;

import net.minecraft.item.Item;
import net.pitan76.mcpitanlib.api.event.item.CanRepairArgs;
import net.pitan76.mcpitanlib.api.event.item.EnchantabilityArgs;
import net.pitan76.mcpitanlib.api.event.item.EnchantableArgs;
import net.pitan76.mcpitanlib.api.event.item.InventoryTickEvent;
import net.pitan76.mcpitanlib.api.item.ExtendItemProvider;
import net.pitan76.mcpitanlib.api.item.args.RarityArgs;
import net.pitan76.mcpitanlib.api.item.args.UseActionArgs;
import net.pitan76.mcpitanlib.api.item.consume.CompatUseAction;
import net.pitan76.mcpitanlib.api.tag.item.RepairIngredientTag;
import net.pitan76.mcpitanlib.api.util.CompatRarity;
import net.pitan76.mcpitanlib.midohra.item.ItemWrapper;

public interface CompatItemProvider extends ExtendItemProvider {
    CompatibleItemSettings getCompatSettings();

    default ItemWrapper getWrapper() {
        return this instanceof Item ? ItemWrapper.of((Item) this) : ItemWrapper.of();
    }

    default CompatUseAction getUseAction(UseActionArgs args, Options options) {
        options.cancel = false;
        return null;
    }

    default CompatRarity getRarity(RarityArgs args) {
        return getCompatSettings().rarity;
    }

    default boolean isEnchantable(EnchantableArgs args) {
        return getCompatSettings().enchantability != -1;
    }

    default int getEnchantability(EnchantabilityArgs args) {
        return getCompatSettings().enchantability;
    }

    default boolean canRepair(CanRepairArgs args) {
        RepairIngredientTag tag = getCompatSettings().repairIngredientTag;
        return tag != null && tag.contains(args.stack);
    }

    default void inventoryTick(InventoryTickEvent e, Options options) {
        options.cancel = false;
    }
}
