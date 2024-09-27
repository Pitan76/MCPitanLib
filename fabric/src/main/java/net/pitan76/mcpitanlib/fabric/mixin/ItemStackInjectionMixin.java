package net.pitan76.mcpitanlib.fabric.mixin;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.pitan76.mcpitanlib.api.util.NbtUtil;
import net.pitan76.mcpitanlib.fabric.injection.ItemStackInjection;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ItemStack.class)
public class ItemStackInjectionMixin implements ItemStackInjection {

    public NbtCompound method_7953(NbtCompound nbt) {
        ItemStack stack = (ItemStack) (Object) this;
        NbtUtil.putSimpleItemStack(nbt, "simple_stack", stack);
        return nbt;
    }
}
