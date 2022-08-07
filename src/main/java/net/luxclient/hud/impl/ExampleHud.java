package net.luxclient.hud.impl;

import net.luxclient.hud.HudComponent;
import net.minecraft.client.gui.Gui;

public class ExampleHud extends HudComponent {

    public ExampleHud(int width, int height) {
        super(width, height);
        x = 3;
        y = 3;
    }

    @Override
    public void render() {
        Gui.drawRect(getX(), getY(), getX() + getWidth(), getY() + getHeight(), this.backgroundColor);
        font.drawCenteredString("Example Mod", this.getX() + this.width / 2, (float) (this.y + (this.height - font.FONT_HEIGHT - 1) / 2), -1);
    }

    @Override
    public void renderDummy() {
        Gui.drawRect(getX(), getY(), getX() + getWidth(), getY() + getHeight(), this.backgroundColor);
        font.drawCenteredString("Example Dummy", this.getX() + this.width / 2, (float) (this.y + (this.height - font.FONT_HEIGHT - 1) / 2), -1);
    }
}
