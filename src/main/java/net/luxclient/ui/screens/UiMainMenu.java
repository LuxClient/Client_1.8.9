package net.luxclient.ui.screens;

import net.luxclient.ui.UiScreen;
import net.luxclient.ui.components.buttons.UiButton;

public class UiMainMenu extends UiScreen {

    @Override
    public void renderScreen(int mouseX, int mouseY, boolean ingame) {

    }

    @Override
    public void initComponents() {
        this.componentList.add(new UiButton(0, this.width / 2 - 90, this.height / 2 - 50, "SUSSY AHH"));
    }

}
