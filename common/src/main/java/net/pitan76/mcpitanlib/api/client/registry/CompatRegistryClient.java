package net.pitan76.mcpitanlib.api.client.registry;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.color.block.BlockTintSource;
import net.minecraft.client.renderer.block.BlockModelResolver;
import net.minecraft.client.resources.model.sprite.SpriteGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.MenuAccess;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.PlayerSkinRenderCache;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
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
    public static <H extends AbstractContainerMenu, S extends Screen & MenuAccess<H>> void registerScreen(MenuType<? extends H> type, ScreenFactory<H, S> factory) {
        registerScreen(MCPitanLib.MOD_ID, type, factory);
    }

    public static <H extends SimpleScreenHandler, S extends SimpleHandledScreen<H> & MenuAccess<H>> void registerScreen(MenuType<? extends H> type, ScreenFactory2<H, S> factory) {
        registerScreen(MCPitanLib.MOD_ID, type, factory);
    }

    public static <H extends AbstractContainerMenu, S extends Screen & MenuAccess<H>> void registerScreen(String modId, MenuType<? extends H> type, ScreenFactory<H, S> factory) {
        registerScreen(modId, () -> type, factory);
    }

    /**
     * NeoForgeでは登録が遅延されるため、呼び出し時点ではまだ生成されていないことがある。
     * 実際に使うタイミングまで解決を遅らせるためSupplierで受け取る。
     */
    @ExpectPlatform
    public static <H extends AbstractContainerMenu, S extends Screen & MenuAccess<H>> void registerScreen(String modId, Supplier<MenuType<? extends H>> type, ScreenFactory<H, S> factory) {
        throw new AssertionError();
    }

    public static <H extends SimpleScreenHandler, S extends SimpleHandledScreen<H> & MenuAccess<H>> void registerScreen(String modId, MenuType<? extends H> type, ScreenFactory2<H, S> factory) {
        registerScreen(modId, type, (ScreenFactory<H, S>) factory);
    }

    public static <H extends SimpleScreenHandler, S extends SimpleHandledScreen<H> & MenuAccess<H>> void registerScreen(String modId, Supplier<MenuType<? extends H>> type, ScreenFactory2<H, S> factory) {
        registerScreen(modId, type, (ScreenFactory<H, S>) factory);
    }

    public interface ScreenFactory<H extends AbstractContainerMenu, S extends Screen & MenuAccess<H>> {
        S create(H handler, Inventory inventory, Component text);
    }

    public interface ScreenFactory2<H extends SimpleScreenHandler, S extends SimpleHandledScreen<H> & MenuAccess<H>> extends ScreenFactory<H, S> {
        @Override
        default S create(H handler, Inventory inventory, Component text) {
            return create(handler, new CompatPlayerInventory(inventory), new TextComponent(text));
        }

        S create(H handler, CompatPlayerInventory inventory, TextComponent text);
    }

    public static <T extends ParticleOptions> void registerParticle(ParticleType<T> type, ParticleProvider<T> factory) {
        registerParticle(() -> type, factory);
    }

    /**
     * NeoForgeでは登録が遅延されるため、呼び出し時点ではまだ生成されていないことがある。
     * 実際に使うタイミングまで解決を遅らせるためSupplierで受け取る。
     */
    @ExpectPlatform
    public static <T extends ParticleOptions> void registerParticle(Supplier<ParticleType<T>> type, ParticleProvider<T> factory) {
        throw new AssertionError();
    }

    public static <T extends ParticleOptions> void registerParticle(ParticleType<T> type, DeferredParticleProvider<T> provider) {
        registerParticle(() -> type, provider);
    }

    /**
     * NeoForgeでは登録が遅延されるため、呼び出し時点ではまだ生成されていないことがある。
     * 実際に使うタイミングまで解決を遅らせるためSupplierで受け取る。
     */
    @ExpectPlatform
    public static <T extends ParticleOptions> void registerParticle(Supplier<ParticleType<T>> type, DeferredParticleProvider<T> provider) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void registerEntityModelLayer(ModelLayerLocation layer, EntityModelLayerContext context) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static <T extends Entity> void registerEntityRenderer(Supplier<? extends EntityType<? extends T>> type, EntityRendererProvider<T> provider) {
        throw new AssertionError();
    }

    @FunctionalInterface
    public interface DeferredParticleProvider<T extends ParticleOptions> {
        ParticleProvider<T> create(ExtendedSpriteSet spriteSet);
    }

    public interface ExtendedSpriteSet extends SpriteSet {
        TextureAtlas getAtlas();

        List<TextureAtlasSprite> getSprites();
    }

    public static void registryClientSpriteAtlasTexture(Identifier identifier) {
        //registryClientSprite(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE, identifier);
    }

    public static void registryClientSpriteAtlasTexture(TextureAtlasSprite sprite) {
        //registryClientSprite(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE, sprite);
    }

    public static void registryClientSprite(Identifier atlasId, Identifier identifier) {
        // ～1.19.2
    }

    public static void registryClientSprite(Identifier atlasId, TextureAtlasSprite sprite) {
        // ～1.19.2
    }

    public static <T extends BlockEntity> void registerBlockEntityRenderer(BlockEntityType<T> type, BlockEntityRendererFactory<T, BlockEntityRenderState> provider) {
        registerBlockEntityRenderer(() -> type, provider);
    }

    /**
     * NeoForgeでは登録が遅延されるため、呼び出し時点ではまだ生成されていないことがある。
     * 実際に使うタイミングまで解決を遅らせるためSupplierで受け取る。
     */
    @ExpectPlatform
    public static <T extends BlockEntity> void registerBlockEntityRenderer(Supplier<BlockEntityType<T>> type, BlockEntityRendererFactory<T, BlockEntityRenderState> provider) {
        throw new AssertionError();
    }

    @FunctionalInterface
    public interface BlockEntityRendererFactory<T extends BlockEntity, S extends BlockEntityRenderState> {
        BlockEntityRenderer<T, S> create(Context ctx);

        class Context {
            private final BlockEntityRenderDispatcher renderDispatcher;
            private final BlockModelResolver renderManager;
            private final ItemModelResolver itemModelManager;
            private final EntityRenderDispatcher entityRenderDispatcher;
            private final EntityModelSet layerRenderDispatcher;
            private final Font textRenderer;
            private final SpriteGetter spriteHolder;

            private final PlayerSkinRenderCache playerSkinRenderCache;

            public Context(BlockEntityRenderDispatcher renderDispatcher, BlockModelResolver renderManager, ItemModelResolver itemModelManager, EntityRenderDispatcher entityRenderDispatcher, EntityModelSet layerRenderDispatcher, Font textRenderer, SpriteGetter spriteHolder, PlayerSkinRenderCache playerSkinRenderCache) {
                this.renderDispatcher = renderDispatcher;
                this.renderManager = renderManager;
                this.itemModelManager = itemModelManager;
                this.entityRenderDispatcher = entityRenderDispatcher;
                this.layerRenderDispatcher = layerRenderDispatcher;
                this.textRenderer = textRenderer;
                this.spriteHolder = spriteHolder;
                this.playerSkinRenderCache = playerSkinRenderCache;
            }

            public BlockEntityRenderDispatcher getRenderDispatcher() {
                return this.renderDispatcher;
            }

            public BlockModelResolver getRenderManager() {
                return this.renderManager;
            }

            public EntityRenderDispatcher getEntityRenderDispatcher() {
                return this.entityRenderDispatcher;
            }

            public ItemModelResolver getItemModelManager() {
                return itemModelManager;
            }

            public EntityModelSet getLayerRenderDispatcher() {
                return this.layerRenderDispatcher;
            }

            public ModelPart getLayerModelPart(ModelLayerLocation modelLayer) {
                return this.layerRenderDispatcher.bakeLayer(modelLayer);
            }

            public Font getTextRenderer() {
                return this.textRenderer;
            }

            public SpriteGetter getSpriteHolder() {
                return spriteHolder;
            }

            public PlayerSkinRenderCache getPlayerSkinRenderCache() {
                return playerSkinRenderCache;
            }
        }
    }


    public static void registerRenderTypeBlock(RenderType layer, Block block) {
        ChunkSectionLayer blockRenderLayer = null;
        if (layer == RenderTypes.cutoutMovingBlock()) {
            blockRenderLayer = ChunkSectionLayer.CUTOUT;
        } else if (layer == RenderTypes.glintTranslucent()) {
            blockRenderLayer = ChunkSectionLayer.TRANSLUCENT;
        } else if (layer == RenderTypes.solidMovingBlock()) {
            blockRenderLayer = ChunkSectionLayer.SOLID;
        }

        if (blockRenderLayer == null) return;

//        ChunkSectionLayerMap.register(blockRenderLayer, block);
    }

    public static void registerRenderTypeFluid(RenderType layer, Fluid fluid) {
        ChunkSectionLayer blockRenderLayer = null;
        if (layer == RenderTypes.cutoutMovingBlock()) {
            blockRenderLayer = ChunkSectionLayer.CUTOUT;
        } else if (layer == RenderTypes.glintTranslucent()) {
            blockRenderLayer = ChunkSectionLayer.TRANSLUCENT;
        } else if (layer == RenderTypes.solidMovingBlock()) {
            blockRenderLayer = ChunkSectionLayer.SOLID;
        }

        if (blockRenderLayer == null) return;

//        ChunkSectionLayerMap.register(blockRenderLayer, fluid);
    }

    public static void registerCutoutBlock(Block block) {
        registerRenderTypeBlock(RenderTypes.cutoutMovingBlock(), block);
    }

    public static <T extends BlockEntity> void registerCompatBlockEntityRenderer(BlockEntityType<T> type, BlockEntityRendererFactory<T, BlockEntityRenderState> provider) {
        registerCompatBlockEntityRenderer(() -> type, provider);
    }

    /**
     * NeoForgeでは登録が遅延されるため、呼び出し時点ではまだ生成されていないことがある。
     * 実際に使うタイミングまで解決を遅らせるためSupplierで受け取る。
     */
    @ExpectPlatform
    public static <T extends BlockEntity> void registerCompatBlockEntityRenderer(Supplier<BlockEntityType<T>> type, BlockEntityRendererFactory<T, BlockEntityRenderState> provider) {

    }

    public static void registerRenderTypeBlock(CompatRenderLayer layer, Block block) {
        registerRenderTypeBlock(layer.layer, block);
    }

    public static void registerRenderTypeFluid(CompatRenderLayer layer, Fluid fluid) {
        registerRenderTypeFluid(layer.layer, fluid);
    }

    public static void registerColorProviderBlock(List<BlockTintSource> provider, Block... blocks) {
        registerColorProviderBlock(provider, () -> blocks);
    }

    /**
     * NeoForgeでは登録が遅延されるため、呼び出し時点ではまだ生成されていないことがある。
     * 実際に使うタイミングまで解決を遅らせるためSupplierで受け取る。
     */
    @ExpectPlatform
    public static void registerColorProviderBlock(List<BlockTintSource> provider, Supplier<Block[]> blocks) {
        throw new AssertionError();
    }

    public static void registerColorProviderBlock(CompatBlockColorProvider provider, Block... blocks) {
        registerColorProviderBlock(provider.toTintSource(), blocks);
    }
}
