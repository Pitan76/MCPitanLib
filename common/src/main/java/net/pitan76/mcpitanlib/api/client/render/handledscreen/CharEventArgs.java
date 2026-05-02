package net.pitan76.mcpitanlib.api.client.render.handledscreen;

public class CharEventArgs {
    private final int character;

    public CharEventArgs(char character, int modifiers) {
        this.character = character;
    }

    public CharEventArgs(int codepoint) {
        this.character = codepoint;
    }

    public int getCharacter() {
        return character;
    }

    public char getChar() {
        return (char) character;
    }

    public int getModifiers() {
        return 0;
    }
}
