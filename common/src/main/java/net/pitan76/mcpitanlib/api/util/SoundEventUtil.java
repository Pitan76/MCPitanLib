package net.pitan76.mcpitanlib.api.util;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SoundEventUtil {
    public static Identifier getId(SoundEvent soundEvent) {
        return soundEvent.getId();
    }

    public static SoundEvent getSoundEvent(Identifier id) {
        return new SoundEvent(id);
    }

    public static CompatIdentifier getCompatId(SoundEvent soundEvent) {
        return CompatIdentifier.fromMinecraft(getId(soundEvent));
    }

    public static SoundEvent getSoundEvent(CompatIdentifier id) {
        return getSoundEvent(id.toMinecraft());
    }

    public static List<SoundEvent> getAllSoundEvents() {
        return Registry.SOUND_EVENT.stream().collect(Collectors.toList());
    }

    public static List<Identifier> getAllSoundEventIds() {
        return new ArrayList<>(Registry.SOUND_EVENT.getIds());
    }
}
