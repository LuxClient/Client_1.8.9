package net.luxclient.hud.impl;

import net.luxclient.hud.HudComponent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

public class FpsDisplayHud extends HudComponent {

    public FpsDisplayHud(int width, int height) {
        super(width, height);
    }

    @Override
    public void render() {
        Gui.drawRect(getX(), getY(), getX() + getWidth(), getY() + getHeight(), this.backgroundColor);
        font.drawCenteredString("FPS: " + Minecraft.getDebugFPS(), this.getX() + this.width / 2, (float) (this.y + (this.height - font.FONT_HEIGHT - 1) / 2), -1);
        System.out.println("FPS Module");
    }

    @Override
    public void renderDummy() {
        Gui.drawRect(getX(), getY(), getX() + getWidth(), getY() + getHeight(), this.backgroundColor);
        font.drawCenteredString("FPS: 1000", this.getX() + this.width / 2, (float) (this.y + (this.height - font.FONT_HEIGHT - 1) / 2), -1);
    }
}
