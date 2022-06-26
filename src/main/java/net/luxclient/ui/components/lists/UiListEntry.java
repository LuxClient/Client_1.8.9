package net.luxclient.ui.components.lists;

import lombok.Getter;
import lombok.Setter;

public abstract class UiListEntry {

    @Getter @Setter
    protected boolean selected = false;

    public abstract void renderEntry(int x, int y, int width, int height, int index);

}
