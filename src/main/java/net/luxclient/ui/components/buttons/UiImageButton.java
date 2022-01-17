package net.luxclient.ui.components.buttons;

import net.luxclient.util.ClientGuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
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
            if(hoverFade < 25) ++hoverFade;

        } else {
            if(hoverFade > 0) --hoverFade;

        }

        Color c = new Color(255, 255, 255, 30 + hoverFade);
        Color outline = new Color(255, 255, 255, 26);

        //perfect line width for every gui scale
        int lineWidth = (int) (new ScaledResolution(Minecraft.getMinecraft()).getScaleFactor() * 1.5F);
        ClientGuiUtils.drawRoundedRect(this.x, this.y, this.width, this.height, 2, c);
        ClientGuiUtils.drawRoundedOutline(this.x, this.y, this.width + this.x, this.height + this.y, 4, lineWidth, outline.getRGB());

        Minecraft.getMinecraft().getTextureManager().bindTexture(image);
        Gui.drawModalRectWithCustomSizedTexture(this.x + 4, this.y + 4, 0, 0, this.width - 8, this.width - 8, this.width - 8, this.width - 8);
    }
}
