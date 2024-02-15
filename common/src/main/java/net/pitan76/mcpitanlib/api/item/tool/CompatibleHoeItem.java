package net.pitan76.mcpitanlib.api.item.tool;

import net.minecraft.block.BlockState;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.pitan76.mcpitanlib.api.item.CompatibleItemSettings;
import net.pitan76.mcpitanlib.api.item.ExtendItemProvider;

public class CompatibleHoeItem extends HoeItem implements ExtendItemProvider {
    public CompatibleHoeItem(CompatibleToolMaterial material, int attackDamage, float attackSpeed, CompatibleItemSettings settings) {
        super(material, attackDamage, attackSpeed, settings.build());
    }

    public CompatibleHoeItem(int attackDamage, float attackSpeed, ToolMaterial material, CompatibleItemSettings settings) {
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
