package net.pitan76.mcpitanlib.mixin;

import net.minecraft.resources.FileToIdConverter;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.pitan76.mcpitanlib.api.datapack.VirtualDatapack;
import net.pitan76.mcpitanlib.api.datapack.VirtualResourcePack;
import org.spongepowered.asm.mixin.Mixin;
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
@Mixin(FileToIdConverter.class)
public abstract class ResourceFinderMixin {

    @Inject(method = "listMatchingResources", at = @At("RETURN"), cancellable = true)
    private void mcpitanlib$listMatchingResources(ResourceManager manager, CallbackInfoReturnable<Map<Identifier, Resource>> cir) {
        FileToIdConverter self = (FileToIdConverter) (Object) this;
        if (!VirtualDatapack.has(self.prefix())) return;

        Map<Identifier, Resource> result = new HashMap<>(cir.getReturnValue());
        for (Map.Entry<Identifier, String> entry : VirtualDatapack.get(self.prefix()).entrySet()) {
            Identifier path = self.idToFile(entry.getKey());
            if (result.containsKey(path)) continue;

            result.put(path, mcpitanlib$createResource(entry.getValue()));
        }

        cir.setReturnValue(result);
    }

    @Inject(method = "listMatchingResourceStacks", at = @At("RETURN"), cancellable = true)
    private void mcpitanlib$listMatchingResourceStacks(ResourceManager manager, CallbackInfoReturnable<Map<Identifier, List<Resource>>> cir) {
        FileToIdConverter self = (FileToIdConverter) (Object) this;
        if (!VirtualDatapack.has(self.prefix())) return;

        Map<Identifier, List<Resource>> result = new HashMap<>(cir.getReturnValue());
        for (Map.Entry<Identifier, String> entry : VirtualDatapack.get(self.prefix()).entrySet()) {
            Identifier path = self.idToFile(entry.getKey());
            if (result.containsKey(path)) continue;

            List<Resource> resources = new ArrayList<>();
            resources.add(mcpitanlib$createResource(entry.getValue()));
            result.put(path, resources);
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
