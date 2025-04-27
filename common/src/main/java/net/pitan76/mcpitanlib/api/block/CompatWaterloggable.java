package net.pitan76.mcpitanlib.api.block;

import net.minecraft.block.Waterloggable;
import net.minecraft.sound.SoundEvent;
import net.pitan76.mcpitanlib.api.sound.CompatSoundEvent;

import java.util.Optional;

public interface CompatWaterloggable extends Waterloggable {

    @Override
    default Optional<SoundEvent> getBucketFillSound() {
        return getCompactBucketFillSound()
                .map(CompatSoundEvent::get);
    }

    default Optional<CompatSoundEvent> getCompactBucketFillSound() {
        return Waterloggable.super.getBucketFillSound()
                .map(CompatSoundEvent::of);
    }
}
