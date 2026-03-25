package net.pitan76.mcpitanlib.midohra.block.entity;

import net.minecraft.world.level.block.entity.BlockEntityType;

public class RewritableBlockEntityTypeWrapper extends BlockEntityTypeWrapper {
    private BlockEntityType<?> type;

    protected RewritableBlockEntityTypeWrapper(BlockEntityType<?> type) {
        this.type = type;
    }

    protected RewritableBlockEntityTypeWrapper() {

    }

    public static RewritableBlockEntityTypeWrapper of(BlockEntityType<?> type) {
        return new RewritableBlockEntityTypeWrapper(type);
    }

    public static RewritableBlockEntityTypeWrapper of() {
        return new RewritableBlockEntityTypeWrapper();
    }

    public void set(BlockEntityType<?> type) {
        this.type = type;
    }

    @Override
    public BlockEntityType<?> get() {
        return type;
    }
}
