package net.pitan76.mcpitanlib.mixin;

import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.hit.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

// TODO(Ravel): can not resolve target class ItemUsageContext
@Mixin(ItemUsageContext.class)
public interface ItemUsageContextMixin {
    // TODO(Ravel): Could not determine a single target
    @Accessor
    public BlockHitResult getHit();
}