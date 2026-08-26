package net.pitan76.mcpitanlib.midohra.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.Registries;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;

import java.util.Optional;

/**
 * エンチャントへの参照。
 * <p>
 * 1.21以降エンチャントはデータパックレジストリに移り、ワールドを読み込むまで実体が存在しない。
 * そのためIDだけを保持し、実体が必要なときにワールドから引く。
 */
public class EnchantmentWrapper {

    private final RegistryKey<Enchantment> key;

    protected EnchantmentWrapper(RegistryKey<Enchantment> key) {
        this.key = key;
    }

    public static EnchantmentWrapper of(RegistryKey<Enchantment> key) {
        return new EnchantmentWrapper(key);
    }

    public static EnchantmentWrapper of(CompatIdentifier id) {
        return of(RegistryKey.of(RegistryKeys.ENCHANTMENT, id.toMinecraft()));
    }

    public static EnchantmentWrapper of(String id) {
        return of(CompatIdentifier.of(id));
    }

    public RegistryKey<Enchantment> getKey() {
        return key;
    }

    public CompatIdentifier getId() {
        return CompatIdentifier.fromMinecraft(key.getValue());
    }

    /**
     * ワールドから実体を引く。データパックに存在しない場合は空。
     */
    /**
     * このバージョンではエンチャントは静的レジストリにあるため、ワールドは使わない。
     */
    public Optional<Enchantment> getEntry(World world) {
        return Registries.ENCHANTMENT.getOrEmpty(key.getValue());
    }

    public boolean isPresent(World world) {
        return getEntry(world).isPresent();
    }

    public Optional<Enchantment> get(World world) {
        return getEntry(world);
    }

    /**
     * このエンチャントが付いたエンチャントの本を作る。
     */
    public net.pitan76.mcpitanlib.midohra.item.ItemStack createEnchantedBook(World world, int level) {
        return net.pitan76.mcpitanlib.midohra.item.ItemStack.of(
                net.pitan76.mcpitanlib.api.util.EnchantmentUtil.createEnchantedBook(this, world, level));
    }

    public net.pitan76.mcpitanlib.midohra.item.ItemStack createEnchantedBook(World world) {
        return createEnchantedBook(world, 1);
    }

    /**
     * ItemStackに付いているこのエンチャントのレベル。付いていない場合は0。
     */
    public int getLevel(net.minecraft.item.ItemStack stack, World world) {
        return getEntry(world).map(entry -> net.minecraft.enchantment.EnchantmentHelper.getLevel(entry, stack)).orElse(0);
    }

    // ------------------------------------------------------------------
    // midohra版
    // ------------------------------------------------------------------

    public Optional<Enchantment> getEntry(net.pitan76.mcpitanlib.midohra.world.World world) {
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
