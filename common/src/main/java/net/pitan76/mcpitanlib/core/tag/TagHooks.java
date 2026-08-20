package net.pitan76.mcpitanlib.core.tag;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.EntityTypeTags;
import net.minecraft.tag.ItemTags;
import net.minecraft.tag.ServerTagManagerHolder;
import net.minecraft.tag.Tag;
import net.minecraft.tag.TagGroup;
import net.minecraft.util.Identifier;

import java.util.function.Supplier;

public class TagHooks {
    public static <T> Tag.Identified<T> getOptional(Identifier id, Supplier<TagGroup<T>> groupSupplier) {
        return new CompatTagDelegate<T>(id, groupSupplier);
    }

    public static Tag.Identified<Block> getBlockOptional(Identifier id) {
        return getOptional(id, new Supplier<TagGroup<Block>>() {
            @Override
            public TagGroup<Block> get() {
                return BlockTags.getTagGroup();
            }
        });
    }

    public static Tag.Identified<Item> getItemOptional(Identifier id) {
        return getOptional(id, new Supplier<TagGroup<Item>>() {
            @Override
            public TagGroup<Item> get() {
                return ItemTags.getTagGroup();
            }
        });
    }

    // FluidTagsのTagGroupを取るメソッドはyarnで名前が付いていないため、TagManagerから取る
    public static Tag.Identified<Fluid> getFluidOptional(Identifier id) {
        return getOptional(id, new Supplier<TagGroup<Fluid>>() {
            @Override
            public TagGroup<Fluid> get() {
                return ServerTagManagerHolder.getTagManager().getFluids();
            }
        });
    }

    public static Tag.Identified<EntityType<?>> getEntityTypeOptional(Identifier id) {
        return getOptional(id, new Supplier<TagGroup<EntityType<?>>>() {
            @Override
            public TagGroup<EntityType<?>> get() {
                return EntityTypeTags.getTagGroup();
            }
        });
    }
}
