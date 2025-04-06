package net.pitan76.mcpitanlib.midohra.component;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Rarity;
import net.pitan76.mcpitanlib.api.item.stack.LoreUtil;
import net.pitan76.mcpitanlib.api.util.BlockEntityDataUtil;
import net.pitan76.mcpitanlib.api.util.CustomDataUtil;
import net.pitan76.mcpitanlib.api.util.NbtUtil;
import net.pitan76.mcpitanlib.midohra.component.item.CustomNameComponentType;
import net.pitan76.mcpitanlib.midohra.component.item.ItemComponentType;
import net.pitan76.mcpitanlib.midohra.component.item.RarityComponentType;

import java.util.List;

public class ItemComponentTypes {
    public static final ItemComponentType<NbtCompound> CUSTOM_DATA = new ItemComponentType<>("components.minecraft:custom_data") {
        @Override
        public void put(ItemStack stack, NbtCompound value) {
            CustomDataUtil.setNbt(stack, value);
        }

        @Override
        public NbtCompound get(ItemStack stack) {
            return CustomDataUtil.getNbt(stack);
        }

        @Override
        public boolean has(ItemStack stack) {
            return CustomDataUtil.hasNbt(stack);
        }
    };

    public static final ItemComponentType<Integer> MAX_STACK_SIZE = new ItemComponentType<>("components.minecraft:max_stack_size") {
        @Override
        public void put(ItemStack stack, Integer value) {

        }

        @Override
        public boolean has(ItemStack stack) {
            return false;
        }

        @Override
        public Integer get(ItemStack stack) {
            return stack.getMaxCount();
        }
    };

    public static final ItemComponentType<Integer> MAX_DAMAGE = new ItemComponentType<>("components.minecraft:max_damage") {
        @Override
        public void put(ItemStack stack, Integer value) {

        }

        @Override
        public boolean has(ItemStack stack) {
            return false;
        }

        @Override
        public Integer get(ItemStack stack) {
            return stack.getMaxDamage();
        }
    };

    public static final ItemComponentType<Integer> DAMAGE = new ItemComponentType<>("Damage") {
        @Override
        public void put(ItemStack stack, Integer value) {
            stack.setDamage(value);
        }

        @Override
        public boolean has(ItemStack stack) {
            return stack.hasNbt() && NbtUtil.has(stack.getNbt(), "Damage");
        }

        @Override
        public Integer get(ItemStack stack) {
            return stack.getDamage();
        }
    };

    public static final ItemComponentType<Boolean> UNBREAKABLE = new ItemComponentType<>("Unbreakable") {
        @Override
        public void put(ItemStack stack, Boolean value) {
            if (value) {
                stack.getNbt().putBoolean("Unbreakable", true);
            } else {
                stack.removeSubNbt("Unbreakable");
            }
        }

        @Override
        public Boolean get(ItemStack stack) {
            return has(stack);
        }
    };

    public static final ItemComponentType<Rarity> RARITY = new RarityComponentType();

    public static final CustomNameComponentType CUSTOM_NAME = new CustomNameComponentType();

    public static final ItemComponentType<NbtCompound> BLOCK_ENTITY_DATA = new ItemComponentType<>("BlockEntityTag") {
        @Override
        public void put(ItemStack stack, NbtCompound value) {
            BlockEntityDataUtil.setBlockEntityNbt(stack, value);
        }

        @Override
        public boolean has(ItemStack stack) {
            return BlockEntityDataUtil.hasBlockEntityNbt(stack);
        }

        @Override
        public NbtCompound get(ItemStack stack) {
            return BlockEntityDataUtil.getBlockEntityNbt(stack);
        }
    };

    public static final ItemComponentType<NbtCompound> ENTITY_DATA = new ItemComponentType<>("EntityTag") {
        @Override
        public void put(ItemStack stack, NbtCompound value) {
            stack.setSubNbt("EntityTag", value);
        }

        @Override
        public boolean has(ItemStack stack) {
            return stack.hasNbt() && NbtUtil.has(stack.getNbt(), "EntityTag");
        }

        @Override
        public NbtCompound get(ItemStack stack) {
            return stack.getSubNbt("EntityTag");
        }
    };

    public static final ItemComponentType<List<Text>> LORE = new ItemComponentType<>("display.Lore") {
        @Override
        public void put(ItemStack stack, List<Text> value) {
            LoreUtil.setLore(stack, value);
        }

        @Override
        public boolean has(ItemStack stack) {
            return LoreUtil.hasLore(stack);
        }

        @Override
        public List<Text> get(ItemStack stack) {
            return LoreUtil.getLore(stack);
        }
    };
}
