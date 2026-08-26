package net.pitan76.mcpitanlib.api.enchantment.effect;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.phys.Vec3;
import net.pitan76.mcpitanlib.api.event.BaseEvent;
import net.pitan76.mcpitanlib.midohra.entity.EntityWrapper;

public class EnchantmentEffectEvent extends BaseEvent {

    public ServerLevel world;
    public int level;
    public EnchantedItemInUse context;

    /**
     * 効果を受ける側。post_attackで affected を victim にした場合は攻撃された相手。
     */
    public Entity target;

    public Vec3 pos;

    public EnchantmentEffectEvent(ServerLevel world, int level, EnchantedItemInUse context, Entity target, Vec3 pos) {
        this.world = world;
        this.level = level;
        this.context = context;
        this.target = target;
        this.pos = pos;
    }

    public ServerLevel getWorld() {
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
        return context.itemStack();
    }

    public net.pitan76.mcpitanlib.midohra.item.ItemStack getMidohraStack() {
        return net.pitan76.mcpitanlib.midohra.item.ItemStack.of(getStack());
    }

    public Vec3 getPos() {
        return pos;
    }

    public net.pitan76.mcpitanlib.midohra.util.math.Vector3d getMidohraPos() {
        return net.pitan76.mcpitanlib.midohra.util.math.Vector3d.of(pos);
    }

    public EnchantedItemInUse getContext() {
        return context;
    }

    public EntityWrapper getOwnerWrapper() {
        return EntityWrapper.of(getOwner());
    }

    public EntityWrapper getTargetWrapper() {
        return EntityWrapper.of(getTarget());
    }
}
