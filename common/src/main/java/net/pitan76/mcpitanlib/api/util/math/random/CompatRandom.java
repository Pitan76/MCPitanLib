package net.pitan76.mcpitanlib.api.util.math.random;

public class CompatRandom {
    private java.util.Random javaRandom;
    private net.minecraft.util.math.random.Random mcRandom;

    public CompatRandom(java.util.Random javaRandom) {
        this.javaRandom = javaRandom;
    }

    public CompatRandom(net.minecraft.util.math.random.Random mcRandom) {
        this.mcRandom = mcRandom;
    }

    @Deprecated
    public CompatRandom() {
        this.mcRandom = net.minecraft.util.math.random.Random.create();
    }

    @Deprecated
    public CompatRandom(long seed) {
        this.mcRandom = net.minecraft.util.math.random.Random.create(seed);
    }

    public static CompatRandom of(long seed) {
        return new CompatRandom(seed);
    }

    public static CompatRandom of() {
        return new CompatRandom();
    }

    public void setSeed(long seed) {
        if (javaRandom != null) {
            javaRandom.setSeed(seed);
        } else {
            mcRandom.setSeed(seed);
        }
    }

    public void skip(int count) {
        if (javaRandom != null) {
            for (int i = 0; i < count; i++) {
                javaRandom.nextInt();
            }
        } else {
            mcRandom.skip(count);
        }
    }

    public void split() {
        if (javaRandom != null) {
            javaRandom.nextInt();
        } else {
            mcRandom.split();
        }
    }

    public int nextInt() {
        if (javaRandom != null) {
            return javaRandom.nextInt();
        } else {
            return mcRandom.nextInt();
        }
    }

    public int nextInt(int bound) {
        if (javaRandom != null) {
            return javaRandom.nextInt(bound);
        } else {
            return mcRandom.nextInt(bound);
        }
    }

    public long nextLong() {
        if (javaRandom != null) {
            return javaRandom.nextLong();
        } else {
            return mcRandom.nextLong();
        }
    }

    public double nextDouble() {
        if (javaRandom != null) {
            return javaRandom.nextDouble();
        } else {
            return mcRandom.nextDouble();
        }
    }

    public double nextGaussian() {
        if (javaRandom != null) {
            return javaRandom.nextGaussian();
        } else {
            return mcRandom.nextGaussian();
        }
    }

    public float nextFloat() {
        if (javaRandom != null) {
            return javaRandom.nextFloat();
        } else {
            return mcRandom.nextFloat();
        }
    }

    public int nextBetween(int min, int max) {
        if (javaRandom != null) {
            return min + javaRandom.nextInt(max - min);
        } else {
            return min + mcRandom.nextInt(max - min);
        }
    }

    public int nextBetweenExclusive(int min, int max) {
        if (javaRandom != null) {
            return min + javaRandom.nextInt(max - min - 1);
        } else {
            return min + mcRandom.nextInt(max - min - 1);
        }
    }

    public boolean nextBoolean() {
        if (javaRandom != null) {
            return javaRandom.nextBoolean();
        } else {
            return mcRandom.nextBoolean();
        }
    }

    @Deprecated
    public java.util.Random getJavaRandom() {
        return javaRandom;
    }

    @Deprecated
    public net.minecraft.util.math.random.Random getMcRandom() {
        return mcRandom;
    }
}
