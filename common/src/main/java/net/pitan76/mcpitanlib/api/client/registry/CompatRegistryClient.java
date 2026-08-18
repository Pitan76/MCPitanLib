package net.pitan76.mcpitanlib.api.client.registry;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.color.block.BlockColorProvider;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.ScreenHandlerProvider;
import net.minecraft.client.item.ItemModelManager;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.render.BlockRenderLayer;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.LoadedEntityModels;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.fluid.Fluid;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.random.Random;
import net.pitan76.mcpitanlib.MCPitanLib;
import net.pitan76.mcpitanlib.api.client.color.CompatBlockColorProvider;
import net.pitan76.mcpitanlib.api.client.gui.screen.SimpleHandledScreen;
import net.pitan76.mcpitanlib.api.client.render.CompatRenderLayer;
import net.pitan76.mcpitanlib.api.client.render.EntityModelLayerContext;
import net.pitan76.mcpitanlib.api.gui.SimpleScreenHandler;
import net.pitan76.mcpitanlib.api.text.TextComponent;
import net.pitan76.mcpitanlib.api.util.inventory.CompatPlayerInventory;

import java.util.List;
import java.util.function.Supplier;

@Environment(EnvType.CLIENT)
public class CompatRegistryClient {
    public static <H extends ScreenHandler, S extends Screen & ScreenHandlerProvider<H>> void registerScreen(ScreenHandlerType<? extends H> type, ScreenFactory<H, S> factory) {
        registerScreen(MCPitanLib.MOD_ID, type, factory);
    }

    public static <H extends SimpleScreenHandler, S extends SimpleHandledScreen<H> & ScreenHandlerProvider<H>> void registerScreen(ScreenHandlerType<? extends H> type, ScreenFactory2<H, S> factory) {
        registerScreen(MCPitanLib.MOD_ID, type, factory);
    }

    public static <H extends ScreenHandler, S extends Screen & ScreenHandlerProvider<H>> void registerScreen(String modId, ScreenHandlerType<? extends H> type, ScreenFactory<H, S> factory) {
        registerScreen(modId, () -> type, factory);
    }

    /**
     * NeoForgeではScreenHandlerTypeの生成が遅延されるため、登録時点ではnullのことがある。
     * 実際に使うタイミングまで解決を遅らせるためSupplierで受け取る。
     */
    @ExpectPlatform
    public static <H extends ScreenHandler, S extends Screen & ScreenHandlerProvider<H>> void registerScreen(String modId, Supplier<ScreenHandlerType<? extends H>> type, ScreenFactory<H, S> factory) {
        throw new AssertionError();
    }

    public static <H extends SimpleScreenHandler, S extends SimpleHandledScreen<H> & ScreenHandlerProvider<H>> void registerScreen(String modId, ScreenHandlerType<? extends H> type, ScreenFactory2<H, S> factory) {
        registerScreen(modId, type, (ScreenFactory<H, S>) factory);
    }

    public static <H extends SimpleScreenHandler, S extends SimpleHandledScreen<H> & ScreenHandlerProvider<H>> void registerScreen(String modId, Supplier<ScreenHandlerType<? extends H>> type, ScreenFactory2<H, S> factory) {
        registerScreen(modId, type, (ScreenFactory<H, S>) factory);
    }

    public interface ScreenFactory<H extends ScreenHandler, S extends Screen & ScreenHandlerProvider<H>> {
        S create(H handler, PlayerInventory inventory, Text text);
    }

    public interface ScreenFactory2<H extends SimpleScreenHandler, S extends SimpleHandledScreen<H> & ScreenHandlerProvider<H>> extends ScreenFactory<H, S> {
        @Override
        default S create(H handler, PlayerInventory inventory, Text text) {
            return create(handler, new CompatPlayerInventory(inventory), new TextComponent(text));
        }

        S create(H handler, CompatPlayerInventory inventory, TextComponent text);
    }

    public static <T extends ParticleEffect> void registerParticle(ParticleType<T> type, ParticleFactory<T> factory) {
        registerParticle(() -> type, factory);
    }

    /**
     * NeoForgeでは登録が遅延されるため、呼び出し時点ではまだ生成されていないことがある。
     * 実際に使うタイミングまで解決を遅らせるためSupplierで受け取る。
     */
    @ExpectPlatform
    public static <T extends ParticleEffect> void registerParticle(Supplier<ParticleType<T>> type, ParticleFactory<T> factory) {
        throw new AssertionError();
    }

    public static <T extends ParticleEffect> void registerParticle(ParticleType<T> type, DeferredParticleProvider<T> provider) {
        registerParticle(() -> type, provider);
    }

    /**
     * NeoForgeでは登録が遅延されるため、呼び出し時点ではまだ生成されていないことがある。
     * 実際に使うタイミングまで解決を遅らせるためSupplierで受け取る。
     */
    @ExpectPlatform
    public static <T extends ParticleEffect> void registerParticle(Supplier<ParticleType<T>> type, DeferredParticleProvider<T> provider) {
        throw new AssertionError();
    }

