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
import net.pitan76.mcpitanlib.api.entity.effect.StatusEffectBuilder;
import net.pitan76.mcpitanlib.api.enchantment.EnchantmentBuilder;
import net.pitan76.mcpitanlib.api.potion.BrewingRecipeUtil;
import net.pitan76.mcpitanlib.api.potion.PotionBuilder;
import net.pitan76.mcpitanlib.midohra.enchantment.EnchantmentWrapper;
import net.pitan76.mcpitanlib.midohra.entity.effect.SupplierStatusEffectWrapper;
import net.pitan76.mcpitanlib.api.text.TextComponent;
import net.pitan76.mcpitanlib.api.util.CompatRarity;
import net.pitan76.mcpitanlib.midohra.easybuilder.ItemBuilder;
import net.pitan76.mcpitanlib.midohra.item.ItemGroups;
import net.pitan76.mcpitanlib.midohra.item.SupplierItemWrapper;
import net.pitan76.mcpitanlib.midohra.registry.MidohraRegistryV2;
import net.pitan76.mcpitanlib.midohra.item.ItemWrapper;
import net.pitan76.mcpitanlib.midohra.potion.SupplierPotionWrapper;

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

    public static MidohraRegistryV2 registry2;
    public static SupplierItemWrapper EXAMPLE_BUILDER_ITEM;

    public static SupplierStatusEffectWrapper EXAMPLE_EFFECT;
    public static SupplierPotionWrapper EXAMPLE_POTION;
    public static EnchantmentWrapper EXAMPLE_ENCHANTMENT;

    public void init() {
        INSTANCE = this;
        registry = super.registry;
        registry2 = MidohraRegistryV2.of(registry);

        // en: Register an item with the builder. , ja: builderでアイテムを登録します。
        EXAMPLE_BUILDER_ITEM = registry2.registerItem(ItemBuilder.of(_id("example_builder_item"))
                .maxCount(16)
                .rarity(CompatRarity.RARE)
                .group(ItemGroups.INGREDIENTS)
                .addTooltip(TextComponent.literal("Built with ItemBuilder")));

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

        // en: Register a status effect that teleports the target when applied.
        // ja: 付与された瞬間に対象をテレポートさせる効果を登録します。
        EXAMPLE_EFFECT = StatusEffectBuilder.of(_id("example_effect"))
                .beneficial()
                .color(0x8A2BE2)
                .instant()
                .onInstantApplied(e -> {
                    double range = 8 * (e.getLevel());
                    double x = e.getTarget().getX() + (e.getServerWorld().getRandom().nextDouble() - 0.5) * range * 2;
                    double z = e.getTarget().getZ() + (e.getServerWorld().getRandom().nextDouble() - 0.5) * range * 2;
                    double y = e.getServerWorld().getTopY(net.minecraft.world.Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, (int) x, (int) z);
                    e.getTarget().requestTeleport(x, y, z);
                })
                .build(registry);

        EXAMPLE_POTION = PotionBuilder.of(_id("example_potion"))
                .effect(EXAMPLE_EFFECT, 1, 0)
                .build(registry);

        // en: water bottle + ender pearl -> example potion , ja: 水入り瓶 + エンダーパール -> ポーション
        BrewingRecipeUtil.registerPotionRecipe(
                net.pitan76.mcpitanlib.midohra.potion.PotionWrapper.of(CompatIdentifier.of("minecraft", "water")),
                ItemWrapper.of(CompatIdentifier.of("minecraft", "ender_pearl")),
                EXAMPLE_POTION);

        // en: Register an enchantment through the virtual datapack.
        // ja: 仮想データパック経由でエンチャントを登録します。
        // en: Freezes the target on hit. , ja: 攻撃を当てた相手を凍結させます。
        EXAMPLE_ENCHANTMENT = EnchantmentBuilder.of(_id("example_enchantment"))
                .supportedItems("#minecraft:enchantable/weapon")
                .weight(2)
                .maxLevel(3)
                .anvilCost(2)
                .minCost(5, 8)
                .maxCost(25, 8)
                .mainhand()
                .onPostAttack(e -> e.getTargetWrapper().addFrozenTicks(100 * e.getLevel()))
                .build(registry);

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
