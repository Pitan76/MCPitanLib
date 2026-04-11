package net.pitan76.mcpitanlib.api.item.v2;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import net.pitan76.mcpitanlib.api.event.item.CanRepairArgs;
import net.pitan76.mcpitanlib.api.event.item.EnchantabilityArgs;
import net.pitan76.mcpitanlib.api.event.item.EnchantableArgs;
import net.pitan76.mcpitanlib.api.event.item.InventoryTickEvent;
import net.pitan76.mcpitanlib.api.item.ExtendItem;
import net.pitan76.mcpitanlib.api.item.args.RarityArgs;
import net.pitan76.mcpitanlib.api.item.args.StoppedUsingArgs;
import net.pitan76.mcpitanlib.api.item.args.UseActionArgs;
import net.pitan76.mcpitanlib.api.item.consume.CompatUseAction;
import net.pitan76.mcpitanlib.api.tag.item.RepairIngredientTag;
import net.pitan76.mcpitanlib.api.util.CompatRarity;
import net.pitan76.mcpitanlib.core.Dummy;
import net.pitan76.mcpitanlib.midohra.item.ItemWrapper;
import org.jetbrains.annotations.Nullable;

public class CompatItem extends ExtendItem {

    public CompatibleItemSettings settings;

    public CompatItem(CompatibleItemSettings settings) {
        super(settings);
        this.settings = settings;
    }

    public CompatibleItemSettings getCompatSettings() {
        return settings;
    }

    public ItemWrapper getWrapper() {
        return ItemWrapper.of(this);
    }

    @Deprecated
    @Override
    public ItemUseAnimation getUseAnimation(ItemStack stack) {
        return getUseAction(new UseActionArgs(stack)).get();
    }

    public CompatUseAction getUseAction(UseActionArgs args) {
        return CompatUseAction.of(super.getUseAnimation(args.stack));
    }

    @Deprecated
    @Override
    public Rarity getRarity(ItemStack stack) {
        return getRarity(new RarityArgs(stack)).get();
    }

    public CompatRarity getRarity(RarityArgs args) {
        return settings.rarity;
    }

    @Override
    public boolean isEnchantable(EnchantableArgs args) {
        return settings.enchantability != -1;
    }

    @Override
    public int getEnchantability(EnchantabilityArgs args) {
        return settings.enchantability;
    }

    @Override
    public boolean canRepair(CanRepairArgs args) {
        RepairIngredientTag tag = settings.repairIngredientTag;
        return tag != null && tag.contains(args.stack);
    }

    @Override
    public boolean hasRecipeRemainder(Dummy dummy) {
        return settings.recipeRemainder != null;
    }

    @Deprecated
    @Override
    public boolean releaseUsing(ItemStack stack, Level world, LivingEntity user, int remainingUseTicks) {
        return onStoppedUsing(new StoppedUsingArgs(stack, world, user, remainingUseTicks));
    }

    public boolean onStoppedUsing(StoppedUsingArgs args) {
        return super.releaseUsing(args.stack, args.world, args.user, args.remainingUseTicks);
    }

    @Deprecated
    @Override
    public void inventoryTick(ItemStack stack, ServerLevel world, Entity entity, @Nullable EquipmentSlot slot) {
        inventoryTick(new InventoryTickEvent(stack, world, entity, slot));
    }

    public void inventoryTick(InventoryTickEvent e) {
        super.inventoryTick(e.stack, e.getServerWorld(), e.entity, e.equipmentSlot);
    }
}
