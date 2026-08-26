package net.pitan76.mcpitanlib.mixin;

import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceFinder;
import net.minecraft.util.Identifier;
import net.pitan76.mcpitanlib.api.datapack.VirtualDatapack;
import net.pitan76.mcpitanlib.api.datapack.VirtualResourcePack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * {@link VirtualDatapack} に登録されたJSONを、データパックから読んだものとして混ぜる。
 */
@Mixin(ResourceFinder.class)
public abstract class ResourceFinderMixin {

    @Shadow
    @Final
    private String directoryName;

    @Inject(method = "findResources", at = @At("RETURN"), cancellable = true)
    private void mcpitanlib$findResources(net.minecraft.resource.ResourceManager manager, CallbackInfoReturnable<Map<Identifier, Resource>> cir) {
        if (!VirtualDatapack.has(directoryName)) return;

        Map<Identifier, Resource> result = new HashMap<>(cir.getReturnValue());
        for (Map.Entry<Identifier, String> entry : VirtualDatapack.get(directoryName).entrySet()) {
            if (result.containsKey(entry.getKey())) continue;

            result.put(entry.getKey(), mcpitanlib$createResource(entry.getValue()));
        }

        cir.setReturnValue(result);
    }

    @Inject(method = "findAllResources", at = @At("RETURN"), cancellable = true)
    private void mcpitanlib$findAllResources(net.minecraft.resource.ResourceManager manager, CallbackInfoReturnable<Map<Identifier, List<Resource>>> cir) {
        if (!VirtualDatapack.has(directoryName)) return;

        Map<Identifier, List<Resource>> result = new HashMap<>(cir.getReturnValue());
        for (Map.Entry<Identifier, String> entry : VirtualDatapack.get(directoryName).entrySet()) {
            if (result.containsKey(entry.getKey())) continue;

            List<Resource> resources = new ArrayList<>();
            resources.add(mcpitanlib$createResource(entry.getValue()));
            result.put(entry.getKey(), resources);
        }

        cir.setReturnValue(result);
    }

    /**
     * InputStreamは開かれるたびに作り直す。使い回すと2回目以降がEOFで失敗する。
     */
    private static Resource mcpitanlib$createResource(String json) {
        byte[] bytes = json.getBytes(StandardCharsets.UTF_8);

        return new Resource(VirtualResourcePack.INSTANCE, () -> new ByteArrayInputStream(bytes));
    }
}
