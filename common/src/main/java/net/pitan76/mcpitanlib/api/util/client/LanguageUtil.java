package net.pitan76.mcpitanlib.api.util.client;

import net.minecraft.client.resource.language.I18n;
import net.minecraft.client.resource.language.LanguageManager;
import net.pitan76.mcpitanlib.midohra.resource.ResourceManager;

public class LanguageUtil {
    public static boolean hasTranslation(String key) {
        return I18n.hasTranslation(key);
    }

    public static String translate(String key) {
        return I18n.translate(key);
    }

    public static String translate(String key, Object... args) {
        return I18n.translate(key, args);
    }

    public static String translateWithFallback(String key, String fallback) {
        return I18n.hasTranslation(key) ? I18n.translate(key) : fallback;
    }

    public static String translateWithFallback(String key, String fallback, Object... args) {
        return I18n.hasTranslation(key) ? I18n.translate(key, args) : fallback;
    }

    public static LanguageManager getLanguageManager() {
        return ClientUtil.getClient().getLanguageManager();
    }

    public static String getLanguage() {
        return getLanguageManager().getLanguage();
    }

    public static void setLanguage(String language) {
        getLanguageManager().setLanguage(language);
    }

    public static void reload(net.minecraft.resource.ResourceManager resourceManager) {
        getLanguageManager().reload(resourceManager);
    }

    public static void reload(ResourceManager resourceManager) {
        reload(resourceManager.getRaw());
    }
}
