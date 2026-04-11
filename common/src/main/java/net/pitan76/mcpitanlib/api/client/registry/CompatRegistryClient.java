package net.pitan76.mcpitanlib.api.client.registry;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.ParticleProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BlockColorRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.minecraft.client.color.block.BlockTintSource;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.block.BlockModelResolver;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.resources.model.sprite.SpriteGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.MenuAccess;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.LayerDefinition;
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
import net.minecraft.util.RandomSource;
import net.pitan76.mcpitanlib.MCPitanLib;
import net.pitan76.mcpitanlib.api.client.color.CompatBlockColorProvider;
import net.pitan76.mcpitanlib.api.client.render.CompatRenderLayer;
import net.pitan76.mcpitanlib.api.client.render.EntityModelLayerContext;

import java.util.List;
import java.util.function.Supplier;

@Environment(EnvType.CLIENT)
public class CompatRegistryClient {
    public static <H extends AbstractContainerMenu, S extends Screen & MenuAccess<H>> void registerScreen(MenuType<? extends H> type, ScreenFactory<H, S> factory) {
        registerScreen(MCPitanLib.MOD_ID, type, factory);
    }

    public static <H extends AbstractContainerMenu, S extends Screen & MenuAccess<H>> void registerScreen(String modId, MenuType<? extends H> type, ScreenFactory<H, S> factory) {
        MenuScreens.register(type, factory::create);
    }

    public interface ScreenFactory<H extends AbstractContainerMenu, S extends Screen & MenuAccess<H>> {
        S create(H handler, Inventory inventory, Component text);
    }

    public static <T extends ParticleOptions> void registerParticle(ParticleType<T> type, ParticleProvider<T> factory) {
        ParticleProviderRegistry.getInstance().register(type, factory);
    }

    public static <T extends ParticleOptions> void registerParticle(ParticleType<T> type, DeferredParticleProvider<T> provider) {
        ParticleProviderRegistry.getInstance().register(type, spriteSet -> provider.create(new ExtendedSpriteSet() {
            @Override
            public TextureAtlas getAtlas() {
                return spriteSet.getAtlas();
            }

            @Override
            public List<TextureAtlasSprite> getSprites() {
                return spriteSet.getSprites();
            }

            @Override
            public TextureAtlasSprite get(int age, int maxAge) {
                return spriteSet.get(age, maxAge);
            }

            @Override
            public TextureAtlasSprite get(RandomSource random) {
                return spriteSet.get(random);
            }

            @Override
            public TextureAtlasSprite first() {
                return spriteSet.first();
            }
        }));
    }

    public static void registerEntityModelLayer(ModelLayerLocation layer, EntityModelLayerContext context) {
        ModelLayerRegistry.registerModelLayer(layer, () -> LayerDefinition.create(context.getData(), context.getWidth(), context.getHeight()));
    }

    public static <T extends Entity> void registerEntityRenderer(Supplier<? extends EntityType<? extends T>> type, EntityRendererProvider<T> provider) {
        EntityRenderers.register(type.get(), provider);
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
        BlockEntityRenderers.register(type, ctx -> provider.create(new BlockEntityRendererFactory.Context(
                ctx.blockEntityRenderDispatcher(), ctx.blockModelResolver(), ctx.itemModelResolver(), ctx.entityRenderer(), ctx.entityModelSet(), ctx.font(), ctx.sprites(), ctx.playerSkinRenderCache()
        )));
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
        BlockEntityRendererRegistry.register(type, ctx -> provider.create(new BlockEntityRendererFactory.Context(
                ctx.blockEntityRenderDispatcher(), ctx.blockModelResolver(), ctx.itemModelResolver(), ctx.entityRenderer(), ctx.entityModelSet(), ctx.font(), ctx.sprites(), ctx.playerSkinRenderCache()
        )));
    }

    public static void registerRenderTypeBlock(CompatRenderLayer layer, Block block) {
        registerRenderTypeBlock(layer.layer, block);
    }

    public static void registerRenderTypeFluid(CompatRenderLayer layer, Fluid fluid) {
        registerRenderTypeFluid(layer.layer, fluid);
    }

    public static void registerColorProviderBlock(List<BlockTintSource> provider, Block... blocks) {
        if (blocks == null || blocks.length == 0) {
            BlockColorRegistry.register(provider);
        } else {
            BlockColorRegistry.register(provider, blocks);
        }
    }

    public static void registerColorProviderBlock(CompatBlockColorProvider provider, Block... blocks) {
        registerColorProviderBlock(provider.toTintSource(), blocks);
    }
}
