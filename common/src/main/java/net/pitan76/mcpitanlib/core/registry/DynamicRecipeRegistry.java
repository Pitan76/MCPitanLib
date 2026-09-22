package net.pitan76.mcpitanlib.core.registry;

import net.minecraft.core.RegistrationInfo;
import net.minecraft.core.WritableRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.crafting.Recipe;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * コードからレシピを追加するための入口。
 * <p>
 * 26.3 からレシピは {@link Registries#RECIPE} レジストリ (リロード可能レジストリ) として
 * 読み込まれるようになり、{@code RecipeManager} 側は書き換えられなくなった。
 * そのため、レジストリが凍結される直前 ({@code RegistryLoadTask#freezeRegistry}) に
 * 割り込んで、ここに登録された {@link Injector} を流し込む。
 * <p>
 * ワールドを読み込むたびにレジストリは組み直されるため、登録内容は保持し続ける。
 */
public class DynamicRecipeRegistry {

    private static final List<Injector> injectors = new CopyOnWriteArrayList<>();

    @Nullable
    private static ResourceManager resourceManager = null;

    /**
     * ミキシンから呼ばれる。
     */
    public static void setResourceManager(@Nullable ResourceManager manager) {
        resourceManager = manager;
    }

    public static void register(Injector injector) {
        injectors.add(injector);
    }

    /**
     * ミキシンから呼ばれる。レジストリが凍結される直前の1回だけ。
     */
    public static void inject(WritableRegistry<Recipe<?>> registry) {
        if (injectors.isEmpty()) return;

        Context context = new Context(registry);
        for (Injector injector : injectors) {
            injector.inject(context);
        }
    }

    @FunctionalInterface
    public interface Injector {
        void inject(Context context);
    }

    public static class Context {
        private final WritableRegistry<Recipe<?>> registry;

        protected Context(WritableRegistry<Recipe<?>> registry) {
            this.registry = registry;
        }

        public WritableRegistry<Recipe<?>> getRegistry() {
            return registry;
        }

        /**
         * レジストリ読み込みに使われた ResourceManager。取得できなかった場合は null。
         */
        @Nullable
        public ResourceManager getResourceManager() {
            return resourceManager;
        }

        public boolean contains(Identifier id) {
            return registry.containsKey(id);
        }

        /**
         * レシピを追加する。すでに同じIDが存在する場合 (データパック側が定義済みなど) は
         * データパック側を優先して何もしない。
         *
         * @return 実際に追加したら true
         */
        public boolean add(Identifier id, Recipe<?> recipe) {
            if (contains(id)) return false;

            registry.register(ResourceKey.create(Registries.RECIPE, id), recipe, RegistrationInfo.BUILT_IN);
            return true;
        }
    }
}
