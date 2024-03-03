package net.pitan76.mcpitanlib.api.util;

import net.minecraft.registry.Registries;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SoundEventUtil {
    public static Identifier getId(SoundEvent soundEvent) {
        return soundEvent.getId();
    }

    public static SoundEvent getSoundEvent(Identifier id) {
        return SoundEvent.of(id);
    }

    public static List<SoundEvent> getAllSoundEvents() {
        return Registries.SOUND_EVENT.stream().collect(Collectors.toList());
    }

    public static List<Identifier> getAllSoundEventIds() {
        return new ArrayList<>(Registries.SOUND_EVENT.getIds());
    }
}
