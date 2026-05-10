package net.pitan76.mcpitanlib.api.client.registry;

import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.registry.client.level.entity.EntityModelLayerRegistry;
import dev.architectury.registry.client.level.entity.EntityRendererRegistry;
import dev.architectury.registry.client.particle.ParticleProviderRegistry;
import dev.architectury.registry.client.rendering.BlockEntityRendererRegistry;
import dev.architectury.registry.client.rendering.RenderTypeRegistry;
import dev.architectury.registry.menu.MenuRegistry;
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
        MenuRegistry.registerScreenFactory(type, factory::create);
    }

    public static <H extends SimpleScreenHandler, S extends SimpleHandledScreen<H> & ScreenHandlerProvider<H>> void registerScreen(String modId, ScreenHandlerType<? extends H> type, ScreenFactory2<H, S> factory) {
        registerScreen(modId, type, factory);
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

    public static <T extends BlockEntity> void registerBlockEntityRenderer(BlockEntityType<T> type, BlockEntityRendererFactory<T> provider) {
        BlockEntityRendererRegistry.register(type, ctx -> provider.create(new BlockEntityRendererFactory.Context(
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


    public static void registerRenderTypeBlock(RenderLayer layer, Block block) {
        RenderTypeRegistry.register(layer, block);
    }

    public static void registerRenderTypeFluid(RenderLayer layer, Fluid fluid) {
        RenderTypeRegistry.register(layer, fluid);
    }

    public static void registerCutoutBlock(Block block) {
        registerRenderTypeBlock(RenderLayer.getCutout(), block);
    }

    public static <T extends BlockEntity> void registerCompatBlockEntityRenderer(BlockEntityType<T> type, BlockEntityRendererFactory<T> provider) {
        BlockEntityRendererRegistry.register(type, ctx -> provider.create(new BlockEntityRendererFactory.Context(
                ctx.getRenderDispatcher(), ctx.getRenderManager(), ctx.getItemModelManager(), ctx.getItemRenderer(), ctx.getEntityRenderDispatcher(), ctx.getLoadedEntityModels(), ctx.getTextRenderer()
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
