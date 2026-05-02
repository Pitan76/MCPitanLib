package net.pitan76.mcpitanlib.api.client.render.handledscreen;

public class CharEventArgs {
    private final int character;
    private final int modifiers;

    public CharEventArgs(char character, int modifiers) {
        this.character = character;
        this.modifiers = modifiers;
    }

    public CharEventArgs(int codepoint) {
        this.character = codepoint;
        this.modifiers = 0;
    }

    public int getCharacter() {
        return character;
    }

    public char getChar() {
        return (char) character;
    }

    public int getModifiers() {
        return modifiers;
    }
}
