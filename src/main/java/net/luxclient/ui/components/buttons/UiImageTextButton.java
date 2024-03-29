package net.luxclient.ui.components.buttons;

import net.luxclient.LuxClient;
import net.luxclient.util.ClientGuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

import java.awt.Color;

public class UiImageTextButton extends UiButton {

    protected ResourceLocation image;

    private double hoverFade = 1.0;

    public UiImageTextButton(int id, int x, int y, String text, ResourceLocation image) {
        super(id, x, y, text);
        this.image = image;
        this.width = 160;
        this.height = 18;
    }

    public UiImageTextButton(int id, int x, int y, int width, int height, String text, ResourceLocation image) {
        super(id, x, y, width, height, text);
        this.image = image;
        this.width = width;
        this.height = height;
    }

    @Override
    public void renderComponent(int mouseX, int mouseY, boolean ingame) {
        if(isHovered(mouseX, mouseY)) {
            if(hoverFade < 1.5) hoverFade += 0.05;

        } else {
            if(hoverFade > 1.0) hoverFade -= 0.05;

        }

        Color c = new Color(ClientGuiUtils.brandingForegroundColor.getRed(), ClientGuiUtils.brandingForegroundColor.getGreen(), ClientGuiUtils.brandingForegroundColor.getBlue(), (int) (ClientGuiUtils.brandingForegroundColor.getAlpha() * hoverFade));
        float lineWidth = new ScaledResolution(Minecraft.getMinecraft()).getScaleFactor() / 2;
        ClientGuiUtils.drawRoundedRect(this.x, this.y, this.width, this.height, 3, c);
        ClientGuiUtils.drawRoundedOutline(this.x, this.y, this.x + this.width, this.y + this.height, 4, lineWidth, ClientGuiUtils.brandingForegroundOutline.getRGB());

        GlStateManager.enableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.color((float) ClientGuiUtils.brandingIconColor.getRed() / 255, (float) ClientGuiUtils.brandingIconColor.getGreen() / 255, (float) ClientGuiUtils.brandingIconColor.getBlue() / 255, (float) ClientGuiUtils.brandingIconColor.getAlpha() / 255);
        Minecraft.getMinecraft().getTextureManager().bindTexture(image);
        Gui.drawModalRectWithCustomSizedTexture(this.x + 4, this.y + 4, 0, 0, this.height - 8, this.height - 8, this.height - 8, this.height - 8);

        LuxClient.Fonts.text.drawString(this.text.toUpperCase(), this.x + this.height, (float) (this.y + (this.height - 6.5) / 2), ClientGuiUtils.brandingIconColor.getRGB());
        GlStateManager.disableAlpha();
        GlStateManager.disableBlend();
    }
}
