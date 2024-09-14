package net.pitan76.mcpitanlib.api.util.math.random;

public class CompatRandom {
    private final java.util.Random javaRandom;

    public CompatRandom(java.util.Random javaRandom) {
        this.javaRandom = javaRandom;
    }


    @Deprecated
    public CompatRandom() {
        this.javaRandom = new java.util.Random();
    }

    @Deprecated
    public CompatRandom(long seed) {
        this.javaRandom = new java.util.Random(seed);
    }

    public static CompatRandom of(long seed) {
        return new CompatRandom(seed);
    }

    public static CompatRandom of() {
        return new CompatRandom();
    }

    public void setSeed(long seed) {
        javaRandom.setSeed(seed);
    }

    public void skip(int count) {
        for (int i = 0; i < count; i++) {
            javaRandom.nextInt();
        }
    }

    public void split() {
        javaRandom.nextInt();
    }

    public int nextInt() {
        return javaRandom.nextInt();
    }

    public int nextInt(int bound) {
        return javaRandom.nextInt(bound);
    }

    public long nextLong() {
        return javaRandom.nextLong();
    }

    public double nextDouble() {
        return javaRandom.nextDouble();
    }

    public double nextGaussian() {
        return javaRandom.nextGaussian();
    }

    public float nextFloat() {
        return javaRandom.nextFloat();
    }

    public int nextBetween(int min, int max) {
        return min + javaRandom.nextInt(max - min);
    }

    public int nextBetweenExclusive(int min, int max) {
        return min + javaRandom.nextInt(max - min + 1);
    }

    public boolean nextBoolean() {
        return javaRandom.nextBoolean();
    }

    @Deprecated
    public java.util.Random getJavaRandom() {
        return javaRandom;
    }
}
