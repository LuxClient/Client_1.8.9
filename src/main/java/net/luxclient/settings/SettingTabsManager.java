package net.luxclient.settings;

import net.luxclient.ui.screens.settings.UiSettingsTab;
import net.luxclient.ui.screens.settings.tabs.UiTabMods;
import net.minecraft.client.Minecraft;

public class SettingTabsManager {

    // TODO init these
    public static final UiSettingsTab TAB_MODS = new UiTabMods();
    public static final UiSettingsTab TAB_SETTINGS = null;
    public static final UiSettingsTab TAB_COSMETICS = null;

    private static UiSettingsTab currentTab = TAB_MODS;

    public static void setCurrentTab(UiSettingsTab tab) {
        currentTab = tab;
        openCurrentScreen();
    }

    public static UiSettingsTab getCurrentTab() {
        return currentTab;
    }

    public static void openCurrentScreen() {
        Minecraft.getMinecraft().displayGuiScreen(currentTab);
    }

}
