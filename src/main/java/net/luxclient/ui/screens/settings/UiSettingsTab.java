package net.luxclient.ui.screens.settings;

import net.luxclient.ui.UiScreen;
import net.luxclient.util.ClientGuiUtils;

import java.awt.Color;
import java.io.IOException;

public abstract class UiSettingsTab extends UiScreen {

    protected static int dragX = -100, dragY = -100;
    protected boolean windowDragged = false;

    private int offsetX, offsetY;

    protected abstract String getWindowName();

    @Override
    public void renderScreen(int mouseX, int mouseY, boolean ingame) {
        if(windowDragged) {
                dragX = mouseX - offsetX;
                dragY = mouseY - offsetY;
        } else {
            offsetX = mouseX - dragX;
            offsetY = mouseY - dragY;
        }

        ClientGuiUtils.drawRoundedRect(dragX - 1, dragY - 1, 352, 202, 5, new Color(12, 12, 12, 55));
        ClientGuiUtils.drawRoundedRect(dragX, dragY, 350, 200, 5, new Color(12, 12, 12, 55));

        ClientGuiUtils.drawRoundedRect(dragX + 4, dragY + 24, 87, 172, 5, new Color(120, 120, 120, 50));
        ClientGuiUtils.drawRoundedRect(dragX + 5, dragY + 25, 85, 170, 5, new Color(120, 120, 120, 50));

        ClientGuiUtils.drawRoundedRect(dragX + 94, dragY + 24, 252, 172, 5, new Color(120, 120, 120, 50));
        ClientGuiUtils.drawRoundedRect(dragX + 95, dragY + 25, 250, 170, 5, new Color(120, 120, 120, 50));
    }

    @Override
    public void initGui() {
        if(dragX == -100) dragX = this.width / 2 - 175;
        if(dragY == -100) dragY = this.height / 2 - 100;

        super.initGui();
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if(mouseX >= dragX - 2 && mouseY >= dragY - 2 && mouseX < dragX + 354 && mouseY < dragY + 29) {
            windowDragged = true;
        }
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        windowDragged = false;
        super.mouseReleased(mouseX, mouseY, state);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        if(keyCode == mc.gameSettings.keyBindings[33].getKeyCode()) {
            mc.displayGuiScreen(null);
        }
        super.keyTyped(typedChar, keyCode);
    }
}

