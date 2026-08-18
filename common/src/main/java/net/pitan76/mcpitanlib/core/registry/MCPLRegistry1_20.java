package net.pitan76.mcpitanlib.core.registry;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.pitan76.mcpitanlib.api.item.CreativeTabBuilder;
import net.pitan76.mcpitanlib.api.util.ItemUtil;
import net.pitan76.mcpitanlib.api.registry.result.RegistrySupplier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

@Deprecated
public class MCPLRegistry1_20 {
    @Deprecated
    public static final Map<Identifier, RegistrySupplier<ItemGroup>> REGISTRY_SUPPLIER_ITEM_GROUP_CACHE = new HashMap<>();

    public static final Map<RegistryKey<ItemGroup>, List<Identifier>> ITEM_GROUP_ITEM_ID_CACHE = new LinkedHashMap<>();

    public static void addItemGroupItem(RegistryKey<ItemGroup> key, Identifier itemId) {
        ITEM_GROUP_ITEM_ID_CACHE.computeIfAbsent(key, k -> new ArrayList<>()).add(itemId);

        // allRegister()より後にアイテムが生成されるプラットフォームがあるため、ここで即時登録する
        CreativeTabEventRegistry.addStack(key, () -> new ItemStack(ItemUtil.fromId(itemId)));
    }

    private final MCPLRegistry mcplr;

    public MCPLRegistry1_20(MCPLRegistry mcplr, String MOD_ID) {
        this.mcplr = mcplr;
    }

    public void register() {

    }

    public RegistrySupplier<ItemGroup> registryItemGroup(Identifier id, Supplier<ItemGroup> supplier) {
        RegistrySupplier<ItemGroup> itemGroup = Registry.registryItemGroup(id, supplier);
        REGISTRY_SUPPLIER_ITEM_GROUP_CACHE.put(id, itemGroup);
        return itemGroup;
    }

    public RegistrySupplier<ItemGroup> registryItemGroup(Identifier id, CreativeTabBuilder builder) {
        return registryItemGroup(id, builder::build);
    }
}
