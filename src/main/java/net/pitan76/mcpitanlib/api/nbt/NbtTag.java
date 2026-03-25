package net.pitan76.mcpitanlib.api.nbt;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.TagType;

import net.minecraft.nbt.Tag;
import net.minecraft.nbt.StreamTagVisitor;
import net.minecraft.nbt.TagVisitor;

import java.io.DataOutput;
import java.io.IOException;

@Deprecated
public class NbtTag {
    public CompoundTag nbt;

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
        if (nbt instanceof CompoundTag) {
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
        return from(stack.get(DataComponents.CUSTOM_DATA).copyTag());
    }

    /**
     * Set nbt to item stack
     * @param stack Item stack
     * @param nbt NbtTag
     */
    public static void setNbt(ItemStack stack, NbtTag nbt) {
        stack.set(DataComponents.CUSTOM_DATA, CustomData.of(nbt.nbt));
    }

    public boolean contains(String key) {
        return nbt.contains(key);
    }

    public void write(DataOutput output) throws IOException {
        nbt.write(output);
    }

    public byte getType() {
        return nbt.getId();
    }

    public TagType<CompoundTag> getNbtType() {
        return nbt.getType();
    }

    public Tag copy() {
        return nbt.copy();
    }

    public int getSizeInBytes() {
        return nbt.sizeInBytes();
    }

    public void accept(TagVisitor visitor) {
        nbt.accept(visitor);
    }

    public StreamTagVisitor.ValueResult doAccept(StreamTagVisitor visitor) {
        return nbt.accept(visitor);
    }
}