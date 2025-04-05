package net.pitan76.mcpitanlib.api.item.tool;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.event.item.PostHitEvent;
import net.pitan76.mcpitanlib.api.event.item.PostMineEvent;
import net.pitan76.mcpitanlib.api.item.v2.CompatItemProvider;
import net.pitan76.mcpitanlib.api.item.v2.CompatibleItemSettings;
import net.pitan76.mcpitanlib.api.tag.TagKey;
import net.pitan76.mcpitanlib.api.util.ItemStackUtil;

public class CompatibleMiningToolItem extends Item implements CompatItemProvider {

    public CompatibleItemSettings settings;

    @Deprecated
    protected CompatibleMiningToolItem(float attackDamage, float attackSpeed, ToolMaterial material, net.minecraft.registry.tag.TagKey<Block> effectiveBlocks, Settings settings) {
        super(settings.tool(material, effectiveBlocks, attackDamage, attackSpeed, 0));
    }

    public CompatibleMiningToolItem(CompatibleToolMaterial material, int attackDamage, float attackSpeed, TagKey<Block> tagKey, CompatibleItemSettings settings) {
        this(attackDamage, attackSpeed, material.build(), tagKey.getTagKey(), settings.build());
        this.settings = settings;
    }

    @Override
    public CompatibleItemSettings getCompatSettings() {
        return settings;
    }

    public boolean overrideIsSuitableFor(BlockState state) {
        return super.isCorrectForDrops(getDefaultStack(), state);
    }

    @Deprecated
    @Override
    public boolean isCorrectForDrops(ItemStack stack, BlockState state) {
        return overrideIsSuitableFor(state);
    }

    public float overrideGetMiningSpeedMultiplier(ItemStack stack, BlockState state) {
        return 1.0F;
    }

    @Deprecated
    @Override
    public float getMiningSpeed(ItemStack stack, BlockState state) {
        return overrideGetMiningSpeedMultiplier(stack, state);
    }

    /**
     * post hit event
     * @param event PostHitEvent
     * @return boolean
     */
    public boolean postHit(PostHitEvent event) {
        super.postHit(event.stack, event.target, event.attacker);
        return true;
    }

    /**
     * post mine event
     * @param event PostMineEvent
     * @return boolean
     */
    public boolean postMine(PostMineEvent event) {
        return super.postMine(event.stack, event.world, event.state, event.pos, event.miner);
    }

    @Deprecated
    @Override
    public void postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        postHit(new PostHitEvent(stack, target, attacker));
    }

    @Deprecated
    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        return postMine(new PostMineEvent(stack, world, state, pos, miner));
    }

    // -1.20.6
    public boolean isDamageableOnDefault() {
        return ItemStackUtil.getMaxDamage(this) > 0;
    }
}