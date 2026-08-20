package net.pitan76.mcpitanlib.api.client.registry.fabric;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.FabricSpriteProvider;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.gui.screen.ingame.ScreenHandlerProvider;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluid;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.pitan76.mcpitanlib.api.client.registry.ArchRegistryClient;
import net.pitan76.mcpitanlib.api.client.registry.CompatRegistryClient;

import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Supplier;

public class CompatRegistryClientImpl {

    public static <H extends ScreenHandler, S extends Screen & ScreenHandlerProvider<H>> void registerScreen(String modId, Supplier<ScreenHandlerType<? extends H>> type, CompatRegistryClient.ScreenFactory<H, S> factory) {
        HandledScreens.register(type.get(), factory::create);
    }

    public static <T extends ParticleEffect> void registerParticle(ParticleType<T> type, ParticleFactory<T> factory) {
        ParticleFactoryRegistry.getInstance().register(type, factory);
    }

    public static <T extends ParticleEffect> void registerParticle(ParticleType<T> type, final ArchRegistryClient.DeferredParticleProvider<T> provider) {
        ParticleFactoryRegistry.getInstance().register(type, new ParticleFactoryRegistry.PendingParticleFactory<T>() {
            @Override
            public ParticleFactory<T> create(final FabricSpriteProvider spriteSet) {
                return provider.create(new ArchRegistryClient.ExtendedSpriteSet() {
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
                });
            }
        });
    }

    @SuppressWarnings("unchecked")
    public static <T extends Entity> void registerEntityRenderer(Supplier<? extends EntityType<? extends T>> type, final Function<EntityRenderDispatcher, EntityRenderer<T>> factory) {
        EntityRendererRegistry.INSTANCE.register((EntityType<T>) type.get(), new EntityRendererRegistry.Factory() {
            @Override
            public EntityRenderer<?> create(EntityRenderDispatcher dispatcher, EntityRendererRegistry.Context context) {
                return factory.apply(dispatcher);
            }
        });
    }

    public static <T extends BlockEntity> void registerBlockEntityRenderer(Supplier<BlockEntityType<T>> type, final CompatRegistryClient.BlockEntityRendererFactory<T> provider) {
        BlockEntityRendererRegistry.INSTANCE.register(type.get(), new Function<BlockEntityRenderDispatcher, BlockEntityRenderer<? super T>>() {
            @Override
            public BlockEntityRenderer<? super T> apply(BlockEntityRenderDispatcher dispatcher) {
                return provider.create(new CompatRegistryClient.BlockEntityRendererFactory.Context(dispatcher));
            }
        });
    }

    public static void registerRenderTypeBlock(RenderLayer layer, Block block) {
        BlockRenderLayerMap.INSTANCE.putBlock(block, layer);
    }

    public static void registerRenderTypeFluid(RenderLayer layer, Fluid fluid) {
        BlockRenderLayerMap.INSTANCE.putFluid(fluid, layer);
    }
}
