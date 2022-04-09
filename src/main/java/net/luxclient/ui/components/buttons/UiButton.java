package net.luxclient.ui.components.buttons;

import net.luxclient.LuxClient;
import net.luxclient.ui.UiComponent;
import net.luxclient.util.ClientGuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;

import java.awt.Color;

public class UiButton extends UiComponent {

    protected int id;
    protected String text;

    private double hoverFade = 1.0;

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
            if(hoverFade < 1.5) hoverFade += 0.05;

        } else {
            if(hoverFade > 1.0) hoverFade -= 0.05;

        }

        Color c = new Color(ClientGuiUtils.brandingForegroundColor.getRed(), ClientGuiUtils.brandingForegroundColor.getGreen(), ClientGuiUtils.brandingForegroundColor.getBlue(), (int) (ClientGuiUtils.brandingForegroundColor.getAlpha() * hoverFade));
        float lineWidth = new ScaledResolution(Minecraft.getMinecraft()).getScaleFactor();
        ClientGuiUtils.drawRoundedRect(this.x, this.y, this.width, this.height, 3, c);
        ClientGuiUtils.drawRoundedOutline(this.x, this.y, this.x + this.width, this.y + this.height, 4, lineWidth, ClientGuiUtils.brandingForegroundOutline.getRGB());

        LuxClient.Fonts.text.drawCenteredString(this.text.toUpperCase(), this.x + this.width / 2, this.y + (this.height - 7) / 2, ClientGuiUtils.brandingIconColor.getRGB());
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