    public static void registerEntityModelLayer(EntityModelLayer layer, EntityModelLayerContext context) {
        registerEntityModelLayer(layer, () -> TexturedModelData.of(context.getData(), context.getWidth(), context.getHeight()));
    }

    @ExpectPlatform
    public static void registerEntityModelLayer(EntityModelLayer layer, Supplier<TexturedModelData> supplier) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static <T extends Entity> void registerEntityRenderer(Supplier<? extends EntityType<? extends T>> type, EntityRendererFactory<T> provider) {
        throw new AssertionError();
    }

    @FunctionalInterface
    public interface DeferredParticleProvider<T extends ParticleEffect> {
        ParticleFactory<T> create(ExtendedSpriteSet spriteSet);
    }

    public interface ExtendedSpriteSet extends SpriteProvider {
        SpriteAtlasTexture getAtlas();

        List<Sprite> getSprites();
    }

    public static void registryClientSpriteAtlasTexture(Identifier identifier) {
        //registryClientSprite(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE, identifier);
    }

    public static void registryClientSpriteAtlasTexture(Sprite sprite) {
        //registryClientSprite(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE, sprite);
    }

    public static void registryClientSprite(Identifier atlasId, Identifier identifier) {
        // ～1.19.2
    }

    public static void registryClientSprite(Identifier atlasId, Sprite sprite) {
        // ～1.19.2
    }

    public static <T extends BlockEntity> void registerBlockEntityRenderer(BlockEntityType<T> type, BlockEntityRendererFactory<T> provider) {
        registerBlockEntityRenderer(() -> type, provider);
    }

    /**
     * NeoForgeでは登録が遅延されるため、呼び出し時点ではまだ生成されていないことがある。
     * 実際に使うタイミングまで解決を遅らせるためSupplierで受け取る。
     */
    public static <T extends BlockEntity> void registerBlockEntityRenderer(Supplier<BlockEntityType<T>> type, BlockEntityRendererFactory<T> provider) {
        registerBlockEntityRendererRaw(type, ctx -> provider.create(new BlockEntityRendererFactory.Context(
                ctx.getRenderDispatcher(), ctx.getRenderManager(), ctx.getItemModelManager(), ctx.getItemRenderer(), ctx.getEntityRenderDispatcher(), ctx.getLoadedEntityModels(), ctx.getTextRenderer()
        )));
    }

    @FunctionalInterface
    public interface BlockEntityRendererFactory<T extends BlockEntity> {
        BlockEntityRenderer<T> create(Context ctx);

        class Context {
            private final BlockEntityRenderDispatcher renderDispatcher;
            private final BlockRenderManager renderManager;
            private final ItemModelManager itemModelManager;
            private final ItemRenderer itemRenderer;
            private final EntityRenderDispatcher entityRenderDispatcher;
            private final LoadedEntityModels layerRenderDispatcher;
            private final TextRenderer textRenderer;

            public Context(BlockEntityRenderDispatcher renderDispatcher, BlockRenderManager renderManager, ItemModelManager itemModelManager, ItemRenderer itemRenderer, EntityRenderDispatcher entityRenderDispatcher, LoadedEntityModels layerRenderDispatcher, TextRenderer textRenderer) {
                this.renderDispatcher = renderDispatcher;
                this.renderManager = renderManager;
                this.itemModelManager = itemModelManager;
                this.itemRenderer = itemRenderer;
                this.entityRenderDispatcher = entityRenderDispatcher;
                this.layerRenderDispatcher = layerRenderDispatcher;
                this.textRenderer = textRenderer;
            }

            public BlockEntityRenderDispatcher getRenderDispatcher() {
                return this.renderDispatcher;
            }

            public BlockRenderManager getRenderManager() {
                return this.renderManager;
            }

            public EntityRenderDispatcher getEntityRenderDispatcher() {
                return this.entityRenderDispatcher;
            }

            public ItemModelManager getItemModelManager() {
                return itemModelManager;
            }

            public ItemRenderer getItemRenderer() {
                return this.itemRenderer;
            }

            public LoadedEntityModels getLayerRenderDispatcher() {
                return this.layerRenderDispatcher;
            }

            public ModelPart getLayerModelPart(EntityModelLayer modelLayer) {
                return this.layerRenderDispatcher.getModelPart(modelLayer);
            }

            public TextRenderer getTextRenderer() {
                return this.textRenderer;
            }
        }
    }


    public static <T extends BlockEntity, S extends BlockEntityRenderState> void registerBlockEntityRendererRaw(BlockEntityType<T> type, net.minecraft.client.render.block.entity.BlockEntityRendererFactory<T, S> factory) {
        registerBlockEntityRendererRaw(() -> type, factory);
    }

