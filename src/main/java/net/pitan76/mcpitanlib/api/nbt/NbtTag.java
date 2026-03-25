package net.pitan76.mcpitanlib.api.nbt;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtType;

import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.scanner.NbtScanner;
import net.minecraft.nbt.visitor.NbtElementVisitor;

import java.io.DataOutput;
import java.io.IOException;

@Deprecated
public class NbtTag {
    public NbtCompound nbt;

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
        stack.set(DataComponentTypes.CUSTOM_DATA, NbtComponent.of(nbt.nbt));
    }

    public boolean contains(String key) {
        return nbt.contains(key);
    }

    public void write(DataOutput output) throws IOException {
        nbt.write(output);
    }

    public byte getType() {
        return nbt.getType();
    }

    public NbtType<NbtCompound> getNbtType() {
        return nbt.getNbtType();
    }

    public NbtElement copy() {
        return nbt.copy();
    }

    public int getSizeInBytes() {
        return nbt.getSizeInBytes();
    }

    public void accept(NbtElementVisitor visitor) {
        nbt.accept(visitor);
    }

    public NbtScanner.Result doAccept(NbtScanner visitor) {
        return nbt.doAccept(visitor);
    }
}