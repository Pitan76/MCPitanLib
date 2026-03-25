package net.pitan76.mcpitanlib.api.client.registry;

import dev.architectury.event.events.client.ClientLifecycleEvent;
import dev.architectury.registry.client.gui.MenuScreenRegistry;
import dev.architectury.registry.client.level.entity.EntityRendererRegistry;
import dev.architectury.registry.client.particle.ParticleProviderRegistry;
import dev.architectury.registry.client.rendering.BlockEntityRendererRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.MenuAccess;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.entity.ItemRenderer;
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

import java.util.List;
import java.util.function.Supplier;

@Deprecated
@Environment(EnvType.CLIENT)
public class ArchRegistryClient {
    public static <H extends AbstractContainerMenu, S extends Screen & MenuAccess<H>> void registerScreen(MenuType<? extends H> type, ScreenFactory<H, S> factory) {
        ClientLifecycleEvent.CLIENT_SETUP.register((state) -> {

        });
        MenuScreenRegistry.registerScreenFactory(type, factory::create);
    }

    public interface ScreenFactory<H extends AbstractContainerMenu, S extends Screen & MenuAccess<H>> {
        S create(H handler, Inventory inventory, Component text);
    }

    public static <T extends ParticleOptions> void registerParticle(ParticleType<T> type, ParticleProvider<T> factory) {
        ParticleProviderRegistry.register(type, factory);
    }

    public static <T extends ParticleOptions> void registerParticle(ParticleType<T> type, DeferredParticleProvider<T> provider) {
        ParticleProviderRegistry.register(type, spriteSet -> provider.create(new ExtendedSpriteSet() {
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

    public static <T extends Entity> void registerEntityRenderer(Supplier<? extends EntityType<? extends T>> type, EntityRendererProvider<T> provider) {
        EntityRendererRegistry.register(type, provider);
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
        BlockEntityRendererRegistry.register(type, ctx -> provider.create(new BlockEntityRendererFactory.Context(
                ctx.blockEntityRenderDispatcher(), ctx.blockRenderDispatcher(), ctx.itemRenderer(), ctx.entityRenderer(), ctx.entityModelSet(), ctx.font()
        )));
    }

    @FunctionalInterface
    public interface BlockEntityRendererFactory<T extends BlockEntity, S extends BlockEntityRenderState> {
        BlockEntityRenderer<T, S> create(BlockEntityRendererFactory.Context ctx);

        class Context {
            private final BlockEntityRenderDispatcher renderDispatcher;
            private final BlockRenderDispatcher renderManager;
            private final ItemRenderer itemRenderer;
            private final EntityRenderDispatcher entityRenderDispatcher;
            private final EntityModelSet layerRenderDispatcher;
            private final Font textRenderer;

            public Context(BlockEntityRenderDispatcher renderDispatcher, BlockRenderDispatcher renderManager, ItemRenderer itemRenderer, EntityRenderDispatcher entityRenderDispatcher, EntityModelSet layerRenderDispatcher, Font textRenderer) {
                this.renderDispatcher = renderDispatcher;
                this.renderManager = renderManager;
                this.itemRenderer = itemRenderer;
                this.entityRenderDispatcher = entityRenderDispatcher;
                this.layerRenderDispatcher = layerRenderDispatcher;
                this.textRenderer = textRenderer;
            }

            public BlockEntityRenderDispatcher getRenderDispatcher() {
                return this.renderDispatcher;
            }

            public BlockRenderDispatcher getRenderManager() {
                return this.renderManager;
            }

            public EntityRenderDispatcher getEntityRenderDispatcher() {
                return this.entityRenderDispatcher;
            }

            public ItemRenderer getItemRenderer() {
                return this.itemRenderer;
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
        }
    }


    public static void registerRenderTypeBlock(RenderType layer, Block block) {
        CompatRegistryClient2.registerRenderTypeBlock(layer, block);
    }

    public static void registerRenderTypeFluid(RenderType layer, Fluid fluid) {
        CompatRegistryClient2.registerRenderTypeFluid(layer, fluid);
    }
}
