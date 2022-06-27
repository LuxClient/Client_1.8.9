package net.luxclient.ui.screens.settings;

import net.luxclient.LuxClient;
import net.luxclient.hud.HudComponent;
import net.luxclient.module.LuxModule;
import net.luxclient.settings.SettingTabsManager;
import net.luxclient.ui.UiComponent;
import net.luxclient.ui.UiScreen;
import net.luxclient.ui.components.buttons.UiButton;
import net.luxclient.ui.components.buttons.UiImageButtonClear;
import net.luxclient.ui.components.buttons.UiImageTextButton;
import net.luxclient.util.ClientGuiUtils;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public abstract class UiSettingsTab extends UiScreen {

    protected static int dragX = -100, dragY = -100;
    private static boolean windowLocked = false;
    private boolean windowDragged = false;

    private int offsetX, offsetY;
    protected List<UiComponent> draggedComponents;

    private UiImageButtonClear lockButton;

    protected abstract String getWindowName();

    public UiSettingsTab() {
        this.draggedComponents = new LinkedList<>();
    }

    @Override
    public void renderScreen(int mouseX, int mouseY, boolean ingame) {
        this.renderHud();

        if(windowDragged) {
                dragX = mouseX - offsetX;
                dragY = mouseY - offsetY;
        } else {
            offsetX = mouseX - dragX;
            offsetY = mouseY - dragY;
        }

        GL11.glPushMatrix();
        GlStateManager.enableAlpha();
        GL11.glTranslatef(dragX, dragY, this.zLevel);

        ClientGuiUtils.drawRoundedRect(0, 0, 350, 184, 5, ClientGuiUtils.brandingBackgroundColor);

        ClientGuiUtils.drawRoundedRect(5, 25, 100, 154, 5, ClientGuiUtils.brandingSecondBackgroundColor);
        ClientGuiUtils.drawRoundedRect(110, 25, 235, 154, 5, ClientGuiUtils.brandingSecondBackgroundColor);

        LuxClient.Fonts.textBold.drawString(this.getWindowName().toUpperCase(), 115, 29, ClientGuiUtils.brandingIconColor.getRGB());

        GlStateManager.color(1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(new ResourceLocation("lux/logo.png"));
        Gui.drawModalRectWithCustomSizedTexture(6, 6, 0, 0, 15, 15, 15, 15);

        LuxClient.Fonts.titleBold.drawStringScaled("LUX", 24, 8, -1, 0.53F);
        LuxClient.Fonts.titleThin.drawStringScaled("CLIENT", 40, 8, -1, 0.53F);

        for (UiComponent c : this.draggedComponents) {
            if(c.isVisible()) {
                c.renderComponent(mouseX, mouseY, ingame);
            }
        }

    }

    @Override
    public void initComponents() {
        this.draggedComponents.clear();
        this.draggedComponents.add(new UiImageTextButton(0, 10, 30, 90, 17, "MODS", new ResourceLocation("lux/icons/settings/mods.png")) {
            @Override
            public boolean isHovered(int mouseX, int mouseY) {
                return mouseX >= x + dragX &&
                        mouseY >= y + dragY &&
                        mouseX < x + dragX + this.width &&
                        mouseY < y + dragY + this.height;
            }
        });
        this.draggedComponents.add(new UiImageTextButton(1, 10, 50, 90, 17, "SETTINGS", new ResourceLocation("lux/icons/settings/cog.png")) {
            @Override
            public boolean isHovered(int mouseX, int mouseY) {
                return mouseX >= x + dragX &&
                        mouseY >= y + dragY &&
                        mouseX < x + dragX + this.width &&
                        mouseY < y + dragY + this.height;
            }
        });
        this.draggedComponents.add(new UiImageTextButton(2, 10, 70, 90, 17, "COSMETICS", new ResourceLocation("lux/icons/settings/shirt.png")) {
            @Override
            public boolean isHovered(int mouseX, int mouseY) {
                return mouseX >= x + dragX &&
                        mouseY >= y + dragY &&
                        mouseX < x + dragX + this.width &&
                        mouseY < y + dragY + this.height;
            }
        });
        this.draggedComponents.add(new UiImageTextButton(6, 10, 90, 90, 17, "WAYPOINTS", new ResourceLocation("lux/icons/settings/marker.png")) {
            @Override
            public boolean isHovered(int mouseX, int mouseY) {
                return mouseX >= x + dragX &&
                        mouseY >= y + dragY &&
                        mouseX < x + dragX + this.width &&
                        mouseY < y + dragY + this.height;
            }
        });
        this.draggedComponents.add(new UiImageTextButton(7, 10, 110, 90, 17, "SCREENSHOTS", new ResourceLocation("lux/icons/settings/camera.png")) {
            @Override
            public boolean isHovered(int mouseX, int mouseY) {
                return mouseX >= x + dragX &&
                        mouseY >= y + dragY &&
                        mouseX < x + dragX + this.width &&
                        mouseY < y + dragY + this.height;
            }
        });

        this.draggedComponents.add(new UiImageTextButton(3, 10, 157, 90, 17, "HUD EDITOR", new ResourceLocation("lux/icons/settings/hud.png")) {
            @Override
            public boolean isHovered(int mouseX, int mouseY) {
                return mouseX >= x + dragX &&
                        mouseY >= y + dragY &&
                        mouseX < x + dragX + this.width &&
                        mouseY < y + dragY + this.height;
            }
        });

        this.draggedComponents.add(new UiImageButtonClear(4, 331, 6, new ResourceLocation("lux/icons/close.png"), 0, 0, "") {
            @Override
            public boolean isHovered(int mouseX, int mouseY) {
                return mouseX >= x + dragX &&
                        mouseY >= y + dragY &&
                        mouseX < x + dragX + this.width &&
                        mouseY < y + dragY + this.height;
            }
        });
        
        this.loadLockButton();
    }

    @Override
    public void initGui() {
        if(dragX == -100) dragX = this.width / 2 - 175;
        if(dragY == -100) dragY = this.height / 2 - 91;

        super.initGui();
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (mouseButton == 0)
        {
            boolean buttonHovered = false;

            for (int i = 0; i < this.draggedComponents.size(); ++i)
            {
                if(this.draggedComponents.get(i) instanceof UiButton) {

                    UiButton button = (UiButton) this.draggedComponents.get(i);

                    if (button.isHovered(mouseX, mouseY))
                    {
                        buttonHovered = true;
                        this.mc.getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
                        this.buttonClicked(button);
                    }
                }
            }
            if(buttonHovered || windowLocked) return;

            if(mouseX >= dragX - 1 && mouseY >= dragY - 1 && mouseX < dragX + 352 && mouseY < dragY + 27) {
                windowDragged = true;
            }

        }
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        windowDragged = false;
        super.mouseReleased(mouseX, mouseY, state);
    }

    @Override
    public void buttonClicked(UiButton button) {
        if(button.getId() == 0) {
            SettingTabsManager.setCurrentTab(SettingTabsManager.TAB_MODS);
        }
        if(button.getId() == 1) {
            SettingTabsManager.setCurrentTab(SettingTabsManager.TAB_SETTINGS);
        }
        if(button.getId() == 2) {
            SettingTabsManager.setCurrentTab(SettingTabsManager.TAB_COSMETICS);
        }
        if(button.getId() == 6) {
            SettingTabsManager.setCurrentTab(SettingTabsManager.TAB_WAYPOINTS);
        }
        if(button.getId() == 7) {
            SettingTabsManager.setCurrentTab(SettingTabsManager.TAB_SCREENSHOTS);
        }

        if(button.getId() == 3) {
            this.mc.displayGuiScreen(new UiHudEditor());
        }

        if(button.getId() == 4) {
            this.mc.displayGuiScreen(null);
        }

        if(button.getId() == 5) {
            windowLocked = !windowLocked;
            this.draggedComponents.remove(this.lockButton);
            this.loadLockButton();
        }
    }

    private void loadLockButton() {
        ResourceLocation icon = new ResourceLocation("lux/icons/settings/lock_close.png");
        String text = "LOCK GUI";
        int textX = 304;
        if(windowLocked) {
            icon = new ResourceLocation("lux/icons/settings/lock_open.png");
            text = "UNLOCK GUI";
            textX = 300;
        }
        this.draggedComponents.add(this.lockButton = new UiImageButtonClear(5, 312, 6, icon, textX, -5, text) {
            @Override
            public boolean isHovered(int mouseX, int mouseY) {
                return mouseX >= x + dragX &&
                        mouseY >= y + dragY &&
                        mouseX < x + dragX + this.width &&
                        mouseY < y + dragY + this.height;
            }
        });
    }

    private void renderHud() {
        for (LuxModule module : LuxClient.getModuleManager().getModules()) {
            if (!module.isEnabled() || module.getHudComponents() == null)
                continue;
            for (HudComponent component : module.getHudComponents()) {
                component.renderDummy();
            }
        }
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        if(keyCode == mc.gameSettings.keyBindings[33].getKeyCode()) {
            mc.displayGuiScreen(null);
        }
        super.keyTyped(typedChar, keyCode);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return true;
    }
}
