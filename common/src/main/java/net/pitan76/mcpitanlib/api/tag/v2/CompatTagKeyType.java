package net.pitan76.mcpitanlib.api.tag.v2;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.item.Item;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;

public class CompatTagKeyType<T> {
    public static final CompatTagKeyType<Block> BLOCK = of(Registries.BLOCK);
    public static final CompatTagKeyType<Item> ITEM = new CompatTagKeyType<>(Registries.ITEM);
    public static final CompatTagKeyType<Fluid> FLUID = new CompatTagKeyType<>(Registries.FLUID);
    public static final CompatTagKeyType<EntityType<?>> ENTITY_TYPE = new CompatTagKeyType<>(Registries.ENTITY_TYPE);
    public static final CompatTagKeyType<BlockEntityType<?>> BLOCK_ENTITY_TYPE = new CompatTagKeyType<>(Registries.BLOCK_ENTITY_TYPE);
    public static final CompatTagKeyType<MenuType<?>> SCREEN_HANDLER = new CompatTagKeyType<>(Registries.MENU);

    public final CompatIdentifier id;

    protected CompatTagKeyType(CompatIdentifier id) {
        this.id = id;
    }

    public static <T> CompatTagKeyType<T> of(CompatIdentifier id) {
        return new CompatTagKeyType<>(id);
    }

    // RegistryKey
    private ResourceKey<Registry<T>> key;

    protected CompatTagKeyType(ResourceKey<Registry<T>> key) {
        this.id = CompatIdentifier.fromMinecraft(key.registry());
        this.key = key;
    }

    public static <T> CompatTagKeyType<T> of(ResourceKey<Registry<T>> key) {
        return new CompatTagKeyType<>(key);
    }

    @Deprecated
    public ResourceKey<Registry<T>> getRegistryKey() {
        return key;
    }
}
