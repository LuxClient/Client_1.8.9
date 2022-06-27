package net.luxclient.ui.screens.settings;

import net.luxclient.LuxClient;
import net.luxclient.hud.HudComponent;
import net.luxclient.settings.SettingTabsManager;
import net.luxclient.ui.UiScreen;
import net.luxclient.ui.components.buttons.UiButton;
import net.luxclient.util.ClientGuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

public class UiHudEditor extends UiScreen {

    private List<HudComponent> components;
    private HudComponent dragged = null;
    private int offsetX, offsetY;

    @Override
    public void initComponents() {
        this.components = new LinkedList<>();
        LuxClient.getModuleManager().getModules().forEach(m -> {
            if (m.isEnabled() && m.getHudComponents() != null) {
                components.addAll(m.getHudComponents());
            }
        });

        this.componentList.add(new UiButton(0, this.width / 2 - 30, this.height / 2 + 18, 60, 15, "CLOSE"));
    }

    @Override
    public void renderScreen(int mouseX, int mouseY, boolean ingame) {
        if(ingame) {
            Gui.drawRect(0, 0, this.width, this.height, new Color(0, 0, 0, 80).getRGB());
        }

        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.color((float) ClientGuiUtils.brandingIconColor.getRed() / 255, (float) ClientGuiUtils.brandingIconColor.getGreen() / 255, (float) ClientGuiUtils.brandingIconColor.getBlue() / 255, (float) ClientGuiUtils.brandingIconColor.getAlpha() / 255);

        this.mc.getTextureManager().bindTexture(new ResourceLocation("lux/logo.png"));
        Gui.drawModalRectWithCustomSizedTexture(this.width / 2 - 30, this.height / 2 - 50, 0, 0, 60, 60, 60, 60);
        GlStateManager.popMatrix();

        int snapLineC = new Color(255, 255, 255, 80).getRGB();
        ClientGuiUtils.drawVerticalLine(this.width / 2, 0, this.height, 2.0F, snapLineC, false);
        ClientGuiUtils.drawLine(0, this.width, this.height / 2, 2.0F, snapLineC, false);

        float lineWidth = new ScaledResolution(Minecraft.getMinecraft()).getScaleFactor() / 1.4F;
        ClientGuiUtils.drawOutline(3, 3, this.width - 6, this.height - 6, lineWidth, new Color(255, 70, 70, 100).getRGB());

        for (HudComponent c : this.components) {
            c.renderDummy();
            ClientGuiUtils.drawOutline(c.getX() - 1, c.getY() - 1, c.getWidth() + 2, c.getHeight() + 2, lineWidth, new Color(137, 255, 254, 100).getRGB());
        }

    }

    @Override
    public void buttonClicked(UiButton button) {
        if(button.getId() == 0) {
            SettingTabsManager.openCurrentScreen();
        }
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        this.dragged = getHovered(mouseX, mouseY);

        if (dragged != null) {
            offsetX = mouseX - this.dragged.getX();
            offsetY = mouseY - this.dragged.getY();
        }

        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
        if(this.dragged != null) {
            int x = mouseX - offsetX;
            int y = mouseY - offsetY;

            if (x < 3) x = 3;
            if (y < 3) y = 3;
            if (x > this.width - this.dragged.getWidth() - 3) x = this.width - this.dragged.getWidth() - 3;
            if (y > this.height - this.dragged.getHeight() - 3) y = this.height - this.dragged.getHeight() - 3;

            this.dragged.setPosition(x, y);
        }
        super.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
    }

    private HudComponent getHovered(int mouseX, int mouseY) {
        for (HudComponent c : this.components) {
            if (mouseX > c.getX() &&
                mouseY > c.getY() &&
                mouseX <= c.getX() + c.getWidth() &&
                mouseY <= c.getY() + c.getHeight()
            ) return c;
        }
        return null;
    }

    @Override
    public boolean doesGuiPauseGame() {
        return true;
    }

    @Override
    protected boolean renderCopyrightString() {
        return false;
    }

    @Override
    protected boolean renderVersionString() {
        return false;
    }
}
