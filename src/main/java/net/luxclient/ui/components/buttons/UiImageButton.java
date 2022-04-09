package net.luxclient.ui.components.buttons;

import net.luxclient.util.ClientGuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

import java.awt.*;

public class UiImageButton extends UiButton {

    protected ResourceLocation image;

    private double hoverFade = 1.0;

    public UiImageButton(int id, int x, int y, ResourceLocation image) {
        super(id, x, y, 18, 18, "");
        this.image = image;
    }

    public UiImageButton(int id, int x, int y, int size, ResourceLocation image) {
        super(id, x, y, size, size, "");
        this.image = image;
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

        GlStateManager.color(ClientGuiUtils.brandingIconColor.getRed(), ClientGuiUtils.brandingIconColor.getGreen(), ClientGuiUtils.brandingIconColor.getBlue(), ClientGuiUtils.brandingIconColor.getAlpha());
        Minecraft.getMinecraft().getTextureManager().bindTexture(image);
        Gui.drawModalRectWithCustomSizedTexture(this.x + 4, this.y + 4, 0, 0, this.width - 8, this.width - 8, this.width - 8, this.width - 8);
    }
}
