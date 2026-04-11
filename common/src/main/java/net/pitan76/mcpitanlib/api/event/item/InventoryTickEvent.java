package net.pitan76.mcpitanlib.api.event.item;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.event.BaseEvent;
import net.pitan76.mcpitanlib.api.item.ArmorEquipmentType;
import net.pitan76.mcpitanlib.api.util.WorldUtil;
import net.pitan76.mcpitanlib.api.util.entity.EquipmentSlotUtil;
import net.pitan76.mcpitanlib.api.util.entity.LivingEntityUtil;
import net.pitan76.mcpitanlib.midohra.entity.EntityWrapper;

public class InventoryTickEvent extends BaseEvent {
    public ItemStack stack;
    public Level world;
    public Entity entity;
    public int slot;
    public boolean selected;
    public EquipmentSlot equipmentSlot;

    public InventoryTickEvent(ItemStack stack, Level world, Entity entity, int slot, boolean selected) {
        this.stack = stack;
        this.world = world;
        this.entity = entity;
        this.slot = slot;
        this.selected = selected;
        this.equipmentSlot = EquipmentSlotUtil.fromEntitySlotId(slot);
    }

    public InventoryTickEvent(ItemStack stack, ServerLevel world, Entity entity, EquipmentSlot slot) {
        this(stack, world, entity, EquipmentSlotUtil.getEntitySlotId(slot), isSelected(entity, slot, stack));
        this.equipmentSlot = slot;
    }

    private static boolean isSelected(Entity entity, EquipmentSlot slot, ItemStack stack) {
        if (entity instanceof LivingEntity) {
            LivingEntity livingEntity = (LivingEntity) entity;
            ItemStack equippedStack = LivingEntityUtil.getEquippedStack(livingEntity, slot);
            return equippedStack.is(stack.getItem()) && equippedStack.getCount() == stack.getCount();
        } else {
            return false;
        }
    }

    public ItemStack getStack() {
        return stack;
    }

    public Level getWorld() {
        return world;
    }

    public boolean isServer() {
        return !isClient();
    }

    public ServerLevel getServerWorld() {
        return (ServerLevel) world;
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
        return getEntity() instanceof net.minecraft.world.entity.player.Player;
    }

    public Player getPlayer() {
        if (isPlayer()) {
            return new Player((net.minecraft.world.entity.player.Player) getEntity());
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
