package net.pitan76.mcpitanlib.fabric.mixin;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Recipe;
import net.minecraft.util.collection.DefaultedList;
import net.pitan76.mcpitanlib.api.item.FixedRecipeRemainderItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Recipe.class)
public interface RecipeMixin<C extends Inventory> {
    @Inject(method = "getRemainder", at = @At("RETURN"))
    default void injectGetRemainder(C inventory, CallbackInfoReturnable<DefaultedList<ItemStack>> cir) {
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
}
