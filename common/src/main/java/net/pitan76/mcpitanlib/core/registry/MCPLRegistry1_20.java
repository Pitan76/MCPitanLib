package net.pitan76.mcpitanlib.core.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.Identifier;
import net.pitan76.mcpitanlib.api.item.CreativeTabBuilder;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.api.util.item.ItemUtil;
import net.pitan76.mcpitanlib.core.mc261.CreativeModeTabEventRegistry;
import net.pitan76.mcpitanlib.api.registry.result.RegistrySupplier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

@Deprecated
public class MCPLRegistry1_20 {
    @Deprecated
    public static final Map<ResourceKey<CreativeModeTab>, RegistrySupplier<CreativeModeTab>> REGISTRY_SUPPLIER_ITEM_GROUP_CACHE = new HashMap<>();

    @Deprecated
    public static final Map<ResourceKey<CreativeModeTab>, List<Identifier>> ITEM_GROUP_ITEM_ID_CACHE = new HashMap<>();

    public static void addItemGroupItem(ResourceKey<CreativeModeTab> itemGroup, Identifier itemId) {
        if (!ITEM_GROUP_ITEM_ID_CACHE.containsKey(itemGroup)) {
            ITEM_GROUP_ITEM_ID_CACHE.put(itemGroup, new ArrayList<>());
        }

        ITEM_GROUP_ITEM_ID_CACHE.get(itemGroup).add(itemId);

        // allRegister()より後にアイテムが生成されるプラットフォームがあるため、ここで即時登録する
        CreativeModeTabEventRegistry.addStack(itemGroup, () -> new ItemStack(ItemUtil.fromId(CompatIdentifier.fromMinecraft(itemId))));
    }

    private final MCPLRegistry mcplr;

    public MCPLRegistry1_20(MCPLRegistry mcplr, String MOD_ID) {
        this.mcplr = mcplr;
    }

    public void register() {

    }

    public RegistrySupplier<CreativeModeTab> registryItemGroup(Identifier id, Supplier<CreativeModeTab> supplier) {
        ResourceKey<CreativeModeTab> key = ResourceKey.create(Registries.CREATIVE_MODE_TAB, id);
        RegistrySupplier<CreativeModeTab> itemGroup = Registry.registryItemGroup(id, supplier);
//        RegistrySupplier<CreativeModeTab> itemGroup = ITEM_GROUP.register(id, supplier);
        REGISTRY_SUPPLIER_ITEM_GROUP_CACHE.put(key, itemGroup);
        return itemGroup;
    }

    public RegistrySupplier<CreativeModeTab> registryItemGroup(Identifier id, CreativeTabBuilder builder) {
        ResourceKey<CreativeModeTab> key = ResourceKey.create(Registries.CREATIVE_MODE_TAB, id);
        RegistrySupplier<CreativeModeTab> itemGroup = Registry.registryItemGroup(id, builder::build);
//                new RegistrySupplier<>(Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, key, builder.build()));
//        RegistrySupplier<CreativeModeTab> itemGroup = ITEM_GROUP.register(id, builder::build);
        REGISTRY_SUPPLIER_ITEM_GROUP_CACHE.put(key, itemGroup);
        return itemGroup;
    }
}
