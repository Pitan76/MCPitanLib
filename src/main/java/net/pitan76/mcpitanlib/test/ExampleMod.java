package net.pitan76.mcpitanlib.test;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.screen.ScreenHandlerType;
import net.pitan76.mcpitanlib.api.CommonModInitializer;
import net.pitan76.mcpitanlib.api.block.v2.CompatibleBlockSettings;
import net.pitan76.mcpitanlib.api.block.CompatibleMaterial;
import net.pitan76.mcpitanlib.api.block.v2.CompatBlock;
import net.pitan76.mcpitanlib.api.command.CommandRegistry;
import net.pitan76.mcpitanlib.api.gui.SimpleScreenHandlerTypeBuilder;
import net.pitan76.mcpitanlib.api.item.v2.CompatItem;
import net.pitan76.mcpitanlib.api.item.v2.CompatibleItemSettings;
import net.pitan76.mcpitanlib.api.simple.item.SimpleGuiItem;
import net.pitan76.mcpitanlib.api.registry.result.RegistryResult;
import net.pitan76.mcpitanlib.api.registry.result.SupplierResult;
import net.pitan76.mcpitanlib.api.registry.v2.CompatRegistryV2;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.api.util.item.ItemUtil;
import net.pitan76.mcpitanlib.guilib.GuiRegistry;
import net.pitan76.mcpitanlib.midohra.item.ItemGroups;

public class ExampleMod extends CommonModInitializer {
    public static final String MOD_ID = "examplemod";
    public static final String MOD_NAME = "ExampleMod";

    public static ExampleMod INSTANCE;
    public static CompatRegistryV2 registry;

    public static SupplierResult<ScreenHandlerType<ExampleScreenHandler>> EXAMPLE_SCREENHANDLER;
    public static SupplierResult<ScreenHandlerType<ExampleContainerGui>> EXAMPLE_CONTAINER_GUI;

    public static RegistryResult<Item> EXAMPLE_ITEM;
    public static RegistryResult<Block> EXAMPLE_BLOCK;
    public static RegistryResult<Item> EXAMPLE_BLOCK_ITEM;
    public static RegistryResult<Item> EXAMPLE_GUI_ITEM;
    public static RegistryResult<Item> EXAMPLE_CONTAINER_GUI_ITEM;
    public static RegistryResult<Block> EXAMPLE_GUI_BLOCK;
    public static RegistryResult<Item> EXAMPLE_GUI_BLOCK_ITEM;

    public static CompatIdentifier EXAMPLE_ITEM_ID = _id("example_item");
    public static CompatIdentifier EXAMPLE_BLOCK_ID = _id("example_block");
    public static CompatIdentifier EXAMPLE_GUI_ITEM_ID = _id("example_gui_item");
    public static CompatIdentifier EXAMPLE_GUI_BLOCK_ID = _id("example_gui_block");
    public static CompatIdentifier EXAMPLE_CONTAINER_GUI_ITEM_ID = _id("example_container_gui_item");
    
    
    public void init() {
        INSTANCE = this;
        registry = super.registry;

        // en: Register all items, blocks and others. , ja: 全てのアイテム、ブロック、その他を登録します。
        EXAMPLE_SCREENHANDLER = registry.registerScreenHandlerType(compatId("example_gui"), new SimpleScreenHandlerTypeBuilder<>(ExampleScreenHandler::new));
        EXAMPLE_CONTAINER_GUI = GuiRegistry.register(registry, compatId("example_container_gui"), new SimpleScreenHandlerTypeBuilder<>(ExampleContainerGui::new));

        EXAMPLE_ITEM = registry.registerItem(EXAMPLE_ITEM_ID, () -> new CompatItem(CompatibleItemSettings.of(EXAMPLE_ITEM_ID).addGroup(ItemGroups.INGREDIENTS)));
        EXAMPLE_BLOCK = registry.registerBlock(EXAMPLE_BLOCK_ID, () -> new CompatBlock(CompatibleBlockSettings.of(EXAMPLE_BLOCK_ID, CompatibleMaterial.STONE)));
        EXAMPLE_BLOCK_ITEM = registry.registerItem(EXAMPLE_BLOCK_ID, () -> ItemUtil.create(EXAMPLE_BLOCK.supplier.get(), CompatibleItemSettings.of(EXAMPLE_BLOCK_ID).addGroup(ItemGroups.INGREDIENTS)));

        EXAMPLE_GUI_ITEM = registry.registerItem(EXAMPLE_GUI_ITEM_ID, () -> new ExampleGuiItem(CompatibleItemSettings.of(EXAMPLE_GUI_ITEM_ID).addGroup(ItemGroups.INGREDIENTS)));

        EXAMPLE_GUI_BLOCK = registry.registerBlock(EXAMPLE_GUI_BLOCK_ID, () -> new ExampleGuiBlock(CompatibleBlockSettings.of(EXAMPLE_GUI_BLOCK_ID, CompatibleMaterial.STONE)));
        EXAMPLE_GUI_BLOCK_ITEM = registry.registerItem(EXAMPLE_GUI_BLOCK_ID, () -> ItemUtil.create(EXAMPLE_GUI_BLOCK.supplier.get(), CompatibleItemSettings.of(EXAMPLE_GUI_BLOCK_ID).addGroup(ItemGroups.INGREDIENTS)));

        EXAMPLE_CONTAINER_GUI_ITEM = registry.registerItem(EXAMPLE_CONTAINER_GUI_ITEM_ID,
                () -> new SimpleGuiItem(CompatibleItemSettings.of(EXAMPLE_CONTAINER_GUI_ITEM_ID)
                        .addGroup(ItemGroups.INGREDIENTS),
                        ExampleContainerGui::new)
        );

        // en: Register the command , ja: コマンドを登録します
        CommandRegistry.register("mpla", new ExampleCommand());
    }

    public static CompatIdentifier _id(String id) {
        return CompatIdentifier.of(MOD_ID, id);
    }

    @Override
    public String getId() {
        return MOD_ID;
    }

    @Override
    public String getName() {
        return MOD_NAME;
    }
}
