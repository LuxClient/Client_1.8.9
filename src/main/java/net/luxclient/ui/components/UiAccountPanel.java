package net.luxclient.ui.components;

import net.luxclient.LuxClient;
import net.luxclient.ui.UiComponent;
import net.luxclient.ui.screens.account.UiLoginSelection;
import net.luxclient.util.ClientGuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Session;

import java.awt.Color;

public class UiAccountPanel extends UiComponent {

    private Session account;

    private double hoverFade = 1.0;

    public UiAccountPanel(int x, int y, Session session) {
        super(x, y, 100, 18);
        this.account = session;
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

        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("lux/steve.png"));
        GlStateManager.color(1.0F, 1.0F, 1.0F);
        Gui.drawModalRectWithCustomSizedTexture(this.x + 3, this.y + 3, 0, 0, 12, 12, 12, 12);

        LuxClient.Fonts.text.drawString(this.account.getUsername(), this.x + 18, (float) (this.y + (this.height - 6.5) / 2), ClientGuiUtils.brandingIconColor.getRGB());

    }

    @Override
    public void mouseClicked(int mouseButton, int mouseX, int mouseY) {
        if(this.isHovered(mouseX, mouseY)) {
            Minecraft.getMinecraft().displayGuiScreen(new UiLoginSelection());
        }
        super.mouseClicked(mouseButton, mouseX, mouseY);
    }

}
