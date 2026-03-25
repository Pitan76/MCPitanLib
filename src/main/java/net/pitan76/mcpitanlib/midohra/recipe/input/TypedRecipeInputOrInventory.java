package net.pitan76.mcpitanlib.midohra.recipe.input;

import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.RecipeInput;
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

    public static TypedRecipeInputOrInventory<?> of(Container inventory) {
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
    public Container getInventory() {
        if (getRaw() instanceof Container) {
            return (Container) getRaw();
        }

        return null;
    }
}
