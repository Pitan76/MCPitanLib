package net.pitan76.mcpitanlib.api.event.v1;

import net.minecraft.core.Holder;
import net.minecraft.resources.Identifier;
import net.minecraft.util.profiling.InactiveProfiler;
import net.minecraft.world.item.crafting.Recipe;
import net.pitan76.mcpitanlib.api.event.v0.event.RecipeManagerEvent;
import net.pitan76.mcpitanlib.core.registry.DynamicRecipeRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class RecipeManagerRegistry {
    public static List<CustomRecipeManager> managers = new ArrayList<>();

    private static boolean initialized = false;

    public static void register(CustomRecipeManager manager) {
        if (!initialized) {
            initialized = true;
            DynamicRecipeRegistry.register(RecipeManagerRegistry::apply);
        }

        managers.add(manager);
    }

    /**
     * レシピレジストリが凍結される直前に呼ばれる。
     * <p>
     * 26.3 からレシピはレジストリ管理になったため、既存レシピの差し替え・削除はできない。
     * イベントで追加されたレシピだけがレジストリに流し込まれる。
     */
    private static void apply(DynamicRecipeRegistry.Context context) {
        if (managers.isEmpty()) return;

        SortedMap<Identifier, Recipe<?>> sortedMap = new TreeMap<>();
        for (Holder.Reference<Recipe<?>> holder : context.getRegistry().listElements().toList())
            sortedMap.put(holder.key().identifier(), holder.value());

        RecipeManagerEvent event = new RecipeManagerEvent(sortedMap, context.getResourceManager(), InactiveProfiler.INSTANCE);
        managers.forEach((manager) -> manager.apply(event));

        sortedMap.forEach(context::add);
    }

    @FunctionalInterface
    public interface CustomRecipeManager {
        void apply(RecipeManagerEvent event);
    }
}
