package net.luxclient.ui.components.buttons;

import net.luxclient.ui.UiComponent;
import net.luxclient.util.ClientGuiUtils;
import net.minecraft.client.Minecraft;

import java.awt.*;

public class UiButton extends UiComponent {

    private int id;
    private String text;

    private int hoverFade = 0;

    public UiButton(int id, int x, int y, String text) {
        super(x, y, 180, 18);

        this.id = id;
        this.text = text;
    }

    @Override
    public void renderComponent(int mouseX, int mouseY, boolean ingame) {
        if(isHovered(mouseX, mouseY)) {
            if(hoverFade < 10) hoverFade += 5;

        } else {
            if(hoverFade > 0) hoverFade -= 5;

        }

        Color c = new Color(255, 255, 255, 30 + hoverFade);
        Color outline = new Color(255, 255, 255, 10 + hoverFade);

        ClientGuiUtils.drawRoundedRect(this.x, this.y, this.width, this.height, 3, c);
        ClientGuiUtils.drawRoundedOutline(this.x, this.y, this.width + this.x, this.height + this.y, 5, 3.0F, outline.getRGB());

        this.drawCenteredString(Minecraft.getMinecraft().fontRendererObj, this.text.toUpperCase(), this.width / 2, this.y + 10, -1);

    }
}
