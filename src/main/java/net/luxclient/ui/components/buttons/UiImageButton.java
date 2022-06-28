package net.luxclient.ui.components.buttons;

import net.luxclient.LuxClient;
import net.luxclient.util.ClientGuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

import java.awt.Color;

public class UiImageButton extends UiButton {

    protected ResourceLocation image;

    protected int textX, textY;

    private double hoverFade = 1.0;

    public UiImageButton(int id, int x, int y, ResourceLocation image, int textX, int textY, String displayText) {
        super(id, x, y, 18, 18, displayText);
        this.image = image;
        this.textX = textX;
        this.textY = textY;
    }

    public UiImageButton(int id, int x, int y, int size, ResourceLocation image, int textX, int textY, String displayText) {
        super(id, x, y, size, size, "");
        this.image = image;
        this.textX = textX;
        this.textY = textY;
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

        GlStateManager.enableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.color((float) ClientGuiUtils.brandingIconColor.getRed() / 255, (float) ClientGuiUtils.brandingIconColor.getGreen() / 255, (float) ClientGuiUtils.brandingIconColor.getBlue() / 255, (float) ClientGuiUtils.brandingIconColor.getAlpha() / 255);
        Minecraft.getMinecraft().getTextureManager().bindTexture(image);
        Gui.drawModalRectWithCustomSizedTexture(this.x + 4, this.y + 4, 0, 0, this.width - 8, this.width - 8, this.width - 8, this.width - 8);
        GlStateManager.disableAlpha();
        GlStateManager.disableBlend();

        if(!this.text.equals("") && isHovered(mouseX, mouseY))
            this.renderDisplayText();
    }

    private void renderDisplayText() {
        int textWidth = (int) (LuxClient.Fonts.text.getWidth(this.text.toUpperCase()));
        int textHeight = 7;

        ClientGuiUtils.drawRoundedRect(this.textX - 2, this.textY - 1, textWidth + 4, textHeight + 2, 3, ClientGuiUtils.brandingForegroundColor);
        LuxClient.Fonts.text.drawString(this.text.toUpperCase(), this.textX, this.textY, ClientGuiUtils.brandingIconColor.getRGB());
    }

}
