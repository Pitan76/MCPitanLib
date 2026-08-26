package net.pitan76.mcpitanlib.api.enchantment.effect;

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

    /**
     * エンチャントが付いた装備を持っている側。
     */
    public LivingEntity owner;

    public ItemStack stack;

    /**
     * 効果を受ける側。
     */
    public Entity target;

    public Vec3d pos;

    public EnchantmentEffectEvent(ServerWorld world, int level, LivingEntity owner, ItemStack stack, Entity target, Vec3d pos) {
        this.world = world;
        this.level = level;
        this.owner = owner;
        this.stack = stack;
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

    public LivingEntity getOwner() {
        return owner;
    }

    /**
     * エンチャントが付いているItemStack。
     */
    public ItemStack getStack() {
        return stack;
    }

    public net.pitan76.mcpitanlib.midohra.item.ItemStack getMidohraStack() {
        return net.pitan76.mcpitanlib.midohra.item.ItemStack.of(stack);
    }

    public Vec3d getPos() {
        return pos;
    }

    public net.pitan76.mcpitanlib.midohra.util.math.Vector3d getMidohraPos() {
        return net.pitan76.mcpitanlib.midohra.util.math.Vector3d.of(pos);
    }

    public EntityWrapper getOwnerWrapper() {
        return EntityWrapper.of(getOwner());
    }

    public EntityWrapper getTargetWrapper() {
        return EntityWrapper.of(getTarget());
    }
}
