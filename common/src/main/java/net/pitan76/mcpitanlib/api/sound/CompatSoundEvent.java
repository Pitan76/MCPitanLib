package net.pitan76.mcpitanlib.api.sound;

import net.minecraft.sound.SoundEvent;

public class CompatSoundEvent {
    public SoundEvent soundEvent;

    public CompatSoundEvent(SoundEvent soundEvent) {
        this.soundEvent = soundEvent;
    }

    public static CompatSoundEvent of(SoundEvent soundEvent) {
        return new CompatSoundEvent(soundEvent);
    }

    public SoundEvent getSoundEvent() {
        return soundEvent;
    }
}
