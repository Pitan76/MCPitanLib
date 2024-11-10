package net.pitan76.mcpitanlib.api.item.v2;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Rarity;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.event.item.CanRepairArgs;
import net.pitan76.mcpitanlib.api.event.item.EnchantabilityArgs;
import net.pitan76.mcpitanlib.api.event.item.EnchantableArgs;
import net.pitan76.mcpitanlib.api.item.ExtendItem;
import net.pitan76.mcpitanlib.api.item.args.RarityArgs;
import net.pitan76.mcpitanlib.api.item.args.StoppedUsingArgs;
import net.pitan76.mcpitanlib.api.item.args.UseActionArgs;
import net.pitan76.mcpitanlib.api.item.consume.CompatUseAction;
import net.pitan76.mcpitanlib.api.tag.item.RepairIngredientTag;
import net.pitan76.mcpitanlib.api.util.CompatRarity;
import net.pitan76.mcpitanlib.core.Dummy;
import net.pitan76.mcpitanlib.midohra.item.ItemWrapper;

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
    public UseAction getUseAction(ItemStack stack) {
        return getUseAction(new UseActionArgs(stack)).getUseAction();
    }

    public CompatUseAction getUseAction(UseActionArgs args) {
        return CompatUseAction.of(super.getUseAction(args.stack));
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

    @Override
    protected String getOrCreateTranslationKey() {
        if (settings.changedTranslationKey)
            return settings.translationKey;

        return super.getOrCreateTranslationKey();
    }

    @Deprecated
    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        onStoppedUsing(new StoppedUsingArgs(stack, world, user, remainingUseTicks));
    }

    public boolean onStoppedUsing(StoppedUsingArgs args) {
        super.onStoppedUsing(args.stack, args.world, args.user, args.remainingUseTicks);
        return false;
    }
}
