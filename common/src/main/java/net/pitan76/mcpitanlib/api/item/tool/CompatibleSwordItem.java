package net.pitan76.mcpitanlib.api.item.tool;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.event.item.PostHitEvent;
import net.pitan76.mcpitanlib.api.event.item.PostMineEvent;
import net.pitan76.mcpitanlib.api.item.CompatibleItemSettings;
import net.pitan76.mcpitanlib.api.item.ExtendItemProvider;
import net.pitan76.mcpitanlib.api.util.ItemStackUtil;

public class CompatibleSwordItem extends SwordItem implements ExtendItemProvider {
    public CompatibleSwordItem(CompatibleToolMaterial material, int attackDamage, float attackSpeed, CompatibleItemSettings settings) {
        super(material, settings.build());
    }

    public CompatibleSwordItem(int attackDamage, float attackSpeed, ToolMaterial material, CompatibleItemSettings settings) {
        super(material, settings.build());
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
        return overrideGetMiningSpeedMultiplier(stack, state) * super.getMiningSpeed(stack, state);
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

    // -1.20.6
    public boolean isDamageableOnDefault() {
        return ItemStackUtil.getMaxDamage(this) > 0;
    }
}