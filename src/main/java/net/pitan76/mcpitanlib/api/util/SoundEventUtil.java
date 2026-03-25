package net.pitan76.mcpitanlib.api.util;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.Identifier;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SoundEventUtil {
    public static Identifier getId(SoundEvent soundEvent) {
        return soundEvent.location();
    }

    public static SoundEvent getSoundEvent(Identifier id) {
        return SoundEvent.createVariableRangeEvent(id);
    }

    public static CompatIdentifier getCompatId(SoundEvent soundEvent) {
        return CompatIdentifier.fromMinecraft(getId(soundEvent));
    }

    public static SoundEvent getSoundEvent(CompatIdentifier id) {
        return getSoundEvent(id.toMinecraft());
    }

    public static List<SoundEvent> getAllSoundEvents() {
        return BuiltInRegistries.SOUND_EVENT.stream().collect(Collectors.toList());
    }

    public static List<Identifier> getAllSoundEventIds() {
        return new ArrayList<>(BuiltInRegistries.SOUND_EVENT.keySet());
    }
}
