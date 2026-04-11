package net.pitan76.mcpitanlib.midohra.component;

import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Unit;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.component.TypedEntityData;
import net.pitan76.mcpitanlib.api.item.stack.LoreUtil;
import net.pitan76.mcpitanlib.api.util.*;
import net.pitan76.mcpitanlib.midohra.component.item.CustomNameComponentType;
import net.pitan76.mcpitanlib.midohra.component.item.ItemComponentType;
import net.pitan76.mcpitanlib.midohra.component.item.RarityComponentType;

import java.util.List;

public class ItemComponentTypes {
    public static final ItemComponentType<CompoundTag> CUSTOM_DATA = new ItemComponentType<>(DataComponents.CUSTOM_DATA) {
        @Override
        public void put(ItemStack stack, CompoundTag value) {
            CustomDataUtil.setNbt(stack, value);
        }

        @Override
        public CompoundTag get(ItemStack stack) {
            return CustomDataUtil.getNbt(stack);
        }
    };

    public static final ItemComponentType<Integer> MAX_STACK_SIZE = new ItemComponentType<>(DataComponents.MAX_STACK_SIZE) {
        @Override
        public void put(ItemStack stack, Integer value) {
            stack.set(DataComponents.MAX_STACK_SIZE, value);
        }

        @Override
        public Integer get(ItemStack stack) {
            return stack.getMaxStackSize();
        }
    };

    public static final ItemComponentType<Integer> MAX_DAMAGE = new ItemComponentType<>(DataComponents.MAX_DAMAGE) {
        @Override
        public void put(ItemStack stack, Integer value) {
            stack.set(DataComponents.MAX_DAMAGE, value);
        }

        @Override
        public Integer get(ItemStack stack) {
            return stack.getMaxDamage();
        }
    };

    public static final ItemComponentType<Integer> DAMAGE = new ItemComponentType<>(DataComponents.DAMAGE) {
        @Override
        public void put(ItemStack stack, Integer value) {
            stack.setDamageValue(value);
        }

        @Override
        public Integer get(ItemStack stack) {
            return stack.getDamageValue();
        }
    };

    public static final ItemComponentType<Boolean> UNBREAKABLE = new ItemComponentType<>(DataComponents.UNBREAKABLE) {
        @Override
        public void put(ItemStack stack, Boolean value) {
            if (value) {
                stack.set(DataComponents.UNBREAKABLE, Unit.valueOf(""));
            } else {
                stack.remove(DataComponents.UNBREAKABLE);
            }
        }

        @Override
        public Boolean get(ItemStack stack) {
            return has(stack);
        }
    };

    public static final ItemComponentType<Rarity> RARITY = new RarityComponentType();

    public static final CustomNameComponentType CUSTOM_NAME = new CustomNameComponentType();

    public static final ItemComponentType<CompoundTag> BLOCK_ENTITY_DATA = new ItemComponentType<>(DataComponents.BLOCK_ENTITY_DATA) {
        @Override
        public void put(ItemStack stack, CompoundTag value) {
            BlockEntityDataUtil.setBlockEntityNbt(stack, value);
        }

        @Override
        public CompoundTag get(ItemStack stack) {
            return BlockEntityDataUtil.getBlockEntityNbt(stack);
        }
    };

    public static final ItemComponentType<CompoundTag> ENTITY_DATA = new ItemComponentType<>(DataComponents.ENTITY_DATA) {
        @Override
        public void put(ItemStack stack, CompoundTag value) {
            EntityType<?> type = EntityTypeUtil.fromId(CompatIdentifier.of(NbtUtil.getString(value, "id")));
            stack.set(DataComponents.ENTITY_DATA, TypedEntityData.of(type, value));
        }

        @Override
        public CompoundTag get(ItemStack stack) {
            if (!has(stack)) return NbtUtil.create();
            if (!stack.has(DataComponents.ENTITY_DATA)) return NbtUtil.create();

            TypedEntityData<EntityType<?>> data = stack.get(DataComponents.ENTITY_DATA);
            CompoundTag nbt = data.copyTagWithoutId();

            String id = EntityTypeUtil.toID(data.type()).toString();
            NbtUtil.putString(nbt, "id", id);
            return nbt;
        }
    };

    public static final ItemComponentType<List<Component>> LORE = new ItemComponentType<>(DataComponents.LORE) {
        @Override
        public void put(ItemStack stack, List<Component> value) {
            LoreUtil.setLore(stack, value);
        }

        @Override
        public List<Component> get(ItemStack stack) {
            return LoreUtil.getLore(stack);
        }
    };
}
