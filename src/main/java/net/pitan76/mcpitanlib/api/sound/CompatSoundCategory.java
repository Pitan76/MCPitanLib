package net.pitan76.mcpitanlib.api.sound;

import net.minecraft.sound.SoundCategory;

public class CompatSoundCategory {
    
    public static final CompatSoundCategory MASTER = of(SoundCategory.MASTER);
    public static final CompatSoundCategory MUSIC = of(SoundCategory.MUSIC);
    public static final CompatSoundCategory RECORDS = of(SoundCategory.RECORDS);
    public static final CompatSoundCategory WEATHER = of(SoundCategory.WEATHER);
    public static final CompatSoundCategory BLOCKS = of(SoundCategory.BLOCKS);
    public static final CompatSoundCategory HOSTILE = of(SoundCategory.HOSTILE);
    public static final CompatSoundCategory NEUTRAL = of(SoundCategory.NEUTRAL);
    public static final CompatSoundCategory PLAYERS = of(SoundCategory.PLAYERS);
    public static final CompatSoundCategory AMBIENT = of(SoundCategory.AMBIENT);
    public static final CompatSoundCategory VOICE = of(SoundCategory.VOICE);
    
    public SoundCategory soundCategory;
    
    public CompatSoundCategory(SoundCategory soundCategory) {
        this.soundCategory = soundCategory;
    }
    
    public static CompatSoundCategory of(SoundCategory soundCategory) {
        return new CompatSoundCategory(soundCategory);
    }

    public SoundCategory get() {
        return soundCategory;
    }
    
    public String getName() {
        return soundCategory.getName();
    }
}
