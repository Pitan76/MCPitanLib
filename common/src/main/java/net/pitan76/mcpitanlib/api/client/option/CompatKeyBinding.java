package net.pitan76.mcpitanlib.api.client.option;

import net.minecraft.client.option.KeyBinding;
import net.minecraft.text.Text;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CompatKeyBinding {
    private final KeyBinding keyBinding;
    public static final Map<CompatIdentifier, KeyBinding.Category> categories = new HashMap<>();

    public CompatKeyBinding(KeyBinding keyBinding) {
        this.keyBinding = keyBinding;
    }

    public CompatKeyBinding(String translationKey, int defaultKeyCode, CompatIdentifier category) {
        KeyBinding.Category cat;

        if (categories.containsKey(category)) {
            cat = categories.get(category);
        } else {
            cat = KeyBinding.Category.create(category.toMinecraft());
            categories.put(category, cat);
        }

        this.keyBinding = new KeyBinding(translationKey, defaultKeyCode, cat);
    }

    public CompatKeyBinding(String translationKey, int defaultKeyCode) {
        String[] parts = translationKey.split("\\.");
        if (Objects.equals(parts[0], "key") && parts.length == 3) {
            CompatIdentifier category = CompatIdentifier.of(parts[1], "main");
            KeyBinding.Category cat;

            if (categories.containsKey(category)) {
                cat = categories.get(category);
            } else {
                cat = KeyBinding.Category.create(category.toMinecraft());
                categories.put(category, cat);
            }

            this.keyBinding = new KeyBinding(translationKey, defaultKeyCode, cat);
        } else {
            throw new IllegalArgumentException("Cannot infer category from translation key: " + translationKey);
        }
    }

    public String getTranslationKey() {
        return keyBinding.getBoundKeyTranslationKey();
    }

    public Text getBoundKeyLocalizedText() {
        return keyBinding.getBoundKeyLocalizedText();
    }

    public int getDefaultKeyCode() {
        return keyBinding.getDefaultKey().getCode();
    }

    public static CompatKeyBinding of(String translationKey, int defaultKeyCode, CompatIdentifier category) {
        return new CompatKeyBinding(translationKey, defaultKeyCode, category);
    }

    public static CompatKeyBinding of(String translationKey, int defaultKeyCode) {
        return new CompatKeyBinding(translationKey, defaultKeyCode);
    }

    public KeyBinding toMinecraft() {
        return keyBinding;
    }

    public KeyBinding getRaw() {
        return keyBinding;
    }
}
