package net.pitan76.mcpitanlib.api.event.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.event.BaseEvent;
import net.pitan76.mcpitanlib.api.item.ArmorEquipmentType;
import net.pitan76.mcpitanlib.api.util.WorldUtil;
import net.pitan76.mcpitanlib.api.util.entity.EquipmentSlotUtil;
import net.pitan76.mcpitanlib.api.util.entity.LivingEntityUtil;
import net.pitan76.mcpitanlib.midohra.entity.EntityWrapper;

public class InventoryTickEvent extends BaseEvent {
    public ItemStack stack;
    public World world;
    public Entity entity;
    public int slot;
    public boolean selected;
    public EquipmentSlot equipmentSlot;

    public InventoryTickEvent(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        this.stack = stack;
        this.world = world;
        this.entity = entity;
        this.slot = slot;
        this.selected = selected;
        this.equipmentSlot = EquipmentSlotUtil.fromEntitySlotId(slot);
    }

    public InventoryTickEvent(ItemStack stack, ServerWorld world, Entity entity, EquipmentSlot slot) {
        this(stack, world, entity, EquipmentSlotUtil.getEntitySlotId(slot), isSelected(entity, slot, stack));
        this.equipmentSlot = slot;
    }

    private static boolean isSelected(Entity entity, EquipmentSlot slot, ItemStack stack) {
        if (entity instanceof LivingEntity) {
            LivingEntity livingEntity = (LivingEntity) entity;
            ItemStack equippedStack = LivingEntityUtil.getEquippedStack(livingEntity, slot);
            return equippedStack.isOf(stack.getItem()) && equippedStack.getCount() == stack.getCount();
        } else {
            return false;
        }
    }

    public ItemStack getStack() {
        return stack;
    }

    public World getWorld() {
        return world;
    }

    public boolean isServer() {
        return !isClient();
    }

    public ServerWorld getServerWorld() {
        return (ServerWorld) world;
    }

    public Entity getEntity() {
        return entity;
    }

    public int getSlot() {
        return slot;
    }

    public boolean isSelected() {
        return selected;
    }
    
    public boolean isClient() {
        return WorldUtil.isClient(world);
    }

    public EquipmentSlot getEquipmentSlot() {
        return equipmentSlot;
    }

    public ArmorEquipmentType getArmorEquipmentType() {
        return EquipmentSlotUtil.getArmorEquipmentType(equipmentSlot);
    }

    public boolean isPlayer() {
        return getEntity() instanceof net.minecraft.entity.player.PlayerEntity;
    }

    public Player getPlayer() {
        if (isPlayer()) {
            return new Player((net.minecraft.entity.player.PlayerEntity) getEntity());
        } else {
            return null;
        }
    }

    public EntityWrapper getEntityWrapper() {
        return EntityWrapper.of(getEntity());
    }

    public net.pitan76.mcpitanlib.midohra.world.World getMidohraWorld() {
        return net.pitan76.mcpitanlib.midohra.world.World.of(getWorld());
    }

    public net.pitan76.mcpitanlib.midohra.item.ItemStack getStackM() {
        return net.pitan76.mcpitanlib.midohra.item.ItemStack.of(getStack());
    }

    public net.pitan76.mcpitanlib.midohra.world.ServerWorld getServerWorldM() {
        return net.pitan76.mcpitanlib.midohra.world.ServerWorld.of(getServerWorld());
    }
}
