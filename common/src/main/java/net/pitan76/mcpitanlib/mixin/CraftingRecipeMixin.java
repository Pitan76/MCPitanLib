package net.pitan76.mcpitanlib.mixin;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.CraftingRecipe;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.minecraft.util.collection.DefaultedList;
import net.pitan76.mcpitanlib.api.item.FixedRecipeRemainderItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CraftingRecipe.class)
public interface CraftingRecipeMixin {
    @Inject(method = "collectRecipeRemainders", at = @At("RETURN"))
    private static void mcpitanlib$collectRecipeRemainders(CraftingRecipeInput input, CallbackInfoReturnable<DefaultedList<ItemStack>> cir) {
        DefaultedList<ItemStack> defaultedList = cir.getReturnValue();

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
