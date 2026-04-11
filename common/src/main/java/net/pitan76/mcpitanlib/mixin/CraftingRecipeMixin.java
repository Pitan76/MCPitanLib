package net.pitan76.mcpitanlib.mixin;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.core.NonNullList;
import net.pitan76.mcpitanlib.api.item.FixedRecipeRemainderItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CraftingRecipe.class)
public interface CraftingRecipeMixin {
    @Inject(method = "defaultCraftingReminder", at = @At("RETURN"))
    private static void mcpitanlib$collectRecipeRemainders(CraftingInput input, CallbackInfoReturnable<NonNullList<ItemStack>> cir) {
        NonNullList<ItemStack> defaultedList = cir.getReturnValue();

        int i = 0;
        for (ItemStack stack : defaultedList) {
            if (stack.getItem() instanceof FixedRecipeRemainderItem) {
                FixedRecipeRemainderItem remainder = (FixedRecipeRemainderItem) stack.getItem();
                defaultedList.set(i, remainder.getFixedRecipeRemainder(stack.copy()));
            }
            i++;
        }
    }
}
