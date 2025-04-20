package net.pitan76.mcpitanlib.midohra.easybuilder.share;

public class AbstractShare<T, S> {

    public T obj;
    public S args;

    public AbstractShare(T object, S args) {
        this.obj = object;
        this.args = args;
    }

    public T getObject() {
        return obj;
    }

    public S getArgs() {
        return args;
    }

    public boolean hasArgs() {
        return args != null;
    }
}
