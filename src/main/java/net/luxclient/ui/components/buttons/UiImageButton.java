package net.luxclient.ui.components.buttons;

import net.luxclient.util.ClientGuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

import java.awt.*;

public class UiImageButton extends UiButton {

    protected ResourceLocation image;

    private int hoverFade;

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
            if(hoverFade < 25) hoverFade += 5;

        } else {
            if(hoverFade > 0) hoverFade -= 5;

        }

        Color c = new Color(ClientGuiUtils.brandingForegroundColor.getRed(), ClientGuiUtils.brandingForegroundColor.getGreen(), ClientGuiUtils.brandingForegroundColor.getBlue(), ClientGuiUtils.brandingForegroundColor.getAlpha() + hoverFade);
        ClientGuiUtils.drawRoundedRect(this.x, this.y, this.width, this.height, 4, c);

        GlStateManager.color(1.0F, 1.0F, 1.0F);
        Minecraft.getMinecraft().getTextureManager().bindTexture(image);
        Gui.drawModalRectWithCustomSizedTexture(this.x + 4, this.y + 4, 0, 0, this.width - 8, this.width - 8, this.width - 8, this.width - 8);
    }
}
