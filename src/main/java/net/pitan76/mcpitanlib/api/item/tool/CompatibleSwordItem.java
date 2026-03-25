package net.pitan76.mcpitanlib.api.item.tool;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.pitan76.mcpitanlib.api.event.item.PostHitEvent;
import net.pitan76.mcpitanlib.api.event.item.PostMineEvent;
import net.pitan76.mcpitanlib.api.util.ItemStackUtil;
import net.pitan76.mcpitanlib.api.item.v2.CompatItemProvider;
import net.pitan76.mcpitanlib.api.item.v2.CompatibleItemSettings;

public class CompatibleSwordItem extends Item implements CompatItemProvider {

    public CompatibleItemSettings settings;

    public CompatibleSwordItem(CompatibleToolMaterial material, int attackDamage, float attackSpeed, CompatibleItemSettings settings) {
        super(settings.build().sword(material.build(), attackDamage, attackSpeed));
        this.settings = settings;
    }

    public CompatibleSwordItem(int attackDamage, float attackSpeed, ToolMaterial material, CompatibleItemSettings settings) {
        super(settings.build().sword(material, attackDamage, attackSpeed));
        this.settings = settings;
    }

    @Override
    public CompatibleItemSettings getCompatSettings() {
        return settings;
    }

    public boolean overrideIsSuitableFor(BlockState state) {
        return super.isCorrectToolForDrops(getDefaultInstance(), state);
    }

    @Deprecated
    @Override
    public boolean isCorrectToolForDrops(ItemStack stack, BlockState state) {
        return overrideIsSuitableFor(state);
    }

    public float overrideGetMiningSpeedMultiplier(ItemStack stack, BlockState state) {
        return 1.0F;
    }

    @Deprecated
    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        return overrideGetMiningSpeedMultiplier(stack, state) * super.getDestroySpeed(stack, state);
    }

    @Deprecated
    @Override
    public void hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        postHit(new PostHitEvent(stack, target, attacker));
    }

    @Deprecated
    @Override
    public boolean mineBlock(ItemStack stack, Level world, BlockState state, BlockPos pos, LivingEntity miner) {
        return postMine(new PostMineEvent(stack, world, state, pos, miner));
    }

    /**
     * post hit event
     * @param event PostHitEvent
     * @return boolean
     */
    public boolean postHit(PostHitEvent event) {
        super.hurtEnemy(event.stack, event.target, event.attacker);
        return true;
    }

    /**
     * post mine event
     * @param event PostMineEvent
     * @return boolean
     */
    public boolean postMine(PostMineEvent event) {
        return super.mineBlock(event.stack, event.world, event.state, event.pos, event.miner);
    }

    // -1.20.6
    public boolean isDamageableOnDefault() {
        return ItemStackUtil.getMaxDamage(this) > 0;
    }
}