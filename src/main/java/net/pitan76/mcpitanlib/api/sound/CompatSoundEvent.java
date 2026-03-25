package net.pitan76.mcpitanlib.api.sound;

import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvent;
import org.jetbrains.annotations.Nullable;

public class CompatSoundEvent {
    private SoundEvent soundEvent;
    private Holder.Reference<SoundEvent> reference;
    private Holder<SoundEvent> entry;

    public CompatSoundEvent(SoundEvent soundEvent) {
        this.soundEvent = soundEvent;
    }

    public CompatSoundEvent(Holder.Reference<SoundEvent> reference) {
        this.reference = reference;
    }

    public CompatSoundEvent(Holder<SoundEvent> entry) {
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
    public Holder.Reference<SoundEvent> getReference() {
        return reference;
    }

    @Nullable
    @Deprecated
    public Holder<SoundEvent> getEntry() {
        if (entry == null) {
            entry = Holder.direct(soundEvent);
        }

        return entry;
    }
}
