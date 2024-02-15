package net.pitan76.mcpitanlib.api.item.tool;

import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.pitan76.mcpitanlib.api.item.CompatibleItemSettings;
import net.pitan76.mcpitanlib.api.item.ExtendItemProvider;

public class CompatibleSwordItem extends SwordItem implements ExtendItemProvider {
    public CompatibleSwordItem(CompatibleToolMaterial material, int attackDamage, float attackSpeed, CompatibleItemSettings settings) {
        super(material, attackDamage, attackSpeed, settings.build());
    }

    public CompatibleSwordItem(int attackDamage, float attackSpeed, ToolMaterial material, CompatibleItemSettings settings) {
        super(material, attackDamage, attackSpeed, settings.build());
    }

    public boolean overrideIsSuitableFor(BlockState state) {
        return super.isSuitableFor(state);
    }

    @Deprecated
    @Override
    public boolean isSuitableFor(BlockState state) {
        return overrideIsSuitableFor(state);
    }

    public float overrideGetMiningSpeedMultiplier(ItemStack stack, BlockState state) {
        return super.getMiningSpeedMultiplier(stack, state);
    }

    @Deprecated
    @Override
    public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
        return overrideGetMiningSpeedMultiplier(stack, state);
    }
}
