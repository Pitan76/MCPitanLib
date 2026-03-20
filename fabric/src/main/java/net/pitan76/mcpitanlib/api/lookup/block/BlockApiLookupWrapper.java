package net.pitan76.mcpitanlib.api.lookup.block;

import net.fabricmc.fabric.api.lookup.v1.block.BlockApiLookup;
import net.fabricmc.fabric.api.lookup.v1.custom.ApiLookupMap;
import net.fabricmc.fabric.impl.lookup.block.BlockApiLookupImpl;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.midohra.block.BlockState;
import net.pitan76.mcpitanlib.midohra.block.entity.BlockEntityTypeWrapper;
import net.pitan76.mcpitanlib.midohra.block.entity.BlockEntityWrapper;
import net.pitan76.mcpitanlib.midohra.util.math.BlockPos;
import net.pitan76.mcpitanlib.midohra.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;

public class BlockApiLookupWrapper<A, C> {

    private final BlockApiLookup<@NotNull A, C> lookup;

    public BlockApiLookupWrapper(BlockApiLookup<@NotNull A, C> lookup) {
        this.lookup = lookup;
    }

    public BlockApiLookup<@NotNull A, C> getRaw() {
        return lookup;
    }

    public static <A, C> BlockApiLookupWrapper<A, C> of(BlockApiLookup<@NotNull A, C> lookup) {
        return new BlockApiLookupWrapper<>(lookup);
    }

    public @Nullable A find(World world, BlockPos pos, C context) {
        return lookup.find(world.getRaw(), pos.toMinecraft(), context);
    }

    public @Nullable A find(World world, BlockPos pos, BlockState state, BlockEntity blockEntity, C context) {
        return lookup.find(world.getRaw(), pos.toMinecraft(), state.toMinecraft(), blockEntity, context);
    }

    public @Nullable A find(World world, BlockPos pos, BlockState state, BlockEntityWrapper blockEntity, C context) {
        return lookup.find(world.getRaw(), pos.toMinecraft(), state.toMinecraft(), blockEntity.get(), context);
    }

    public <T extends BlockEntity> void registerForBlockEntity(BiFunction<? super T, C, @Nullable A> provider, BlockEntityType<T> blockEntityType) {
        lookup.registerForBlockEntity(provider, blockEntityType);
    }

    public void registerForBlockEntityWrapper(BiFunction<BlockEntityWrapper, C, @Nullable A> provider, BlockEntityTypeWrapper blockEntityWrapperType) {
        lookup.registerForBlockEntity((blockEntity, context) ->
                provider.apply(BlockEntityWrapper.of(blockEntity), context), blockEntityWrapperType.get());
    }

    public CompatIdentifier getId() {
        try {
            Field lookupsField = BlockApiLookupImpl.class.getDeclaredField("LOOKUPS");
            lookupsField.setAccessible(true);
            ApiLookupMap<BlockApiLookup<?, ?>> lookups = (ApiLookupMap<BlockApiLookup<?, ?>>) lookupsField.get(null);

            for (Field f : lookups.getClass().getDeclaredFields()) {
                f.setAccessible(true);
                Object value = f.get(lookups);
                if (value instanceof Map) {
                    Map<?, ?> map = (Map<?, ?>) value;
                    for (Map.Entry<?, ?> entry : map.entrySet()) {
                        Object key = entry.getKey();
                        Object val = entry.getValue();
                        if (val == lookup || (val != null && val.equals(lookup))) {
                            if (key instanceof Identifier) {
                                return CompatIdentifier.fromMinecraft((Identifier) key);
                            } else if (key instanceof String) {
                                return CompatIdentifier.of((String) key);
                            } else if (key != null) {
                                return CompatIdentifier.of(key.toString());
                            }
                        }
                    }
                }
            }

            try {
                for (Object entry : lookups) {
                    if (entry instanceof Map.Entry) {
                        Map.Entry<?, ?> e = (Map.Entry<?, ?>) entry;
                        Object val = e.getValue();
                        if (Objects.equals(val, lookup)) {
                            Object key = e.getKey();
                            if (key instanceof Identifier) return CompatIdentifier.fromMinecraft((Identifier) key);
                            if (key instanceof String) return CompatIdentifier.of((String) key);
                            if (key != null) return CompatIdentifier.of(key.toString());
                        }
                    }

                    if (Objects.equals(entry, lookup)) {
                        for (Field ef : entry.getClass().getDeclaredFields()) {
                            ef.setAccessible(true);
                            Object idObj = ef.get(entry);
                            if (idObj instanceof Identifier) return CompatIdentifier.fromMinecraft((Identifier) idObj);
                            if (idObj instanceof String) return CompatIdentifier.of((String) idObj);
                        }
                    }
                }
            } catch (Throwable ignored) {

            }

        } catch (NoSuchFieldException | IllegalAccessException ignored) {

        }

        return CompatIdentifier.EMPTY;
    }
}