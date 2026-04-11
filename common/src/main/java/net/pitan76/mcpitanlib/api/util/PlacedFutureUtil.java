package net.pitan76.mcpitanlib.api.util;

import net.minecraft.core.Holder;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;

import java.util.List;

public class PlacedFutureUtil {
    public static PlacedFeature create(ConfiguredFeature<?, ?> configuredFeature, List<PlacementModifier> placementModifiers) {
        return new PlacedFeature(Holder.direct(configuredFeature), placementModifiers);
    }

    public static List<PlacementModifier> createPlacementModifiers(CountPlacement countPlacementModifier, InSquarePlacement squarePlacementModifier, HeightRangePlacement heightRangePlacementModifier) {
        return List.of(countPlacementModifier, squarePlacementModifier, heightRangePlacementModifier);
    }

    public static List<PlacementModifier> createPlacementModifiers(int chunkCount, int top, int bottom) {
        return createPlacementModifiers(
CountPlacement.of(chunkCount),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(bottom), VerticalAnchor.absolute(top))
        );
    }

    public static List<PlacementModifier> createPlacementModifiers(int chunkCount, int top) {
        return createPlacementModifiers(
                CountPlacement.of(chunkCount),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(top))
        );
    }
}
