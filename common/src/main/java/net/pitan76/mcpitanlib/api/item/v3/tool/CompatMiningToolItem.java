package net.pitan76.mcpitanlib.api.item.v3.tool;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.pitan76.mcpitanlib.api.item.args.tool.MiningSpeedMultiplierArgs;
import net.pitan76.mcpitanlib.api.item.args.tool.SuitableForArgs;
import net.pitan76.mcpitanlib.api.item.tool.CompatibleMiningToolItem;
import net.pitan76.mcpitanlib.api.item.tool.CompatibleToolMaterial;
import net.pitan76.mcpitanlib.api.item.v2.CompatibleItemSettings;
import net.pitan76.mcpitanlib.api.item.v2.ItemSettingsBuilder;
import net.pitan76.mcpitanlib.api.tag.TagKey;
import net.pitan76.mcpitanlib.api.tag.v2.CompatTagKey;
import net.pitan76.mcpitanlib.api.tag.v2.typed.BlockTagKey;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;

public class CompatMiningToolItem extends CompatibleMiningToolItem {

    public CompatMiningToolItem(CompatibleToolMaterial material, int attackDamage, float attackSpeed, TagKey<Block> tagKey, CompatibleItemSettings settings) {
        super(material, attackDamage, attackSpeed, tagKey, settings);
    }

    public CompatMiningToolItem(CompatibleToolMaterial material, int attackDamage, float attackSpeed, CompatTagKey<Block> tagKey, CompatibleItemSettings settings) {
        super(material, attackDamage, attackSpeed, tagKey, settings);
    }

    public CompatMiningToolItem(CompatibleToolMaterial material, int attackDamage, float attackSpeed, BlockTagKey tagKey, CompatibleItemSettings settings) {
        super(material, attackDamage, attackSpeed, tagKey, settings);
    }

    public CompatMiningToolItem(CompatibleToolMaterial material, int attackDamage, float attackSpeed, TagKey<Block> tagKey, ItemSettingsBuilder builder, CompatIdentifier id) {
        this(material, attackDamage, attackSpeed, tagKey, builder.build(id));
    }

    public CompatMiningToolItem(CompatibleToolMaterial material, int attackDamage, float attackSpeed, TagKey<Block> tagKey, ItemSettingsBuilder builder) {
        this(material, attackDamage, attackSpeed, tagKey, builder.build());
    }

    public float getMiningSpeedMultiplier(MiningSpeedMultiplierArgs args) {
        return super.overrideGetMiningSpeedMultiplier(args.stack.toMinecraft(), args.state.toMinecraft());
    }

    @Override
    @Deprecated
    public float overrideGetMiningSpeedMultiplier(ItemStack stack, BlockState state) {
        return getMiningSpeedMultiplier(
                new MiningSpeedMultiplierArgs(net.pitan76.mcpitanlib.midohra.item.ItemStack.of(stack),
                        net.pitan76.mcpitanlib.midohra.block.BlockState.of(state)));
    }

    public boolean isSuitableFor(SuitableForArgs args) {
        return super.overrideIsSuitableFor(args.state.toMinecraft());
    }

    @Override
    @Deprecated
    public boolean overrideIsSuitableFor(BlockState state) {
        return isSuitableFor(new SuitableForArgs(net.pitan76.mcpitanlib.midohra.block.BlockState.of(state)));
    }
}
