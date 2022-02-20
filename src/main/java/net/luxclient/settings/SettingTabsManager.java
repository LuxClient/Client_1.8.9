package net.luxclient.settings;

import net.luxclient.ui.screens.settings.UiSettingsTab;
import net.minecraft.client.Minecraft;

public class SettingTabsManager {

    private static UiSettingsTab currentTab;

    // TODO init these
    public static final UiSettingsTab TAB_MODS = null;
    public static final UiSettingsTab TAB_SETTINGS = null;
    public static final UiSettingsTab TAB_COSMETICS = null;

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
