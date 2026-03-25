package net.pitan76.mcpitanlib.api.sound;

import net.minecraft.sound.SoundEvent;
import net.pitan76.mcpitanlib.api.registry.result.RegistryResult;

public class RegistryResultCompatSoundEvent extends CompatSoundEvent {

    public RegistryResult<SoundEvent> result;

    public RegistryResultCompatSoundEvent(RegistryResult<SoundEvent> result) {
        super((SoundEvent) null);
        this.result = result;
    }

    public static RegistryResultCompatSoundEvent of(RegistryResult<SoundEvent> result) {
        return new RegistryResultCompatSoundEvent(result);
    }

    @Override
    public SoundEvent get() {
        if ((result == null || result.getOrNull() == null) && super.get() != null)
            return super.get();

        return result.get();
    }
}
