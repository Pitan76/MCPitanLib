package net.pitan76.mcpitanlib.api.item.equipment;

import net.minecraft.component.type.EquippableComponent;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryEntryLookup;
import net.pitan76.mcpitanlib.api.item.ArmorEquipmentType;
import net.pitan76.mcpitanlib.api.sound.CompatSoundEvent;
import net.pitan76.mcpitanlib.api.tag.TagKey;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;

public class EquippableComponentBuilder {

    public ArmorEquipmentType equipmentType;
    public CompatSoundEvent equipSound;
    public CompatSoundEvent shearingSound;
    public boolean equipOnInteract = false;
    public boolean canBeSheared = false;
    public CompatEquipmentAsset model;
    public TagKey<?> allowedEntities = null;

    public EquippableComponentBuilder() {

    }

    public EquippableComponentBuilder(ArmorEquipmentType equipmentType) {
        this.equipmentType = equipmentType;
    }

    public EquippableComponentBuilder equipmentType(ArmorEquipmentType equipmentType) {
        this.equipmentType = equipmentType;
        return this;
    }

    public EquippableComponentBuilder equipSound(CompatSoundEvent equipSound) {
        this.equipSound = equipSound;
        return this;
    }

    public EquippableComponentBuilder shearingSound(CompatSoundEvent shearingSound) {
        this.shearingSound = shearingSound;
        return this;
    }

    public EquippableComponentBuilder equipOnInteract(boolean equipOnInteract) {
        this.equipOnInteract = equipOnInteract;
        return this;
    }

    public EquippableComponentBuilder canBeSheared(boolean canBeSheared) {
        this.canBeSheared = canBeSheared;
        return this;
    }

    public EquippableComponentBuilder model(CompatEquipmentAsset model) {
        this.model = model;
        return this;
    }

    public EquippableComponentBuilder model(CompatIdentifier id) {
        return model(CompatEquipmentAsset.of(id));
    }

    public EquippableComponentBuilder allowedEntities(TagKey<?> allowedEntities) {
        this.allowedEntities = allowedEntities;
        return this;
    }

    public EquippableComponentBuilder allowedEntities(CompatIdentifier tagId) {
        return allowedEntities(TagKey.create(TagKey.Type.ENTITY_TYPE, tagId));
    }

    public EquippableComponentBuilder allowedEntities(String tagId) {
        return allowedEntities(CompatIdentifier.of(tagId));
    }

    public CompatEquippableComponent build() {
        EquippableComponent.Builder component = EquippableComponent.builder(equipmentType.getSlot());
        if (equipSound != null)
            component.equipSound(equipSound.getEntry() != null ? equipSound.getEntry() : equipSound.getReference());
        if (shearingSound != null)
            component.shearingSound(shearingSound.getEntry() != null ? shearingSound.getEntry() : shearingSound.getReference());

        component.equipOnInteract(equipOnInteract);
        component.canBeSheared(canBeSheared);
        if (model != null) component.model(model.raw());
        if (allowedEntities != null) {
            RegistryEntryLookup<EntityType<?>> registryEntryLookup = Registries.createEntryLookup(Registries.ENTITY_TYPE);
            component.allowedEntities(registryEntryLookup.getOrThrow((net.minecraft.registry.tag.TagKey<EntityType<?>>) allowedEntities.getTagKey()));
        }

        return CompatEquippableComponent.of(component.build());
    }
}
