package net.pitan76.mcpitanlib.midohra.recipe.input;

import net.minecraft.inventory.Inventory;
import org.jetbrains.annotations.Nullable;

public class TypedRecipeInputOrInventory<T extends Inventory> extends RecipeInputOrInventory {
    private final T recipeInput;

    public static TypedRecipeInputOrInventory<?> NONE = new TypedRecipeInputOrInventory<>(null);

    protected TypedRecipeInputOrInventory(T recipeInput) {
        super(null);
        this.recipeInput = recipeInput;
    }

    public static <T extends Inventory> TypedRecipeInputOrInventory<T> _of(T recipeInput) {
        return new TypedRecipeInputOrInventory<>(recipeInput);
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
        return getRaw();
    }
}
