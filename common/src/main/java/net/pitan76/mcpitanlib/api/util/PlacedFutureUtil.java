package net.pitan76.mcpitanlib.api.util;

import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.placementmodifier.CountPlacementModifier;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;

import java.util.List;

public class PlacedFutureUtil {
    public static PlacedFeature create(ConfiguredFeature<?, ?> configuredFeature, List<PlacementModifier> placementModifiers) {
        return new PlacedFeature(RegistryEntry.of(configuredFeature), placementModifiers);
    }

    public static List<PlacementModifier> createPlacementModifiers(CountPlacementModifier countPlacementModifier, SquarePlacementModifier squarePlacementModifier, HeightRangePlacementModifier heightRangePlacementModifier) {
        return List.of(countPlacementModifier, squarePlacementModifier, heightRangePlacementModifier);
    }

    public static List<PlacementModifier> createPlacementModifiers(int chunkCount, int top, int bottom) {
        return createPlacementModifiers(
CountPlacementModifier.of(chunkCount),
                SquarePlacementModifier.of(),
                HeightRangePlacementModifier.uniform(YOffset.fixed(bottom), YOffset.fixed(top))
        );
    }

    public static List<PlacementModifier> createPlacementModifiers(int chunkCount, int top) {
        return createPlacementModifiers(
                CountPlacementModifier.of(chunkCount),
                SquarePlacementModifier.of(),
                HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(top))
        );
    }
}
