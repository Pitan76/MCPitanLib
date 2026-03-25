package net.pitan76.mcpitanlib.api.client.option;

import net.minecraft.client.KeyMapping;
import net.minecraft.network.chat.Component;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CompatKeyBinding {
    private final KeyMapping keyBinding;
    public static final Map<CompatIdentifier, KeyMapping.Category> categories = new HashMap<>();

    public CompatKeyBinding(KeyMapping keyBinding) {
        this.keyBinding = keyBinding;
    }

    public CompatKeyBinding(String translationKey, int defaultKeyCode, CompatIdentifier category) {
        KeyMapping.Category cat;

        if (categories.containsKey(category)) {
            cat = categories.get(category);
        } else {
            cat = KeyMapping.Category.register(category.toMinecraft());
            categories.put(category, cat);
        }

        this.keyBinding = new KeyMapping(translationKey, defaultKeyCode, cat);
    }

    public CompatKeyBinding(String translationKey, int defaultKeyCode) {
        String[] parts = translationKey.split("\\.");
        if (Objects.equals(parts[0], "key") && parts.length == 3) {
            CompatIdentifier category = CompatIdentifier.of(parts[1], "main");
            KeyMapping.Category cat;

            if (categories.containsKey(category)) {
                cat = categories.get(category);
            } else {
                cat = KeyMapping.Category.register(category.toMinecraft());
                categories.put(category, cat);
            }

            this.keyBinding = new KeyMapping(translationKey, defaultKeyCode, cat);
        } else {
            throw new IllegalArgumentException("Cannot infer category from translation key: " + translationKey);
        }
    }

    public String getTranslationKey() {
        return keyBinding.saveString();
    }

    public Component getBoundKeyLocalizedText() {
        return keyBinding.getTranslatedKeyMessage();
    }

    public int getDefaultKeyCode() {
        return keyBinding.getDefaultKey().getValue();
    }

    public static CompatKeyBinding of(String translationKey, int defaultKeyCode, CompatIdentifier category) {
        return new CompatKeyBinding(translationKey, defaultKeyCode, category);
    }

    public static CompatKeyBinding of(String translationKey, int defaultKeyCode) {
        return new CompatKeyBinding(translationKey, defaultKeyCode);
    }

    public KeyMapping toMinecraft() {
        return keyBinding;
    }

    public KeyMapping getRaw() {
        return keyBinding;
    }
}
