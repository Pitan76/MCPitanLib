package net.pitan76.mcpitanlib.mixin;

import com.mojang.serialization.Dynamic;
import net.minecraft.datafixer.fix.ItemStackComponentizationFix;
import net.pitan76.mcpitanlib.api.datafixer.ItemStackFixer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemStackComponentizationFix.class)
public class ItemStackComponentizationFixMixin {
    @Inject(method = "fixStack", at = @At("TAIL"))
    private static void mcpitanlib$fixStack(ItemStackComponentizationFix.StackData data, Dynamic<?> dynamic, CallbackInfo ci) {
        if (!ItemStackFixer.getNbt2componentMapIfItemEqualMap().isEmpty()) {
            ItemStackFixer.getNbt2componentMapIfItemEqualMap().forEach((itemId, map) -> {
                for (String nbtKey : map.keySet()) {
                    if (data.itemEquals(itemId)) {
                        data.moveToComponent(nbtKey, map.get(nbtKey));
                    }
                }
            });
        }

        if (!ItemStackFixer.getNbt2componentMapIfitemMatchesMap().isEmpty()) {
            ItemStackFixer.getNbt2componentMapIfitemMatchesMap().forEach((itemIds, map) -> {
                for (String nbtKey : map.keySet()) {
                    if (data.itemMatches(itemIds)) {
                        data.moveToComponent(nbtKey, map.get(nbtKey));
                    }
                }
            });
        }

        if (!ItemStackFixer.getNbt2componentMap().isEmpty()) {
            ItemStackFixer.getNbt2componentMap().forEach(data::moveToComponent);
        }
    }

}
