package net.pitan76.mcpitanlib.api.tag.v2;

import me.shedaniel.architectury.mixin.FluidTagsAccessor;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.tag.*;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;

public class CompatTagKeyType<T> {
    public static final CompatTagKeyType<Block> BLOCK = of("block", BlockTags.getTagGroup());
    public static final CompatTagKeyType<Item> ITEM = of("item", ItemTags.getTagGroup());
    public static final CompatTagKeyType<Fluid> FLUID = of("fluid", FluidTagsAccessor.getHelper().getGroup());
    public static final CompatTagKeyType<EntityType<?>> ENTITY_TYPE = of("entity_type", EntityTypeTags.getTagGroup());
    public static final CompatTagKeyType<BlockEntityType<?>> BLOCK_ENTITY_TYPE = of("block_entity_type", TagGroup.createEmpty());
    public static final CompatTagKeyType<ScreenHandlerType<?>> SCREEN_HANDLER = of("screen_handler", TagGroup.createEmpty());

    public final CompatIdentifier id;

    protected CompatTagKeyType(CompatIdentifier id) {
        this.id = id;
    }

    public static <T> CompatTagKeyType<T> of(CompatIdentifier id) {
        return new CompatTagKeyType<>(id);
    }

    private TagGroup<T> tagGroup;

    protected CompatTagKeyType(CompatIdentifier id, TagGroup<T> tagGroup) {
        this.id = id;
        this.tagGroup = tagGroup;
    }

    protected static <T> CompatTagKeyType<T> of(String id, TagGroup<T> tagGroup) {
        return new CompatTagKeyType<>(CompatIdentifier.of(id), tagGroup);
    }

    @Deprecated
    public TagGroup<T> getTagGroup() {
        return tagGroup;
    }

    // RegistryKey
    private RegistryKey<Registry<T>> key;

    protected CompatTagKeyType(RegistryKey<Registry<T>> key) {
        this.id = CompatIdentifier.fromMinecraft(key.getValue());
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
