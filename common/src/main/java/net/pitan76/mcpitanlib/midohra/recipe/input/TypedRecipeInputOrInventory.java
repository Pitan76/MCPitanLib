package net.pitan76.mcpitanlib.midohra.recipe.input;

import net.minecraft.inventory.Inventory;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.minecraft.recipe.input.RecipeInput;
import net.minecraft.recipe.input.SingleStackRecipeInput;
import net.pitan76.mcpitanlib.api.util.inventory.CompatInventory;
import org.jetbrains.annotations.Nullable;

public class TypedRecipeInputOrInventory<T extends RecipeInput> extends RecipeInputOrInventory {
    private final T recipeInput;

    public static TypedRecipeInputOrInventory<?> NONE = new TypedRecipeInputOrInventory<>(null);

    protected TypedRecipeInputOrInventory(T recipeInput) {
        super(null);
        this.recipeInput = recipeInput;
    }

    public static <T extends RecipeInput> TypedRecipeInputOrInventory<T> _of(T recipeInput) {
        return new TypedRecipeInputOrInventory<>(recipeInput);
    }

    public static <T extends RecipeInput> TypedRecipeInputOrInventory<T> _of(CompatInventory recipeInput) {
        if (recipeInput instanceof RecipeInput) {
            return _of((T) recipeInput);
        }

        if (recipeInput.callSize() == 1) {
            SingleStackRecipeInput singleStackInput = new SingleStackRecipeInput(recipeInput.getStack(0));
            return _of((T) singleStackInput);
        }

        if (recipeInput.callSize() == 4) {
            CraftingRecipeInput craftingInput = CraftingRecipeInput.create(2, 2, recipeInput.callGetHeldStacks());
            return _of((T) craftingInput);
        }

        if (recipeInput.callSize() == 9) {
            CraftingRecipeInput craftingInput = CraftingRecipeInput.create(3, 3, recipeInput.callGetHeldStacks());
            return _of((T) craftingInput);
        }

        return new TypedRecipeInputOrInventory<>(null);
    }

    public static TypedRecipeInputOrInventory<?> of(Inventory inventory) {
        if (inventory instanceof RecipeInput) {
            return _of((RecipeInput) inventory);
        }

        return NONE;
    }

    @Nullable
    @Override
    public T getRaw() {
        return recipeInput;
    }

    @Nullable
    @Override
    public T getRecipeInput() {
        return getRaw();
    }

    @Nullable
    @Override
    public T toMinecraft() {
        return getRaw();
    }

    @Nullable
    @Override
    public Inventory getInventory() {
        if (getRaw() instanceof Inventory) {
            return (Inventory) getRaw();
        }

        return null;
    }
}
