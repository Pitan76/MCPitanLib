package net.pitan76.mcpitanlib.api.item.equipment;

import net.minecraft.world.item.equipment.Equippable;
import net.minecraft.world.entity.EntityType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.HolderGetter;
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
        Equippable.Builder component = Equippable.builder(equipmentType.getSlot());
        if (equipSound != null)
            component.setEquipSound(equipSound.getEntry() != null ? equipSound.getEntry() : equipSound.getReference());
        if (shearingSound != null)
            component.setShearingSound(shearingSound.getEntry() != null ? shearingSound.getEntry() : shearingSound.getReference());

        component.setEquipOnInteract(equipOnInteract);
        component.setCanBeSheared(canBeSheared);
        if (model != null) component.setAsset(model.raw());
        if (allowedEntities != null) {
            HolderGetter<EntityType<?>> registryEntryLookup = BuiltInRegistries.acquireBootstrapRegistrationLookup(BuiltInRegistries.ENTITY_TYPE);
            component.setAllowedEntities(registryEntryLookup.getOrThrow((net.minecraft.tags.TagKey<EntityType<?>>) allowedEntities.getTagKey()));
        }

        return CompatEquippableComponent.of(component.build());
    }
}
