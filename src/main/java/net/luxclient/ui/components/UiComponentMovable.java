package net.luxclient.ui.components;

import net.luxclient.ui.UiComponent;

public abstract class UiComponentMovable extends UiComponent {

    public UiComponentMovable(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public boolean isHovered(int mouseX, int mouseY, int movedX, int movedY) {
        return mouseX >= x + movedX &&
                mouseY >= y + movedY &&
                mouseX < x + movedX + this.width &&
                mouseY < y + movedY + this.height;
    }
}
