package net.luxclient.ui.components.buttons;

import lombok.Getter;
import lombok.Setter;
import net.luxclient.LuxClient;
import net.luxclient.util.ClientGuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class UiImageButtonClear extends UiButton {

    @Getter @Setter
    protected ResourceLocation image;

    @Getter
    protected int textX, textY;

    private double hoverFade = 1.0;

    public UiImageButtonClear(int id, int x, int y, ResourceLocation image, int textX, int textY, String displayText) {
        super(id, x, y, 14, 14, displayText);
        this.image = image;
        this.textX = textX;
        this.textY = textY;
    }

    public UiImageButtonClear(int id, int x, int y, int size, ResourceLocation image, int textX, int textY, String displayText) {
        super(id, x, y, size, size, "");
        this.image = image;
        this.textX = textX;
        this.textY = textY;
    }

    @Override
    public void renderComponent(int mouseX, int mouseY, boolean ingame) {
        if(isVisible()) {
            if(isHovered(mouseX, mouseY)) {
                if(hoverFade < 1.5) hoverFade += 0.05;

            } else {
                if(hoverFade > 1.0) hoverFade -= 0.05;

            }
            GlStateManager.enableBlend();
            GlStateManager.enableAlpha();
            GlStateManager.color((float) ClientGuiUtils.brandingIconColor.getRed() / 255, (float) ClientGuiUtils.brandingIconColor.getGreen() / 255, (float) ClientGuiUtils.brandingIconColor.getBlue() / 255, (float) (ClientGuiUtils.brandingIconColor.getAlpha() * hoverFade) / 255);
            Minecraft.getMinecraft().getTextureManager().bindTexture(image);
            Gui.drawModalRectWithCustomSizedTexture(this.x, this.y, 0, 0, this.width, this.width, this.width, this.width);
            GlStateManager.disableAlpha();
            GlStateManager.disableBlend();

            if(!this.text.equals("") && isHovered(mouseX, mouseY))
                this.renderDisplayText();
        }
    }

    private void renderDisplayText() {
        int textWidth = (int) (LuxClient.Fonts.text.getWidth(this.text.toUpperCase()));
        int textHeight = 7;

        ClientGuiUtils.drawRoundedRect(this.textX - 2, this.textY - 1, textWidth + 4, textHeight + 2, 3, ClientGuiUtils.brandingForegroundColor);
        LuxClient.Fonts.text.drawString(this.text.toUpperCase(), this.textX, this.textY, ClientGuiUtils.brandingIconColor.getRGB());
    }

    public void setTextCoords(int x, int y) {
        this.textX = x;
        this.textY = y;
    }

}
