package net.luxclient.ui.screens.settings.tabs.settings;

import net.luxclient.ui.screens.settings.UiSettingsTab;
import org.lwjgl.opengl.GL11;

public class UiTabClientSettings extends UiSettingsTab {

    @Override
    public void renderScreen(int mouseX, int mouseY, boolean ingame) {
        super.renderScreen(mouseX, mouseY, ingame);

        GL11.glPopMatrix();
    }

    @Override
    protected String getWindowName() {
        return "SETTINGS";
    }
}
