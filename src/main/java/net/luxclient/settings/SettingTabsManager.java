package net.luxclient.settings;

import net.luxclient.ui.screens.settings.UiSettingsTab;
import net.luxclient.ui.screens.settings.tabs.cosmetics.UiTabCosmetics;
import net.luxclient.ui.screens.settings.tabs.mods.UiTabMods;
import net.luxclient.ui.screens.settings.tabs.screenshots.UiTabScreenshots;
import net.luxclient.ui.screens.settings.tabs.settings.UiTabClientSettings;
import net.luxclient.ui.screens.settings.tabs.waypoints.UiTabWaypoints;
import net.minecraft.client.Minecraft;

public class SettingTabsManager {

    // TODO init these
    public static final UiSettingsTab TAB_MODS = new UiTabMods();
    public static final UiSettingsTab TAB_SETTINGS = new UiTabClientSettings();
    public static final UiSettingsTab TAB_COSMETICS = new UiTabCosmetics();
    public static final UiSettingsTab TAB_WAYPOINTS = new UiTabWaypoints();
    public static final UiSettingsTab TAB_SCREENSHOTS = new UiTabScreenshots();

    private static UiSettingsTab currentTab = TAB_MODS;

    public static void setCurrentTab(UiSettingsTab tab) {
        if(currentTab != tab) {
            currentTab = tab;
            openCurrentScreen();
        }
    }

    public static UiSettingsTab getCurrentTab() {
        return currentTab;
    }

    public static void openCurrentScreen() {
        Minecraft.getMinecraft().displayGuiScreen(currentTab);
    }

}
