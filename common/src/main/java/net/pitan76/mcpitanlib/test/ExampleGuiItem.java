package net.pitan76.mcpitanlib.test;

import net.pitan76.mcpitanlib.api.util.TextUtil;
import net.pitan76.mcpitanlib.api.event.item.ItemUseEvent;
import net.pitan76.mcpitanlib.api.item.CompatibleItemSettings;
import net.pitan76.mcpitanlib.api.item.ExtendItem;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.util.TypedActionResult;

public class ExampleGuiItem extends ExtendItem {

    public ExampleGuiItem(CompatibleItemSettings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> onRightClick(ItemUseEvent event) {
        if (!event.world.isClient()) {
            event.user.openGuiScreen(getFactory());
        }
        return super.onRightClick(event);
    }

    public static NamedScreenHandlerFactory getFactory() {
        return new SimpleNamedScreenHandlerFactory((i, playerInventory, playerEntity) -> new ExampleScreenHandler(i, playerInventory), TextUtil.literal("Example Title"));
    }
}
