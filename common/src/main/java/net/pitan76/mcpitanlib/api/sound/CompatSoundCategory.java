package net.pitan76.mcpitanlib.api.sound;

import net.minecraft.sounds.SoundSource;

public class CompatSoundCategory {
    
    public static final CompatSoundCategory MASTER = of(SoundSource.MASTER);
    public static final CompatSoundCategory MUSIC = of(SoundSource.MUSIC);
    public static final CompatSoundCategory RECORDS = of(SoundSource.RECORDS);
    public static final CompatSoundCategory WEATHER = of(SoundSource.WEATHER);
    public static final CompatSoundCategory BLOCKS = of(SoundSource.BLOCKS);
    public static final CompatSoundCategory HOSTILE = of(SoundSource.HOSTILE);
    public static final CompatSoundCategory NEUTRAL = of(SoundSource.NEUTRAL);
    public static final CompatSoundCategory PLAYERS = of(SoundSource.PLAYERS);
    public static final CompatSoundCategory AMBIENT = of(SoundSource.AMBIENT);
    public static final CompatSoundCategory VOICE = of(SoundSource.VOICE);
    
    public SoundSource soundCategory;
    
    public CompatSoundCategory(SoundSource soundCategory) {
        this.soundCategory = soundCategory;
    }
    
    public static CompatSoundCategory of(SoundSource soundCategory) {
        return new CompatSoundCategory(soundCategory);
    }

    public SoundSource get() {
        return soundCategory;
    }
    
    public String getName() {
        return soundCategory.getName();
    }
}