    /**
     * NeoForgeでは登録が遅延されるため、呼び出し時点ではまだ生成されていないことがある。
     * 実際に使うタイミングまで解決を遅らせるためSupplierで受け取る。
     */
    @ExpectPlatform
    public static <T extends BlockEntity, S extends BlockEntityRenderState> void registerBlockEntityRendererRaw(Supplier<BlockEntityType<T>> type, net.minecraft.client.render.block.entity.BlockEntityRendererFactory<T, S> factory) {
        throw new AssertionError();
    }

    public static void registerRenderTypeBlock(RenderLayer layer, Block block) {
        BlockRenderLayer blockRenderLayer = null;
        if (layer == RenderLayer.getCutout()) {
            blockRenderLayer = BlockRenderLayer.CUTOUT;
        } else if (layer == RenderLayer.getGlintTranslucent()) {
            blockRenderLayer = BlockRenderLayer.TRANSLUCENT;
        } else if (layer == RenderLayer.getSolid()) {
            blockRenderLayer = BlockRenderLayer.SOLID;
        } else if (layer == RenderLayer.getTripwire()) {
            blockRenderLayer = BlockRenderLayer.TRIPWIRE;
        } else if (layer == RenderLayer.getCutoutMipped()) {
            blockRenderLayer = BlockRenderLayer.CUTOUT_MIPPED;
        }

        if (blockRenderLayer == null) return;

        registerRenderLayerBlock(blockRenderLayer, block);
    }

    public static void registerRenderTypeFluid(RenderLayer layer, Fluid fluid) {
        BlockRenderLayer blockRenderLayer = null;
        if (layer == RenderLayer.getCutout()) {
            blockRenderLayer = BlockRenderLayer.CUTOUT;
        } else if (layer == RenderLayer.getGlintTranslucent()) {
            blockRenderLayer = BlockRenderLayer.TRANSLUCENT;
        } else if (layer == RenderLayer.getSolid()) {
            blockRenderLayer = BlockRenderLayer.SOLID;
        } else if (layer == RenderLayer.getTripwire()) {
            blockRenderLayer = BlockRenderLayer.TRIPWIRE;
        } else if (layer == RenderLayer.getCutoutMipped()) {
            blockRenderLayer = BlockRenderLayer.CUTOUT_MIPPED;
        }

        if (blockRenderLayer == null) return;

        registerRenderLayerFluid(blockRenderLayer, fluid);
    }

    /**
     * 1.21.9以降、ブロックのレンダーレイヤーはモデルJSONの render_type で決まるため、
     * プラットフォームによっては何もしない。
     */
    @ExpectPlatform
    public static void registerRenderLayerBlock(BlockRenderLayer layer, Block block) {

    }

    @ExpectPlatform
    public static void registerRenderLayerFluid(BlockRenderLayer layer, Fluid fluid) {

    }

    public static void registerCutoutBlock(Block block) {
        registerRenderTypeBlock(RenderLayer.getCutout(), block);
    }

    public static <T extends BlockEntity> void registerCompatBlockEntityRenderer(BlockEntityType<T> type, BlockEntityRendererFactory<T> provider) {
        registerCompatBlockEntityRenderer(() -> type, provider);
    }

    /**
     * NeoForgeでは登録が遅延されるため、呼び出し時点ではまだ生成されていないことがある。
     * 実際に使うタイミングまで解決を遅らせるためSupplierで受け取る。
     */
    public static <T extends BlockEntity> void registerCompatBlockEntityRenderer(Supplier<BlockEntityType<T>> type, BlockEntityRendererFactory<T> provider) {
        registerBlockEntityRendererRaw(type, ctx -> provider.create(new BlockEntityRendererFactory.Context(
                ctx.getRenderDispatcher(), ctx.getRenderManager(), ctx.getItemModelManager(), ctx.getItemRenderer(), ctx.getEntityRenderDispatcher(), ctx.getLoadedEntityModels(), ctx.getTextRenderer()
        )));
    }

    public static void registerRenderTypeBlock(CompatRenderLayer layer, Block block) {
        registerRenderTypeBlock(layer.layer, block);
    }

    public static void registerRenderTypeFluid(CompatRenderLayer layer, Fluid fluid) {
        registerRenderTypeFluid(layer.layer, fluid);
    }

    public static void registerColorProviderBlock(BlockColorProvider provider, Block... blocks) {
        registerColorProviderBlock(provider, () -> blocks);
    }

    /**
     * NeoForgeでは登録が遅延されるため、呼び出し時点ではまだ生成されていないことがある。
     * 実際に使うタイミングまで解決を遅らせるためSupplierで受け取る。
     */
    @ExpectPlatform
    public static void registerColorProviderBlock(BlockColorProvider provider, Supplier<Block[]> blocks) {
        throw new AssertionError("This method should be replaced by the platform implementation");
    }

    public static void registerColorProviderBlock(CompatBlockColorProvider provider, Block... blocks) {
        registerColorProviderBlock((BlockColorProvider) provider, blocks);
    }
}
