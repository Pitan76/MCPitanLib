package net.pitan76.mcpitanlib.api.tag.v2;

import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;

public class CompatTagKeyType<T> {
    public static final CompatTagKeyType<Block> BLOCK = of(Registry.BLOCK_KEY);
    public static final CompatTagKeyType<Item> ITEM = new CompatTagKeyType<>(Registry.ITEM_KEY);
    public static final CompatTagKeyType<Fluid> FLUID = new CompatTagKeyType<>(Registry.FLUID_KEY);
    public static final CompatTagKeyType<EntityType<?>> ENTITY_TYPE = new CompatTagKeyType<>(Registry.ENTITY_TYPE_KEY);
    public static final CompatTagKeyType<BlockEntityType<?>> BLOCK_ENTITY_TYPE = new CompatTagKeyType<>(Registry.BLOCK_ENTITY_TYPE_KEY);
    public static final CompatTagKeyType<ScreenHandlerType<?>> SCREEN_HANDLER = new CompatTagKeyType<>(Registry.MENU_KEY);

    public final CompatIdentifier id;

    protected CompatTagKeyType(CompatIdentifier id) {
        this.id = id;
    }

    public static <T> CompatTagKeyType<T> of(CompatIdentifier id) {
        return new CompatTagKeyType<>(id);
    }

    // RegistryKey
    private RegistryKey<Registry<T>> key;

    protected CompatTagKeyType(RegistryKey<Registry<T>> key) {
        this.id = CompatIdentifier.fromMinecraft(key.method_41185());
        this.key = key;
    }

    public static <T> CompatTagKeyType<T> of(RegistryKey<Registry<T>> key) {
        return new CompatTagKeyType<>(key);
    }

    @Deprecated
    public RegistryKey<Registry<T>> getRegistryKey() {
        return key;
    }
}
