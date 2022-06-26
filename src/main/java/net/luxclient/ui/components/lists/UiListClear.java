package net.luxclient.ui.components.lists;

import net.luxclient.ui.UiScreen;
import net.luxclient.ui.components.buttons.UiButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import java.io.IOException;
import java.util.List;

public abstract class UiListClear extends UiScreen {

    protected int x, y;
    protected int width, height;
    protected int entryHeight;
    protected List<UiListEntry> entries;

    protected float scrollingHeight;

    public UiListClear(int x, int y, int width, int height, int entryHeight, List<UiListEntry> entries) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.entryHeight = entryHeight;
        this.entries = entries;
    }

    @Override
    public void renderScreen(int mouseX, int mouseY, boolean ingame) {
        mc = Minecraft.getMinecraft();
        ScaledResolution res = new ScaledResolution(mc);

        GL11.glPushMatrix();
        //GL11.glEnable(GL11.GL_SCISSOR_TEST);
        GL11.glTranslatef(0, scrollingHeight, 0);

        int slotHeight = 0;
        for(UiListEntry e : this.entries) {

            if(e.isSelected()) {

            }

            e.renderEntry(this.x, this.y + slotHeight, this.width, this.entryHeight, entries.indexOf(e));
            slotHeight += entryHeight;

        }

        /*GL11.glScissor((int) ((x * mc.displayWidth) / res.getScaledWidth()),
                (int) (((res.getScaledHeight() - y) * mc.displayHeight) / res.getScaledHeight()),
                (int) (width * mc.displayWidth / res.getScaledWidth()),
                (int) (height * mc.displayHeight / res.getScaledHeight()));*/
        //GL11.glDisable(GL11.GL_SCISSOR_TEST);
        GL11.glPopMatrix();
    }

    @Override
    public void initComponents() {

    }

    @Override
    public void buttonClicked(UiButton button) {

    }

    @Override
    public void handleMouseInput() throws IOException {
        super.handleMouseInput();
        scrollingHeight += Integer.compare(Mouse.getEventDWheel(), 0) * 5F;
    }
}
