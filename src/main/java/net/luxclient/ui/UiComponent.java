package net.luxclient.ui;

import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.gui.Gui;

public abstract class UiComponent extends Gui {

    @Getter
    protected int x, y;
    @Getter
    protected int width, height;
    @Getter @Setter
    protected boolean enabled;
    @Getter @Setter
    protected boolean visible = true;

    public UiComponent(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public abstract void renderComponent(int mouseX, int mouseY, boolean ingame);

    public void mouseClicked(int mouseButton, int mouseX, int mouseY) {}

    public boolean isHovered(int mouseX, int mouseY) {
        return mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

}
