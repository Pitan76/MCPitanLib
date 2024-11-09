package net.pitan76.mcpitanlib.api.tag.v2;

import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.screen.ScreenHandlerType;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;

public class CompatTagKeyType<T> {
    public static final CompatTagKeyType<Block> BLOCK = of(RegistryKeys.BLOCK);
    public static final CompatTagKeyType<Item> ITEM = new CompatTagKeyType<>(RegistryKeys.ITEM);
    public static final CompatTagKeyType<Fluid> FLUID = new CompatTagKeyType<>(RegistryKeys.FLUID);
    public static final CompatTagKeyType<EntityType<?>> ENTITY_TYPE = new CompatTagKeyType<>(RegistryKeys.ENTITY_TYPE);
    public static final CompatTagKeyType<BlockEntityType<?>> BLOCK_ENTITY_TYPE = new CompatTagKeyType<>(RegistryKeys.BLOCK_ENTITY_TYPE);
    public static final CompatTagKeyType<ScreenHandlerType<?>> SCREEN_HANDLER = new CompatTagKeyType<>(RegistryKeys.SCREEN_HANDLER);

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
        this.id = CompatIdentifier.fromMinecraft(key.getRegistry());
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
