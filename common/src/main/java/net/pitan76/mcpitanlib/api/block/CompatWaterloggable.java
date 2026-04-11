package net.pitan76.mcpitanlib.api.block;

import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.sounds.SoundEvent;
import net.pitan76.mcpitanlib.api.sound.CompatSoundEvent;

import java.util.Optional;

public interface CompatWaterloggable extends SimpleWaterloggedBlock {

    @Override
    default Optional<SoundEvent> getPickupSound() {
        return getCompactBucketFillSound()
                .map(CompatSoundEvent::get);
    }

    default Optional<CompatSoundEvent> getCompactBucketFillSound() {
        return SimpleWaterloggedBlock.super.getPickupSound()
                .map(CompatSoundEvent::of);
    }
}
