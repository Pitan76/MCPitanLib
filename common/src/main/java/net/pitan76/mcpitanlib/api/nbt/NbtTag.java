package net.pitan76.mcpitanlib.api.nbt;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtType;

@Deprecated
public class NbtTag extends NbtCompound {
    public NbtTag() {
        super();
    }

    /**
     * Create NbtTag instance
     * @return NbtTag instance
     */
    public static NbtTag create() {
        return new NbtTag();
    }

    /**
     * Cast to NbtTag
     * @param nbt Nbt Object
     * @return NbtTag
     */
    public static NbtTag from(Object nbt) {
        if (nbt instanceof NbtCompound) {
            return (NbtTag) nbt;
        }
        return (NbtTag) nbt;
    }

    /**
     * Check item stack nbt
     * @param stack Item stack
     * @return boolean
     */
    public static boolean hasNbt(ItemStack stack) {
        return !stack.getComponents().isEmpty();
    }

    /**
     * Get nbt from item stack
     * @param stack Item stack
     * @return NbtTag
     */
    public static NbtTag getNbt(ItemStack stack) {
        return from(stack.get(DataComponentTypes.CUSTOM_DATA).copyNbt());
    }

    /**
     * Set nbt to item stack
     * @param stack Item stack
     * @param nbt NbtTag
     */
    public static void setNbt(ItemStack stack, NbtTag nbt) {
        stack.set(DataComponentTypes.CUSTOM_DATA, NbtComponent.of(nbt));
    }

    public boolean contains(String key) {
        return super.contains(key);
    }

    public NbtType<NbtCompound> getNbtType() {
        return super.getNbtType();
    }
}