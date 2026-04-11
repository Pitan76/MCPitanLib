package net.pitan76.mcpitanlib.api.item.tool;

import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
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

public class CompatibleMiningToolItem extends Item implements CompatItemProvider {

    public CompatibleItemSettings settings;

    @Deprecated
    protected CompatibleMiningToolItem(float attackDamage, float attackSpeed, ToolMaterial material, net.minecraft.tags.TagKey<Block> effectiveBlocks, Properties settings) {
        super(settings.tool(material, effectiveBlocks, attackDamage, attackSpeed, 0));
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
        return overrideGetMiningSpeedMultiplier(stack, state);
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

    // -1.20.6
    public boolean isDamageableOnDefault() {
        return ItemStackUtil.getMaxDamage(this) > 0;
    }

    @Deprecated
    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player user, LivingEntity entity, InteractionHand hand) {
        return onRightClickOnEntity(new ItemUseOnEntityEvent(stack, user, entity, hand)).toActionResult();
    }

    @Deprecated
    @Override
    public CompatActionResult onRightClickOnEntity(ItemUseOnEntityEvent event, Options options) {
        return CompatItemProvider.super.onRightClickOnEntity(event, options);
    }

    public CompatActionResult onRightClickOnEntity(ItemUseOnEntityEvent e) {
        return CompatActionResult.of(super.interactLivingEntity(e.stack, e.user.getEntity(), e.entity, e.hand));
    }
}