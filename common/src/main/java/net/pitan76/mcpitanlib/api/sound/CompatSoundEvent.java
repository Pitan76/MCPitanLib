package net.pitan76.mcpitanlib.api.sound;

import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;

public class CompatSoundEvent {
    public SoundEvent soundEvent;
    public RegistryEntry.Reference<SoundEvent> reference;
    public RegistryEntry<SoundEvent> entry;

    public CompatSoundEvent(SoundEvent soundEvent) {
        this.soundEvent = soundEvent;
    }

    public CompatSoundEvent(RegistryEntry.Reference<SoundEvent> reference) {
        this.reference = reference;
    }

    public CompatSoundEvent(RegistryEntry<SoundEvent> entry) {
        this.entry = entry;
    }

    public static CompatSoundEvent of(SoundEvent soundEvent) {
        return new CompatSoundEvent(soundEvent);
    }

    public SoundEvent getSoundEvent() {
        if (soundEvent == null) {
            if (reference != null) {
                soundEvent = reference.value();
            } else if (entry != null) {
                soundEvent = entry.value();
            }
        }

        return soundEvent;
    }
}
