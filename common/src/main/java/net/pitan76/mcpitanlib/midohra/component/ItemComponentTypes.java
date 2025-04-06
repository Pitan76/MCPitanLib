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
    public static final ItemComponentType<NbtCompound> CUSTOM_DATA = new ItemComponentType<NbtCompound>("components.minecraft:custom_data") {
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

    public static final ItemComponentType<Integer> MAX_STACK_SIZE = new ItemComponentType<Integer>("components.minecraft:max_stack_size") {
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

    public static final ItemComponentType<Integer> MAX_DAMAGE = new ItemComponentType<Integer>("components.minecraft:max_damage") {
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

    public static final ItemComponentType<Integer> DAMAGE = new ItemComponentType<Integer>("Damage") {
        @Override
        public void put(ItemStack stack, Integer value) {
            stack.setDamage(value);
        }

        @Override
        public boolean has(ItemStack stack) {
            return stack.hasTag() && NbtUtil.has(stack.getTag(), "Damage");
        }

        @Override
        public Integer get(ItemStack stack) {
            return stack.getDamage();
        }
    };

    public static final ItemComponentType<Boolean> UNBREAKABLE = new ItemComponentType<Boolean>("Unbreakable") {
        @Override
        public void put(ItemStack stack, Boolean value) {
            if (value) {
                stack.getTag().putBoolean("Unbreakable", true);
            } else {
                stack.removeSubTag("Unbreakable");
            }
        }

        @Override
        public Boolean get(ItemStack stack) {
            return has(stack);
        }
    };

    public static final ItemComponentType<Rarity> RARITY = new RarityComponentType();

    public static final CustomNameComponentType CUSTOM_NAME = new CustomNameComponentType();

    public static final ItemComponentType<NbtCompound> BLOCK_ENTITY_DATA = new ItemComponentType<NbtCompound>("BlockEntityTag") {
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

    public static final ItemComponentType<NbtCompound> ENTITY_DATA = new ItemComponentType<NbtCompound>("EntityTag") {
        @Override
        public void put(ItemStack stack, NbtCompound value) {
            stack.putSubTag("EntityTag", value);
        }

        @Override
        public boolean has(ItemStack stack) {
            return stack.hasTag() && NbtUtil.has(stack.getTag(), "EntityTag");
        }

        @Override
        public NbtCompound get(ItemStack stack) {
            return stack.getSubTag("EntityTag");
        }
    };

    public static final ItemComponentType<List<Text>> LORE = new ItemComponentType<List<Text>>("display.Lore") {
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
