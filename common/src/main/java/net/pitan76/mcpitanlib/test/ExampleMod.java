package net.pitan76.mcpitanlib.test;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.screen.ScreenHandlerType;
import net.pitan76.mcpitanlib.api.CommonModInitializer;
import net.pitan76.mcpitanlib.api.block.CompatibleBlockSettings;
import net.pitan76.mcpitanlib.api.block.CompatibleMaterial;
import net.pitan76.mcpitanlib.api.block.ExtendBlock;
import net.pitan76.mcpitanlib.api.command.CommandRegistry;
import net.pitan76.mcpitanlib.api.gui.SimpleScreenHandlerTypeBuilder;
import net.pitan76.mcpitanlib.api.item.CompatibleItemSettings;
import net.pitan76.mcpitanlib.api.item.DefaultItemGroups;
import net.pitan76.mcpitanlib.api.item.ExtendItem;
import net.pitan76.mcpitanlib.api.simple.item.SimpleGuiItem;
import net.pitan76.mcpitanlib.api.registry.result.RegistryResult;
import net.pitan76.mcpitanlib.api.registry.result.SupplierResult;
import net.pitan76.mcpitanlib.api.registry.v2.CompatRegistryV2;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.api.util.ItemUtil;
import net.pitan76.mcpitanlib.guilib.GuiRegistry;

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

    public void init() {
        INSTANCE = this;
        registry = super.registry;

        // en: Register all items, blocks and others. , ja: 全てのアイテム、ブロック、その他を登録します。
        EXAMPLE_SCREENHANDLER = registry.registerScreenHandlerType(compatId("example_gui"), new SimpleScreenHandlerTypeBuilder<>(ExampleScreenHandler::new));
        EXAMPLE_CONTAINER_GUI = GuiRegistry.register(registry, compatId("example_container_gui"), new SimpleScreenHandlerTypeBuilder<>(ExampleContainerGui::new));

        EXAMPLE_ITEM = registry.registerItem(compatId("example_item"), () -> new ExtendItem(new CompatibleItemSettings().addGroup(DefaultItemGroups.INGREDIENTS, compatId("example_item"))));
        EXAMPLE_BLOCK = registry.registerBlock(compatId("example_block"), () -> new ExtendBlock(CompatibleBlockSettings.of(CompatibleMaterial.STONE)));
        EXAMPLE_BLOCK_ITEM = registry.registerItem(compatId("example_block"), () -> ItemUtil.ofBlock(EXAMPLE_BLOCK.supplier.get(), new CompatibleItemSettings().addGroup(DefaultItemGroups.INGREDIENTS, compatId("example_block"))));

        EXAMPLE_GUI_ITEM = registry.registerItem(compatId("example_gui_item"), () -> new ExampleGuiItem(new CompatibleItemSettings().addGroup(DefaultItemGroups.INGREDIENTS, compatId("example_gui_item"))));

        EXAMPLE_GUI_BLOCK = registry.registerBlock(compatId("example_gui_block"), () -> new ExampleGuiBlock(CompatibleBlockSettings.of(CompatibleMaterial.STONE).build()));
        EXAMPLE_GUI_BLOCK_ITEM = registry.registerItem(compatId("example_gui_block"), () -> ItemUtil.ofBlock(EXAMPLE_GUI_BLOCK.supplier.get(), new CompatibleItemSettings().addGroup(() -> DefaultItemGroups.INGREDIENTS, compatId("example_gui_block"))));

        EXAMPLE_CONTAINER_GUI_ITEM = registry.registerItem(compatId("example_container_gui_item"),
                () -> new SimpleGuiItem(CompatibleItemSettings.of()
                        .addGroup(DefaultItemGroups.INGREDIENTS, compatId("example_container_gui_item")),
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
