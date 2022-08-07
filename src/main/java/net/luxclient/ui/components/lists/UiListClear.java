package net.luxclient.ui.components.lists;

import net.luxclient.ui.UiComponent;
import net.luxclient.util.ScissorsUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import javax.swing.plaf.basic.BasicTreeUI;
import java.util.List;

public class UiListClear extends UiComponent {

    protected static final float SCROLL_INTENSITY = 12.0F;

    protected final Minecraft mc = Minecraft.getMinecraft();

    protected int entryHeight;
    protected List<UiListEntry> entries;
    protected int mouseX, mouseY;

    protected float scrollingHeight;

    public UiListClear(int x, int y, int width, int height, int entryHeight, List<UiListEntry> entries) {
        super(x, y, width, height);
        this.entryHeight = entryHeight;
        this.entries = entries;
    }

    @Override
    public void renderComponent(int mouseX, int mouseY, boolean ingame) {
        this.mouseX = mouseX;
        this.mouseY = mouseY;

        ScaledResolution res = new ScaledResolution(mc);

        int bottom = -(entryHeight * entries.size() - height);
        if (scrollingHeight < bottom) scrollingHeight = bottom;
        if (scrollingHeight > 0) scrollingHeight = 0;

        GL11.glPushMatrix();
        ScissorsUtil.enable();
        GL11.glTranslatef(0, scrollingHeight, 0);

        int slotHeight = 0;
        for(UiListEntry e : this.entries) {

            if(e.isSelected()) {

            }

            e.renderEntry(this.x, this.y + slotHeight, this.width, this.entryHeight, entries.indexOf(e));
            slotHeight += entryHeight;

        }

        ScissorsUtil.select(this.x, this.y, this.width, this.height);
        ScissorsUtil.disable();
        GL11.glPopMatrix();
    }

    public void keyTyped(int keyCode) {
        if (keyCode == Keyboard.KEY_UP)
            scrollingHeight += SCROLL_INTENSITY;

        if (keyCode == Keyboard. KEY_DOWN)
            scrollingHeight -= SCROLL_INTENSITY;
    }

    public void handleMouseInput() {
        if (this.isHovered(mouseX, mouseY))
            scrollingHeight += Integer.compare(Mouse.getEventDWheel(), 0) * SCROLL_INTENSITY;
    }

}
