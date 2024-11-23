package net.pitan76.mcpitanlib.midohra.recipe.input;

import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.Inventory;
import org.jetbrains.annotations.Nullable;

public class CraftingRecipeInputOrInventory extends RecipeInputOrInventory {
    private final CraftingInventory inventory;

    public static CraftingRecipeInputOrInventory EMPTY = new CraftingRecipeInputOrInventory(null);

    protected CraftingRecipeInputOrInventory(@Nullable CraftingInventory inventory) {
        super(null);
        this.inventory = inventory;
    }

    public static CraftingRecipeInputOrInventory of(Inventory inventory) {
        if (inventory instanceof CraftingInventory) {
            return of((CraftingInventory) inventory);
        }

        return EMPTY;
    }

    public static CraftingRecipeInputOrInventory of(CraftingInventory inventory) {
        return new CraftingRecipeInputOrInventory(inventory);
    }

    @Nullable
    @Override
    public CraftingInventory getRaw() {
        return inventory;
    }

    @Nullable
    @Override
    public CraftingInventory toMinecraft() {
        return getRaw();
    }

    @Nullable
    @Override
    public CraftingInventory getRecipeInput() {
        return getRaw();
    }

    @Nullable
    @Override
    public Inventory getInventory() {
        return getRaw();
    }
}
