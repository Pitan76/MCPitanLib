package net.pitan76.mcpitanlib.test;

import net.pitan76.mcpitanlib.api.block.ExtendBlock;
import net.pitan76.mcpitanlib.api.event.block.BlockUseEvent;
import net.pitan76.mcpitanlib.api.util.TextUtil;
import net.minecraft.block.BlockState;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ExampleGuiBlock extends ExtendBlock {

    public ExampleGuiBlock(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onRightClick(BlockUseEvent e) {
        if (e.world.isClient) {
            e.player.openGuiScreen(e.world, e.state, e.pos);
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public NamedScreenHandlerFactory createScreenHandlerFactory(BlockState state, World world, BlockPos pos) {
        return new SimpleNamedScreenHandlerFactory((i, playerInventory, playerEntity) -> new ExampleScreenHandler(i, playerInventory), TextUtil.literal("Example Title"));
    }
}
