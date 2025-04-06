package net.pitan76.mcpitanlib.api.item.tool;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MiningToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.event.item.ItemUseOnEntityEvent;
import net.pitan76.mcpitanlib.api.event.item.PostHitEvent;
import net.pitan76.mcpitanlib.api.event.item.PostMineEvent;
import net.pitan76.mcpitanlib.api.item.v2.CompatItemProvider;
import net.pitan76.mcpitanlib.api.item.v2.CompatibleItemSettings;
import net.pitan76.mcpitanlib.api.tag.TagKey;
import net.pitan76.mcpitanlib.api.tag.v2.CompatTagKey;
import net.pitan76.mcpitanlib.api.tag.v2.typed.BlockTagKey;
import net.pitan76.mcpitanlib.api.util.CompatActionResult;
import net.pitan76.mcpitanlib.api.util.ItemStackUtil;

public class CompatibleMiningToolItem extends MiningToolItem implements CompatItemProvider {

    public CompatibleItemSettings settings;

    @Deprecated
    protected CompatibleMiningToolItem(float attackDamage, float attackSpeed, ToolMaterial material, net.minecraft.registry.tag.TagKey<Block> effectiveBlocks, Settings settings) {
        super(attackDamage, attackSpeed, material, effectiveBlocks, settings);
    }

    public CompatibleMiningToolItem(CompatibleToolMaterial material, int attackDamage, float attackSpeed, TagKey<Block> tagKey, CompatibleItemSettings settings) {
        this(attackDamage, attackSpeed, material.build(), tagKey.getTagKey(), settings.build());
        this.settings = settings;
    }

    public CompatibleMiningToolItem(CompatibleToolMaterial material, int attackDamage, float attackSpeed, CompatTagKey<Block> tagKey, CompatibleItemSettings settings) {
        this(attackDamage, attackSpeed, material.build(), tagKey.getTagKey(), settings.build());
        this.settings = settings;
    }

    public CompatibleMiningToolItem(CompatibleToolMaterial material, int attackDamage, float attackSpeed, BlockTagKey tagKey, CompatibleItemSettings settings) {
        this(attackDamage, attackSpeed, material.build(), tagKey.getTagKey(), settings.build());
        this.settings = settings;
    }

    @Override
    public CompatibleItemSettings getCompatSettings() {
        return settings;
    }

    public boolean overrideIsSuitableFor(BlockState state) {
        return super.isSuitableFor(state);
    }

    public float overrideGetMiningSpeedMultiplier(ItemStack stack, BlockState state) {
        return super.getMiningSpeedMultiplier(stack, state);
    }

    /**
     * post hit event
     * @param event PostHitEvent
     * @return boolean
     */
    public boolean postHit(PostHitEvent event) {
        return super.postHit(event.stack, event.target, event.attacker);
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
    public boolean isSuitableFor(BlockState state) {
        return overrideIsSuitableFor(state);
    }

    @Deprecated
    @Override
    public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
        return overrideGetMiningSpeedMultiplier(stack, state);
    }

    @Deprecated
    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        return postHit(new PostHitEvent(stack, target, attacker));
    }

    @Deprecated
    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        return postMine(new PostMineEvent(stack, world, state, pos, miner));
    }

    @Deprecated
    public boolean isDamageable() {
        return isDamageableOnDefault();
    }

    // -1.20.6
    public boolean isDamageableOnDefault() {
        return super.isDamageable();
    }

    @Deprecated
    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        return onRightClickOnEntity(new ItemUseOnEntityEvent(stack, user, entity, hand)).toActionResult();
    }

    @Deprecated
    @Override
    public CompatActionResult onRightClickOnEntity(ItemUseOnEntityEvent event, Options options) {
        return CompatItemProvider.super.onRightClickOnEntity(event, options);
    }

    public CompatActionResult onRightClickOnEntity(ItemUseOnEntityEvent e) {
        return CompatActionResult.of(super.useOnEntity(e.stack, e.user.getEntity(), e.entity, e.hand));
    }
}