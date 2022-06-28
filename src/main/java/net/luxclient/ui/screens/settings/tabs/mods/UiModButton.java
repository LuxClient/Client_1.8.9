package net.luxclient.ui.screens.settings.tabs.mods;

import net.luxclient.LuxClient;
import net.luxclient.module.LuxModule;
import net.luxclient.util.ClientGuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.awt.Color;

public class UiModButton {

    protected LuxModule mod;
    protected int x, y;
    protected int width, height;

    protected double hoverFade = 0.0;

    public UiModButton(LuxModule mod, int x, int y) {
        this.mod = mod;
        this.x = x;
        this.y = y;
        this.width = 70;
        this.height = 65;
    }

    public void render(int mouseX, int mouseY) {
        if(isHovered(mouseX, mouseY)) {
            if(hoverFade < 0.2) {
                hoverFade += 0.005;
            }

        } else {
            if(hoverFade > 0.0) {
                hoverFade -= 0.005;
            }
        }
        System.out.println(hoverFade);

        Color backgroundColor;
        Color borderColor;
        if(this.mod.isEnabled()) {
            backgroundColor = new Color(ClientGuiUtils.brandingEnabledColor.getRed(), ClientGuiUtils.brandingEnabledColor.getGreen(), ClientGuiUtils.brandingEnabledColor.getBlue(), (int) (ClientGuiUtils.brandingEnabledColor.getAlpha() * hoverFade));
            borderColor = ClientGuiUtils.brandingEnabledColor;
        } else {
            backgroundColor = new Color(ClientGuiUtils.brandingDisabledColor.getRed(), ClientGuiUtils.brandingDisabledColor.getGreen(), ClientGuiUtils.brandingDisabledColor.getBlue(), (int) (ClientGuiUtils.brandingDisabledColor.getAlpha() * hoverFade));
            borderColor = ClientGuiUtils.brandingDisabledColor;
        }

        GlStateManager.color(1.0F, 1.0F, 1.0F);
        int outline = (int) (new ScaledResolution(Minecraft.getMinecraft()).getScaleFactor() * 0.5);
        ClientGuiUtils.drawRoundedRect(this.x, this.y, this.width, this.height, 3, ClientGuiUtils.brandingBackgroundColor);

        ClientGuiUtils.drawRoundedRect(this.x, this.y, this.width, this.height, 3, backgroundColor);
        ClientGuiUtils.drawRoundedOutline(this.x, this.y, this.x + this.width, this.y + this.height, 4, outline, borderColor.getRGB());

        GlStateManager.enableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.color((float) ClientGuiUtils.brandingIconColor.getRed() / 255, (float) ClientGuiUtils.brandingIconColor.getGreen() / 255, (float) ClientGuiUtils.brandingIconColor.getBlue() / 255, (float) ClientGuiUtils.brandingIconColor.getAlpha() / 255);
        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("lux/icons/mods/test.png")); //TODO put mod icon here
        Gui.drawModalRectWithCustomSizedTexture(this.x + this.width / 2 - 17, this.y + 4, 0, 0, 34, 34, 34, 34);
        LuxClient.Fonts.text.drawCenteredString(this.mod.getName().toUpperCase(), this.x + this.width / 2, this.y + 40, ClientGuiUtils.brandingIconColor.getRGB());
        GlStateManager.disableAlpha();
        GlStateManager.disableBlend();
    }

    public void onClick() {
        Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
        this.mod.toggleEnabled();
    }

    public boolean isHovered(int mouseX, int mouseY) {
        return mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
