package net.pitan76.mcpitanlib.api.client.registry;

import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.entity.EntityType;

import java.util.function.Supplier;

public class EntityRendererRegistry {
    public static void registerEntityRendererAsFlyingItem(Supplier<EntityType<?>> entityType) {
        CompatRegistryClient.registerEntityRenderer(entityType, (ctx -> (EntityRenderer) new FlyingItemEntityRenderer<>(ctx)));
    }
}
