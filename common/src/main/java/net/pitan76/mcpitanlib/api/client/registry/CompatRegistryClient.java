package net.pitan76.mcpitanlib.api.client.registry;

import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.registry.client.gui.MenuScreenRegistry;
import dev.architectury.registry.client.level.entity.EntityModelLayerRegistry;
import dev.architectury.registry.client.level.entity.EntityRendererRegistry;
import dev.architectury.registry.client.particle.ParticleProviderRegistry;
import dev.architectury.registry.client.rendering.BlockEntityRendererRegistry;
import dev.architectury.registry.client.rendering.RenderTypeRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
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
import net.minecraft.client.render.block.entity.BlockEntityRenderManager;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.state.BlockEntityRenderState;
import net.minecraft.client.render.entity.EntityRenderManager;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.LoadedEntityModels;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.texture.PlayerSkinCache;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.texture.SpriteHolder;
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
import net.pitan76.mcpitanlib.api.client.render.CompatRenderLayer;
import net.pitan76.mcpitanlib.api.client.render.EntityModelLayerContext;

import java.util.List;
import java.util.function.Supplier;

@Environment(EnvType.CLIENT)
public class CompatRegistryClient {
    public static <H extends ScreenHandler, S extends Screen & ScreenHandlerProvider<H>> void registerScreen(ScreenHandlerType<? extends H> type, ScreenFactory<H, S> factory) {
        registerScreen(MCPitanLib.MOD_ID, type, factory);
    }

    public static <H extends ScreenHandler, S extends Screen & ScreenHandlerProvider<H>> void registerScreen(String modId, ScreenHandlerType<? extends H> type, ScreenFactory<H, S> factory) {
        MenuScreenRegistry.registerScreenFactory(type, factory::create);
    }

    public interface ScreenFactory<H extends ScreenHandler, S extends Screen & ScreenHandlerProvider<H>> {
        S create(H handler, PlayerInventory inventory, Text text);
    }

    public static <T extends ParticleEffect> void registerParticle(ParticleType<T> type, ParticleFactory<T> factory) {
        ParticleProviderRegistry.register(type, factory);
    }

    public static <T extends ParticleEffect> void registerParticle(ParticleType<T> type, DeferredParticleProvider<T> provider) {
        ParticleProviderRegistry.register(type, spriteSet -> provider.create(new ExtendedSpriteSet() {
            @Override
            public SpriteAtlasTexture getAtlas() {
                return spriteSet.getAtlas();
            }

            @Override
            public List<Sprite> getSprites() {
                return spriteSet.getSprites();
            }

            @Override
            public Sprite getSprite(int age, int maxAge) {
                return spriteSet.getSprite(age, maxAge);
            }

            @Override
            public Sprite getSprite(Random random) {
                return spriteSet.getSprite(random);
            }

            @Override
            public Sprite getFirst() {
                return spriteSet.getFirst();
            }
        }));
    }

    public static void registerEntityModelLayer(EntityModelLayer layer, EntityModelLayerContext context) {
        EntityModelLayerRegistry.register(layer, () -> TexturedModelData.of(context.getData(), context.getWidth(), context.getHeight()));
    }

    public static <T extends Entity> void registerEntityRenderer(Supplier<? extends EntityType<? extends T>> type, EntityRendererFactory<T> provider) {
        EntityRendererRegistry.register(type, provider);
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

    public static <T extends BlockEntity> void registerBlockEntityRenderer(BlockEntityType<T> type, BlockEntityRendererFactory<T, BlockEntityRenderState> provider) {
        BlockEntityRendererRegistry.register(type, ctx -> provider.create(new BlockEntityRendererFactory.Context(
                ctx.renderDispatcher(), ctx.renderManager(), ctx.itemModelManager(), ctx.itemRenderer(), ctx.entityRenderDispatcher(), ctx.loadedEntityModels(), ctx.textRenderer(), ctx.spriteHolder(), ctx.playerSkinRenderCache()
        )));
    }

    @FunctionalInterface
    public interface BlockEntityRendererFactory<T extends BlockEntity, S extends BlockEntityRenderState> {
        BlockEntityRenderer<T, S> create(Context ctx);

        class Context {
            private final BlockEntityRenderManager renderDispatcher;
            private final BlockRenderManager renderManager;
            private final ItemModelManager itemModelManager;
            private final ItemRenderer itemRenderer;
            private final EntityRenderManager entityRenderDispatcher;
            private final LoadedEntityModels layerRenderDispatcher;
            private final TextRenderer textRenderer;
            private final SpriteHolder spriteHolder;

            private final PlayerSkinCache playerSkinRenderCache;

            public Context(BlockEntityRenderManager renderDispatcher, BlockRenderManager renderManager, ItemModelManager itemModelManager, ItemRenderer itemRenderer, EntityRenderManager entityRenderDispatcher, LoadedEntityModels layerRenderDispatcher, TextRenderer textRenderer, SpriteHolder spriteHolder, PlayerSkinCache playerSkinRenderCache) {
                this.renderDispatcher = renderDispatcher;
                this.renderManager = renderManager;
                this.itemModelManager = itemModelManager;
                this.itemRenderer = itemRenderer;
                this.entityRenderDispatcher = entityRenderDispatcher;
                this.layerRenderDispatcher = layerRenderDispatcher;
                this.textRenderer = textRenderer;
                this.spriteHolder = spriteHolder;
                this.playerSkinRenderCache = playerSkinRenderCache;
            }

            public BlockEntityRenderManager getRenderDispatcher() {
                return this.renderDispatcher;
            }

            public BlockRenderManager getRenderManager() {
                return this.renderManager;
            }

            public EntityRenderManager getEntityRenderDispatcher() {
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

            public SpriteHolder getSpriteHolder() {
                return spriteHolder;
            }

            public PlayerSkinCache getPlayerSkinRenderCache() {
                return playerSkinRenderCache;
            }
        }
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

        RenderTypeRegistry.register(blockRenderLayer, block);
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

        RenderTypeRegistry.register(blockRenderLayer, fluid);
    }

    public static void registerCutoutBlock(Block block) {
        registerRenderTypeBlock(RenderLayer.getCutout(), block);
    }

    public static <T extends BlockEntity> void registerCompatBlockEntityRenderer(BlockEntityType<T> type, BlockEntityRendererFactory<T, BlockEntityRenderState> provider) {
        BlockEntityRendererRegistry.register(type, ctx -> provider.create(new BlockEntityRendererFactory.Context(
                ctx.renderDispatcher(), ctx.renderManager(), ctx.itemModelManager(), ctx.itemRenderer(), ctx.entityRenderDispatcher(), ctx.loadedEntityModels(), ctx.textRenderer(), ctx.spriteHolder(), ctx.playerSkinRenderCache()
        )));
    }

    public static void registerRenderTypeBlock(CompatRenderLayer layer, Block block) {
        registerRenderTypeBlock(layer.layer, block);
    }

    public static void registerRenderTypeFluid(CompatRenderLayer layer, Fluid fluid) {
        registerRenderTypeFluid(layer.layer, fluid);
    }

    @ExpectPlatform
    public static void registerColorProviderBlock(BlockColorProvider provider, Block... blocks) {
        throw new AssertionError("This method should be replaced by the platform implementation");
    }

    public static void registerColorProviderBlock(CompatBlockColorProvider provider, Block... blocks) {
        registerColorProviderBlock((BlockColorProvider) provider, blocks);
    }
}
