package net.pitan76.mcpitanlib.api.block;

import net.minecraft.block.Waterloggable;
import net.minecraft.sound.SoundEvent;
import net.pitan76.mcpitanlib.api.sound.CompatSoundEvent;
import net.pitan76.mcpitanlib.api.sound.CompatSoundEvents;

import java.util.Optional;

public interface CompatWaterloggable extends Waterloggable {

    default Optional<SoundEvent> getBucketFillSound() {
        return getCompactBucketFillSound()
                .map(CompatSoundEvent::get);
    }

    default Optional<CompatSoundEvent> getCompactBucketFillSound() {
        return Optional.of(CompatSoundEvents.ITEM_BUCKET_FILL);
    }
}
