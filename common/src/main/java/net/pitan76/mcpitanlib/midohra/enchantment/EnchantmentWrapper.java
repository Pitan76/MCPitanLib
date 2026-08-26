package net.pitan76.mcpitanlib.midohra.enchantment;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;

import java.util.Optional;

/**
 * エンチャントへの参照。
 * <p>
 * 1.21以降エンチャントはデータパックレジストリに移り、ワールドを読み込むまで実体が存在しない。
 * そのためIDだけを保持し、実体が必要なときにワールドから引く。
 */
public class EnchantmentWrapper {

    private final ResourceKey<Enchantment> key;

    protected EnchantmentWrapper(ResourceKey<Enchantment> key) {
        this.key = key;
    }

    public static EnchantmentWrapper of(ResourceKey<Enchantment> key) {
        return new EnchantmentWrapper(key);
    }

    public static EnchantmentWrapper of(CompatIdentifier id) {
        return of(ResourceKey.create(Registries.ENCHANTMENT, id.toMinecraft()));
    }

    public static EnchantmentWrapper of(String id) {
        return of(CompatIdentifier.of(id));
    }

    public ResourceKey<Enchantment> getKey() {
        return key;
    }

    public CompatIdentifier getId() {
        return CompatIdentifier.fromMinecraft(key.identifier());
    }

    /**
     * ワールドから実体を引く。データパックに存在しない場合は空。
     */
    public Optional<Holder.Reference<Enchantment>> getEntry(Level world) {
        return world.registryAccess().get(key);
    }

    public boolean isPresent(Level world) {
        return getEntry(world).isPresent();
    }

    public Optional<Enchantment> get(Level world) {
        return getEntry(world).map(Holder.Reference::value);
    }

    /**
     * このエンチャントが付いたエンチャントの本を作る。
     */
    public net.pitan76.mcpitanlib.midohra.item.ItemStack createEnchantedBook(Level world, int level) {
        return net.pitan76.mcpitanlib.midohra.item.ItemStack.of(
                net.pitan76.mcpitanlib.api.util.EnchantmentUtil.createEnchantedBook(this, world, level));
    }

    public net.pitan76.mcpitanlib.midohra.item.ItemStack createEnchantedBook(Level world) {
        return createEnchantedBook(world, 1);
    }

    /**
     * ItemStackに付いているこのエンチャントのレベル。付いていない場合は0。
     */
    public int getLevel(net.minecraft.world.item.ItemStack stack, Level world) {
        return getEntry(world).map(entry -> net.minecraft.world.item.enchantment.EnchantmentHelper.getItemEnchantmentLevel(entry, stack)).orElse(0);
    }

    // ------------------------------------------------------------------
    // midohra版
    // ------------------------------------------------------------------

    public Optional<Holder.Reference<Enchantment>> getEntry(net.pitan76.mcpitanlib.midohra.world.World world) {
        return getEntry(world.getRaw());
    }

    public boolean isPresent(net.pitan76.mcpitanlib.midohra.world.World world) {
        return isPresent(world.getRaw());
    }

    public Optional<Enchantment> get(net.pitan76.mcpitanlib.midohra.world.World world) {
        return get(world.getRaw());
    }

    public net.pitan76.mcpitanlib.midohra.item.ItemStack createEnchantedBook(net.pitan76.mcpitanlib.midohra.world.World world, int level) {
        return createEnchantedBook(world.getRaw(), level);
    }

    public net.pitan76.mcpitanlib.midohra.item.ItemStack createEnchantedBook(net.pitan76.mcpitanlib.midohra.world.World world) {
        return createEnchantedBook(world, 1);
    }

    public int getLevel(net.pitan76.mcpitanlib.midohra.item.ItemStack stack, net.pitan76.mcpitanlib.midohra.world.World world) {
        return getLevel(stack.toMinecraft(), world.getRaw());
    }

    @Override
    public String toString() {
        return getId().toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof EnchantmentWrapper)) return false;

        return ((EnchantmentWrapper) obj).getKey().equals(getKey());
    }

    @Override
    public int hashCode() {
        return key.hashCode();
    }
}
