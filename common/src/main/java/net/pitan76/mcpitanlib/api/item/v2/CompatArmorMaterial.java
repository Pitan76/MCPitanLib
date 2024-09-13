package net.pitan76.mcpitanlib.api.item.v2;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.pitan76.mcpitanlib.api.item.CompatibleArmorMaterial;
import net.pitan76.mcpitanlib.api.sound.CompatSoundEvent;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;

public interface CompatArmorMaterial extends CompatibleArmorMaterial {

    @Override
    default SoundEvent getEquipSound() {
        return getEquipCompatSound().get();
    }

    CompatSoundEvent getEquipCompatSound();

    @Override
    default Identifier getId() {
        return getCompatId().toMinecraft();
    }

    CompatIdentifier getCompatId();
}
