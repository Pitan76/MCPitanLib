package net.pitan76.mcpitanlib.midohra.item;

import net.minecraft.item.BlockItem;
import net.minecraft.block.Block;
import net.pitan76.mcpitanlib.api.registry.result.RegistryResult;
import net.pitan76.mcpitanlib.midohra.block.SupplierTypedBlockWrapper;
import net.pitan76.mcpitanlib.midohra.core.INonTypedSupplier;

import net.pitan76.mcpitanlib.api.registry.result.RegistrySupplier;

import java.util.function.Supplier;

public class SupplierTypedBlockItemWrapper<T extends Block> extends TypedBlockItemWrapper<T> implements INonTypedSupplier<SupplierItemWrapper> {
    private final Supplier<T> supplier;

    protected SupplierTypedBlockItemWrapper(Supplier<T> supplier) {
        super(null);
        this.supplier = supplier;
    }

    public static <T extends Block> SupplierTypedBlockItemWrapper<T> of(ItemWrapper wrapper) {
        if (wrapper instanceof INonTypedSupplier) {
            return SupplierTypedBlockItemWrapper.of(((INonTypedSupplier<SupplierItemWrapper>) wrapper));
        }

        return SupplierTypedBlockItemWrapper.of(() -> (T) wrapper.asBlock().get());
    }

    public static <T extends Block> SupplierTypedBlockItemWrapper<T> of(Supplier<T> supplier) {
        return new SupplierTypedBlockItemWrapper<>(supplier);
    }

    public static <T extends Block> SupplierTypedBlockItemWrapper<T> of(RegistryResult<T> result) {
        return new SupplierTypedBlockItemWrapper<>(result::get);
    }

    public static <T extends Block> SupplierTypedBlockItemWrapper<T> of(RegistrySupplier<T> result) {
        return new SupplierTypedBlockItemWrapper<>(result::get);
    }

    public static <T extends Block> SupplierTypedBlockItemWrapper<T> of(SupplierItemWrapper result) {
        return new SupplierTypedBlockItemWrapper<>(() -> (T) ((BlockItem) result.get()).getBlock());
    }

    public static <T extends Block> SupplierTypedBlockItemWrapper<T> of(INonTypedSupplier<SupplierItemWrapper> result) {
        return new SupplierTypedBlockItemWrapper<>(() -> (T) ((BlockItem) result.asNonTyped().get()).getBlock());
    }

    @Override
    public SupplierItemWrapper asNonTyped() {
        return SupplierItemWrapper.of(() -> get().asItem());
    }

    @Override
    public SupplierTypedBlockWrapper<T> asBlock() {
        return SupplierTypedBlockWrapper.of(supplier);
    }

    @Override
    public BlockItem get() {
        return (BlockItem) supplier.get().asItem();
    }
}
