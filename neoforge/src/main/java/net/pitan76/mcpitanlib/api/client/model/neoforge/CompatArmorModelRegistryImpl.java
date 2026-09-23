package net.pitan76.mcpitanlib.api.client.model.neoforge;

import net.minecraft.client.model.Model;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.pitan76.mcpitanlib.api.client.model.CompatArmorModelRegistry;
import net.pitan76.mcpitanlib.api.client.model.CompatModelBuilder;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.midohra.item.ItemWrapper;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * NeoForge 1.21.1 にはアイテム外部から防具モデルを登録する仕組みがないため、
 * ClientHooksMixin から getArmorModel / getArmorTexture の結果を差し替える
 */
public class CompatArmorModelRegistryImpl {
    private static final List<Entry> entries = new CopyOnWriteArrayList<>();

    public static void registerImpl(CompatModelBuilder humanoidModel, CompatIdentifier texture, ItemWrapper... items) {
        entries.add(new Entry(humanoidModel, texture, items));
    }

    private static Entry find(ItemStack stack) {
        if (stack == null || stack.isEmpty()) return null;
        Item item = stack.getItem();
        for (Entry entry : entries) {
            // アイテムの登録は遅延されるため、使うときに解決する
            for (ItemWrapper wrapper : entry.items) {
                if (wrapper.get() == item) return entry;
            }
        }
        return null;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static Model getArmorModel(ItemStack stack, EquipmentSlot slot, BipedEntityModel<?> original) {
        Entry entry = find(stack);
        if (entry == null) return null;

        BipedEntityModel<LivingEntity> model = entry.models.computeIfAbsent(slot, s -> new BipedEntityModel<>(entry.humanoidModel.bake()));
        if (original != null)
            ((BipedEntityModel) original).copyBipedStateTo(model);
        CompatArmorModelRegistry.applySlotVisibility(model, slot);
        return model;
    }

    public static Identifier getArmorTexture(ItemStack stack) {
        Entry entry = find(stack);
        return entry == null ? null : entry.texture.toMinecraft();
    }

    private static class Entry {
        private final CompatModelBuilder humanoidModel;
        private final CompatIdentifier texture;
        private final ItemWrapper[] items;
        private final Map<EquipmentSlot, BipedEntityModel<LivingEntity>> models = new EnumMap<>(EquipmentSlot.class);

        private Entry(CompatModelBuilder humanoidModel, CompatIdentifier texture, ItemWrapper[] items) {
            this.humanoidModel = humanoidModel;
            this.texture = texture;
            this.items = items;
        }
    }
}
