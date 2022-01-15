package net.luxclient.ui.components.buttons;

import net.luxclient.ui.UiComponent;
import net.luxclient.util.ClientGuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;

import java.awt.*;

public class UiButton extends UiComponent {

    protected int id;
    protected String text;

    private int hoverFade = 0;

    public UiButton(int id, int x, int y, String text) {
        super(x, y, 200, 20);

        this.id = id;
        this.text = text;
    }

    public UiButton(int id, int x, int y, int width, int height, String text) {
        super(x, y, width, height);

        this.id = id;
        this.text = text;
    }

    @Override
    public void renderComponent(int mouseX, int mouseY, boolean ingame) {
        if(isHovered(mouseX, mouseY)) {
            if(hoverFade < 25) hoverFade += 5;

        } else {
            if(hoverFade > 0) hoverFade -= 5;

        }

        Color c = new Color(255, 255, 255, 30 + hoverFade);
        Color outline = new Color(255, 255, 255, 26);

        //perfect line width for every gui scale
        int lineWidth = (int) (new ScaledResolution(Minecraft.getMinecraft()).getScaleFactor() * 1.5F);
        ClientGuiUtils.drawRoundedRect(this.x, this.y, this.width, this.height, 3, c);
        ClientGuiUtils.drawRoundedOutline(this.x, this.y, this.width + this.x, this.height + this.y, 5, lineWidth, outline.getRGB());

        this.drawCenteredString(Minecraft.getMinecraft().fontRendererObj, this.text.toUpperCase(), this.width / 2 + this.x, this.y + 5, outline.getRGB());

    }

    public int getId() {
        return id;
    }
}
