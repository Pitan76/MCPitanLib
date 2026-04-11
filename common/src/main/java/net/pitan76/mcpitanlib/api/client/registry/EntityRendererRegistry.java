package net.pitan76.mcpitanlib.api.client.registry;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.world.entity.EntityType;

import java.util.function.Supplier;

public class EntityRendererRegistry {
    public static void registerEntityRendererAsFlyingItem(Supplier<EntityType<?>> entityType) {
        CompatRegistryClient.registerEntityRenderer(entityType, (ctx -> (EntityRenderer) new ThrownItemRenderer<>(ctx)));
    }
}
