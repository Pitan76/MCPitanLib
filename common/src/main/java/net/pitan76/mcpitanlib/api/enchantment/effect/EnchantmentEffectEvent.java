package net.pitan76.mcpitanlib.api.enchantment.effect;

import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import net.pitan76.mcpitanlib.api.event.BaseEvent;
import net.pitan76.mcpitanlib.midohra.entity.EntityWrapper;

public class EnchantmentEffectEvent extends BaseEvent {

    public ServerWorld world;
    public int level;
    public EnchantmentEffectContext context;

    /**
     * 効果を受ける側。post_attackで affected を victim にした場合は攻撃された相手。
     */
    public Entity target;

    public Vec3d pos;

    public EnchantmentEffectEvent(ServerWorld world, int level, EnchantmentEffectContext context, Entity target, Vec3d pos) {
        this.world = world;
        this.level = level;
        this.context = context;
        this.target = target;
        this.pos = pos;
    }

    public ServerWorld getWorld() {
        return world;
    }

    public net.pitan76.mcpitanlib.midohra.world.World getMidohraWorld() {
        return net.pitan76.mcpitanlib.midohra.world.World.of(world);
    }

    /**
     * エンチャントのレベル。レベルIは1。
     */
    public int getLevel() {
        return level;
    }

    public Entity getTarget() {
        return target;
    }

    /**
     * エンチャントが付いた装備を持っている側。
     */
    public LivingEntity getOwner() {
        return context.owner();
    }

    /**
     * エンチャントが付いているItemStack。
     */
    public ItemStack getStack() {
        return context.stack();
    }

    public net.pitan76.mcpitanlib.midohra.item.ItemStack getMidohraStack() {
        return net.pitan76.mcpitanlib.midohra.item.ItemStack.of(getStack());
    }

    public Vec3d getPos() {
        return pos;
    }

    public net.pitan76.mcpitanlib.midohra.util.math.Vector3d getMidohraPos() {
        return net.pitan76.mcpitanlib.midohra.util.math.Vector3d.of(pos);
    }

    public EnchantmentEffectContext getContext() {
        return context;
    }

    public EntityWrapper getOwnerWrapper() {
        return EntityWrapper.of(getOwner());
    }

    public EntityWrapper getTargetWrapper() {
        return EntityWrapper.of(getTarget());
    }
}
