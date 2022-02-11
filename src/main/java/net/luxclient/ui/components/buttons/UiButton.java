package net.luxclient.ui.components.buttons;

import net.luxclient.LuxClient;
import net.luxclient.ui.UiComponent;
import net.luxclient.util.ClientGuiUtils;

import java.awt.Color;

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

        Color c = new Color(ClientGuiUtils.brandingForegroundColor.getRed(), ClientGuiUtils.brandingForegroundColor.getGreen(), ClientGuiUtils.brandingForegroundColor.getBlue(), ClientGuiUtils.brandingForegroundColor.getAlpha() + hoverFade);
        ClientGuiUtils.drawRoundedRect(this.x, this.y, this.width, this.height, 4, c);

        LuxClient.Fonts.text.drawCenteredString(this.text.toUpperCase(), this.x + this.width / 2, this.y + (this.height - 10) / 2, -1);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getId() {
        return id;
    }
}
