package net.pitan76.mcpitanlib.api.sound;

import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import org.jetbrains.annotations.Nullable;

public class CompatSoundEvent {
    private SoundEvent soundEvent;
    private RegistryEntry.Reference<SoundEvent> reference;
    private RegistryEntry<SoundEvent> entry;

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

    public SoundEvent get() {
        if (soundEvent == null) {
            if (reference != null) {
                soundEvent = reference.value();
            } else if (entry != null) {
                soundEvent = entry.value();
            }
        }

        return soundEvent;
    }

    @Nullable
    @Deprecated
    public RegistryEntry.Reference<SoundEvent> getReference() {
        return reference;
    }

    @Nullable
    @Deprecated
    public RegistryEntry<SoundEvent> getEntry() {
        return entry;
    }
}
