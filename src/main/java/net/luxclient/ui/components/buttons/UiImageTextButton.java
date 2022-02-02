package net.luxclient.ui.components.buttons;

import net.luxclient.LuxClient;
import net.luxclient.util.ClientGuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;

import java.awt.Color;

public class UiImageTextButton extends UiImageButton {

    private int hoverFade = 0;

    public UiImageTextButton(int id, int x, int y, String text, ResourceLocation image) {
        super(id, x, y, image);
        this.text = text;
        this.width = 160;
        this.height = 18;
    }

    public UiImageTextButton(int id, int x, int y, int width, int height, String text, ResourceLocation image) {
        super(id, x, y, 0, image);
        this.text = text;
        this.width = width;
        this.height = height;
    }

    @Override
    public void renderComponent(int mouseX, int mouseY, boolean ingame) {
        if(isHovered(mouseX, mouseY)) {
            if(hoverFade < 25) hoverFade += 5;

        } else {
            if(hoverFade > 0) hoverFade -= 5;

        }

        ClientGuiUtils.drawRoundedRect(this.x, this.y, this.width, this.height, 4, new Color(255, 255, 255, 26 + hoverFade));

        Minecraft.getMinecraft().getTextureManager().bindTexture(image);
        Gui.drawModalRectWithCustomSizedTexture(this.x + 4, this.y + 4, 0, 0, this.height - 8, this.height - 8, this.height - 8, this.height - 8);

        LuxClient.Fonts.text.drawString(this.text.toUpperCase(), this.x + this.height, this.y + this.height / 2 - LuxClient.Fonts.text.FONT_HEIGHT / 2, -1);
    }
}
