package net.pitan76.mcpitanlib.core.registry;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CookingFuel;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.number.ints.ResolvableInt;
import net.pitan76.mcpitanlib.midohra.item.ItemWrapper;

import java.util.Optional;
import java.util.function.Supplier;

public class FuelRegistry {
    private FuelRegistry() {

    }

    @ExpectPlatform
    public static void register(int time, Supplier<ItemLike> item) {

    }

    public static void register(int time, ItemLike... item) {
        for (ItemLike i : item) {
            register(time, () -> i);
        }
    }

    @Deprecated
    public static int get(ItemStack stack) {
        return 0;
    }

    /**
     * 26.3~: Fuel is an item component (minecraft:cooking_fuel), and the burn duration is
     * resolved through a LootContext, so a ServerLevel is required. Returns 0 on the client.
     */
    public static int get(Level world, ItemStack stack) {
        if (!(world instanceof ServerLevel serverLevel)) return 0;
        return ResolvableInt.getFromItem(stack, DataComponents.COOKING_FUEL, CookingFuel::burnTime, createLootContext(serverLevel), 0);
    }

    public static boolean isFuel(Level world, ItemStack stack) {
        return stack.has(DataComponents.COOKING_FUEL);
    }

    private static LootContext createLootContext(ServerLevel world) {
        return new LootContext.Builder(new LootParams.Builder(world).create(LootContextParamSets.EMPTY)).create(Optional.empty());
    }

    public static void register(int time, ItemWrapper item) {
        register(time, item::get);
    }

    public static int get(net.pitan76.mcpitanlib.midohra.world.World world, net.pitan76.mcpitanlib.midohra.item.ItemStack stack) {
        return get(world.toMinecraft(), stack.toMinecraft());
    }

    public static boolean isFuel(net.pitan76.mcpitanlib.midohra.world.World world, net.pitan76.mcpitanlib.midohra.item.ItemStack stack) {
        return isFuel(world.toMinecraft(), stack.toMinecraft());
    }
}
