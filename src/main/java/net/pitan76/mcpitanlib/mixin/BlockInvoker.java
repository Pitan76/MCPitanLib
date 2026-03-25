package net.pitan76.mcpitanlib.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

// TODO(Ravel): can not resolve target class Block
@Mixin(Block.class)
public interface BlockInvoker {
    // TODO(Ravel): Could not determine a single target
    @Invoker("setDefaultState")
    void setDefaultState_invoke(BlockState state);
}
