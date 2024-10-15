package net.pitan76.mcpitanlib.fabric.mixin;

import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.input.RecipeInput;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Recipe.class)
public interface RecipeMixin<C extends RecipeInput> {

    /*
    @Inject(method = "getRemainder", at = @At("RETURN"))
    default void injectGetRemainder(C input, CallbackInfoReturnable<DefaultedList<ItemStack>> cir) {
        DefaultedList<ItemStack> defaultedList = cir.getReturnValue();

        int i = 0;
        for(ItemStack stack : defaultedList) {
            if (stack.getItem() instanceof FixedRecipeRemainderItem) {
                FixedRecipeRemainderItem remainder = (FixedRecipeRemainderItem) stack.getItem();
                defaultedList.set(i, remainder.getFixedRecipeRemainder(stack.copy()));
            }
            i++;
        }
    }

     */
}
