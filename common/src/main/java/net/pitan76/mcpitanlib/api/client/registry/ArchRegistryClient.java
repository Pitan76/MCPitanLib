package net.pitan76.mcpitanlib.api.client.registry;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.ScreenHandlerProvider;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.fluid.Fluid;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Supplier;

@Deprecated
@Environment(EnvType.CLIENT)
public class ArchRegistryClient {
    public static <H extends ScreenHandler, S extends Screen & ScreenHandlerProvider<H>> void registerScreen(ScreenHandlerType<? extends H> type, ScreenFactory<H, S> factory) {
        CompatRegistryClient.registerScreen(type, factory::create);
    }

    public interface ScreenFactory<H extends ScreenHandler, S extends Screen & ScreenHandlerProvider<H>> {
        S create(H handler, PlayerInventory inventory, Text text);
    }

    public static <T extends ParticleEffect> void registerParticle(ParticleType<T> type, ParticleFactory<T> factory) {
        CompatRegistryClient.registerParticle(type, factory);
    }

    public static <T extends ParticleEffect> void registerParticle(ParticleType<T> type, DeferredParticleProvider<T> provider) {
        CompatRegistryClient.registerParticle(type, spriteSet -> provider.create(new ExtendedSpriteSet() {
            @Override
            public SpriteAtlasTexture getAtlas() {
                return spriteSet.getAtlas();
            }

            @Override
            public java.util.List<Sprite> getSprites() {
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

    public static <T extends Entity> void registerEntityRenderer(Supplier<? extends EntityType<? extends T>> type, Function<EntityRenderDispatcher, EntityRenderer<T>> factory) {
        CompatRegistryClient.registerEntityRenderer(type, factory);
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
        registryClientSprite(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE, identifier);
    }

    public static void registryClientSpriteAtlasTexture(Sprite sprite) {
        registryClientSprite(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE, sprite);
    }

    public static void registryClientSprite(Identifier atlasId, Identifier identifier) {
        //?
    }

    public static void registryClientSprite(Identifier atlasId, Sprite sprite) {
        //?
    }

    public static <T extends BlockEntity> void registerBlockEntityRenderer(BlockEntityType<T> type, BlockEntityRendererFactory<T> provider) {
        CompatRegistryClient.registerBlockEntityRenderer(type, ctx -> provider.create(new BlockEntityRendererFactory.Context(ctx.getRenderDispatcher())));
    }

    @FunctionalInterface
    public interface BlockEntityRendererFactory<T extends BlockEntity> {
        BlockEntityRenderer<T> create(BlockEntityRendererFactory.Context ctx);

        class Context {
            private final BlockEntityRenderDispatcher renderDispatcher;

            public Context(BlockEntityRenderDispatcher renderDispatcher) {
                this.renderDispatcher = renderDispatcher;
            }

            public BlockEntityRenderDispatcher getRenderDispatcher() {
                return this.renderDispatcher;
            }
        }
    }


    public static void registerRenderTypeBlock(RenderLayer layer, Block block) {
        CompatRegistryClient.registerRenderTypeBlock(layer, block);
    }

    public static void registerRenderTypeFluid(RenderLayer layer, Fluid fluid) {
        CompatRegistryClient.registerRenderTypeFluid(layer, fluid);
    }
}
