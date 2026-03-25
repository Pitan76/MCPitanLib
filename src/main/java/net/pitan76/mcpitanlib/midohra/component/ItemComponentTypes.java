// TODO(Ravel): Failed to fully resolve file: null cannot be cast to non-null type com.intellij.psi.PsiJavaCodeReferenceElement
// TODO(Ravel): Failed to fully resolve file: null cannot be cast to non-null type com.intellij.psi.PsiJavaCodeReferenceElement
package net.pitan76.mcpitanlib.midohra.component;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.TypedEntityData;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Rarity;
import net.minecraft.util.Unit;
import net.pitan76.mcpitanlib.api.item.stack.LoreUtil;
import net.pitan76.mcpitanlib.api.util.*;
import net.pitan76.mcpitanlib.midohra.component.item.CustomNameComponentType;
import net.pitan76.mcpitanlib.midohra.component.item.ItemComponentType;
import net.pitan76.mcpitanlib.midohra.component.item.RarityComponentType;

import java.util.List;

public class ItemComponentTypes {
    public static final ItemComponentType<NbtCompound> CUSTOM_DATA = new ItemComponentType<>(DataComponentTypes.CUSTOM_DATA) {
        @Override
        public void put(ItemStack stack, NbtCompound value) {
            CustomDataUtil.setNbt(stack, value);
        }

        @Override
        public NbtCompound get(ItemStack stack) {
            return CustomDataUtil.getNbt(stack);
        }
    };

    public static final ItemComponentType<Integer> MAX_STACK_SIZE = new ItemComponentType<>(DataComponentTypes.MAX_STACK_SIZE) {
        @Override
        public void put(ItemStack stack, Integer value) {
            stack.set(DataComponentTypes.MAX_STACK_SIZE, value);
        }

        @Override
        public Integer get(ItemStack stack) {
            return stack.getMaxCount();
        }
    };

    public static final ItemComponentType<Integer> MAX_DAMAGE = new ItemComponentType<>(DataComponentTypes.MAX_DAMAGE) {
        @Override
        public void put(ItemStack stack, Integer value) {
            stack.set(DataComponentTypes.MAX_DAMAGE, value);
        }

        @Override
        public Integer get(ItemStack stack) {
            return stack.getMaxDamage();
        }
    };

    public static final ItemComponentType<Integer> DAMAGE = new ItemComponentType<>(DataComponentTypes.DAMAGE) {
        @Override
        public void put(ItemStack stack, Integer value) {
            stack.setDamage(value);
        }

        @Override
        public Integer get(ItemStack stack) {
            return stack.getDamage();
        }
    };

    public static final ItemComponentType<Boolean> UNBREAKABLE = new ItemComponentType<>(DataComponentTypes.UNBREAKABLE) {
        @Override
        public void put(ItemStack stack, Boolean value) {
            if (value) {
                stack.set(DataComponentTypes.UNBREAKABLE, Unit.valueOf(""));
            } else {
                stack.remove(DataComponentTypes.UNBREAKABLE);
            }
        }

        @Override
        public Boolean get(ItemStack stack) {
            return has(stack);
        }
    };

    public static final ItemComponentType<Rarity> RARITY = new RarityComponentType();

    public static final CustomNameComponentType CUSTOM_NAME = new CustomNameComponentType();

    public static final ItemComponentType<NbtCompound> BLOCK_ENTITY_DATA = new ItemComponentType<>(DataComponentTypes.BLOCK_ENTITY_DATA) {
        @Override
        public void put(ItemStack stack, NbtCompound value) {
            BlockEntityDataUtil.setBlockEntityNbt(stack, value);
        }

        @Override
        public NbtCompound get(ItemStack stack) {
            return BlockEntityDataUtil.getBlockEntityNbt(stack);
        }
    };

    public static final ItemComponentType<NbtCompound> ENTITY_DATA = new ItemComponentType<>(DataComponentTypes.ENTITY_DATA) {
        @Override
        public void put(ItemStack stack, NbtCompound value) {
            EntityType<?> type = EntityTypeUtil.fromId(CompatIdentifier.of(NbtUtil.getString(value, "id")));
            stack.set(DataComponentTypes.ENTITY_DATA, TypedEntityData.create(type, value));
        }

        @Override
        public NbtCompound get(ItemStack stack) {
            if (!has(stack)) return NbtUtil.create();
            if (!stack.contains(DataComponentTypes.ENTITY_DATA)) return NbtUtil.create();

            TypedEntityData<EntityType<?>> data = stack.get(DataComponentTypes.ENTITY_DATA);
            NbtCompound nbt = data.copyNbtWithoutId();

            String id = EntityTypeUtil.toID(data.getType()).toString();
            NbtUtil.putString(nbt, "id", id);
            return nbt;
        }
    };

    public static final ItemComponentType<List<Text>> LORE = new ItemComponentType<>(DataComponentTypes.LORE) {
        @Override
        public void put(ItemStack stack, List<Text> value) {
            LoreUtil.setLore(stack, value);
        }

        @Override
        public List<Text> get(ItemStack stack) {
            return LoreUtil.getLore(stack);
        }
    };
}
