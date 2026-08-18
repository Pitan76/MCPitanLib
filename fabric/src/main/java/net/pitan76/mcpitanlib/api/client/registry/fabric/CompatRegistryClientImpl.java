package net.pitan76.mcpitanlib.api.client.registry.fabric;

import net.fabricmc.fabric.api.client.particle.v1.ParticleProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BlockColorRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.minecraft.client.color.block.BlockTintSource;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.MenuAccess;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.pitan76.mcpitanlib.api.client.registry.CompatRegistryClient;
import net.pitan76.mcpitanlib.api.client.render.EntityModelLayerContext;

import java.util.List;
import java.util.function.Supplier;

public class CompatRegistryClientImpl {
    public static <H extends AbstractContainerMenu, S extends Screen & MenuAccess<H>> void registerScreen(String modId, Supplier<MenuType<? extends H>> type, CompatRegistryClient.ScreenFactory<H, S> factory) {
        MenuScreens.register(type.get(), factory::create);
    }

    public static <T extends ParticleOptions> void registerParticle(Supplier<ParticleType<T>> type, ParticleProvider<T> factory) {
        ParticleProviderRegistry.getInstance().register(type.get(), factory);
    }

    public static <T extends ParticleOptions> void registerParticle(Supplier<ParticleType<T>> type, CompatRegistryClient.DeferredParticleProvider<T> provider) {
        ParticleProviderRegistry.getInstance().register(type.get(), spriteSet -> provider.create(new CompatRegistryClient.ExtendedSpriteSet() {
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

    public static <T extends BlockEntity> void registerBlockEntityRenderer(Supplier<BlockEntityType<T>> type, CompatRegistryClient.BlockEntityRendererFactory<T, BlockEntityRenderState> provider) {
        BlockEntityRenderers.register(type.get(), ctx -> provider.create(new CompatRegistryClient.BlockEntityRendererFactory.Context(
                ctx.blockEntityRenderDispatcher(), ctx.blockModelResolver(), ctx.itemModelResolver(), ctx.entityRenderer(), ctx.entityModelSet(), ctx.font(), ctx.sprites(), ctx.playerSkinRenderCache()
        )));
    }

    public static <T extends BlockEntity> void registerCompatBlockEntityRenderer(Supplier<BlockEntityType<T>> type, CompatRegistryClient.BlockEntityRendererFactory<T, BlockEntityRenderState> provider) {
        BlockEntityRendererRegistry.register(type.get(), ctx -> provider.create(new CompatRegistryClient.BlockEntityRendererFactory.Context(
                ctx.blockEntityRenderDispatcher(), ctx.blockModelResolver(), ctx.itemModelResolver(), ctx.entityRenderer(), ctx.entityModelSet(), ctx.font(), ctx.sprites(), ctx.playerSkinRenderCache()
        )));
    }

    public static void registerColorProviderBlock(List<BlockTintSource> provider, Supplier<Block[]> blocks) {
        Block[] array = blocks.get();
        if (array == null || array.length == 0) {
            BlockColorRegistry.register(provider);
        } else {
            BlockColorRegistry.register(provider, array);
        }
    }
}
