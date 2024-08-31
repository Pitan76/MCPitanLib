package net.pitan76.mcpitanlib.core.datafixer;

public class Pair<A, B> {
    private A a;
    private B b;

    public Pair(A a, B b) {
        this.a = a;
        this.b = b;
    }

    public A getA() {
        return a;
    }

    public B getB() {
        return b;
    }

    public void setA(A a) {
        this.a = a;
    }

    public void setB(B b) {
        this.b = b;
    }

    public static <A, B> Pair<A, B> of(A a, B b) {
        return new Pair<>(a, b);
    }

    public static <A, B> Pair<A, B> of() {
        return new Pair<>(null, null);
    }

    public static <A, B> Pair<A, B> ofA(A a) {
        return new Pair<>(a, null);
    }

    public static <A, B> Pair<A, B> ofB(B b) {
        return new Pair<>(null, b);
    }
}
