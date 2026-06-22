package net.pitan76.mcpitanlib.api.util.client;

import net.minecraft.client.resources.language.I18n;
import net.minecraft.client.resources.language.LanguageManager;
import net.minecraft.locale.Language;
import net.pitan76.mcpitanlib.midohra.resource.ResourceManager;

public class LanguageUtil {
    public static boolean hasTranslation(String key) {
        return Language.getInstance().has(key);
    }

    public static String translate(String key) {
        return I18n.get(key);
    }

    public static String translate(String key, Object... args) {
        return I18n.get(key, args);
    }

    public static String translateWithFallback(String key, String fallback) {
        return hasTranslation(key) ? I18n.get(key) : fallback;
    }

    public static String translateWithFallback(String key, String fallback, Object... args) {
        return hasTranslation(key) ? I18n.get(key, args) : fallback;
    }

    public static LanguageManager getLanguageManager() {
        return ClientUtil.getClient().getLanguageManager();
    }

    public static String getLanguage() {
        return getLanguageManager().getSelected();
    }

    public static void setLanguage(String language) {
        getLanguageManager().setSelected(language);
    }

    public static void reload(net.minecraft.server.packs.resources.ResourceManager resourceManager) {
        getLanguageManager().onResourceManagerReload(resourceManager);
    }

    public static void reload(ResourceManager resourceManager) {
        reload(resourceManager.getRaw());
    }
}
