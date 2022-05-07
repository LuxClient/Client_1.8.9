package net.luxclient.ui.screens.settings;

import net.luxclient.LuxClient;
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

        ClientGuiUtils.drawRoundedRect(0, 0, 350, 182, 5, ClientGuiUtils.brandingBackgroundColor);

        ClientGuiUtils.drawRoundedRect(5, 25, 100, 152, 5, ClientGuiUtils.brandingSecondBackgroundColor);
        ClientGuiUtils.drawRoundedRect(110, 25, 235, 152, 5, ClientGuiUtils.brandingSecondBackgroundColor);

        GlStateManager.color(1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(new ResourceLocation("lux/logo/logo.png"));
        Gui.drawModalRectWithCustomSizedTexture(6, 6, 0, 0, 15, 15, 15, 15);

        LuxClient.Fonts.titleBold.drawStringScaled("LUX", 24, 9, -1, 0.53F);
        LuxClient.Fonts.titleThin.drawStringScaled("CLIENT", 42, 9, -1, 0.53F);

        for (UiComponent c : this.draggedComponents) {
            if(c.isVisible()) {
                c.renderComponent(mouseX, mouseY, ingame);
            }
        }

    }

    @Override
    public void initComponents() {
        this.draggedComponents.clear();
        this.draggedComponents.add(new UiImageTextButton(0, 10, 30, 90, 18, "MODS", new ResourceLocation("lux/icons/settings/mods.png")) {
            @Override
            public boolean isHovered(int mouseX, int mouseY) {
                return mouseX >= x + dragX &&
                        mouseY >= y + dragY &&
                        mouseX < x + dragX + this.width &&
                        mouseY < y + dragY + this.height;
            }
        });
        this.draggedComponents.add(new UiImageTextButton(1, 10, 51, 90, 18, "SETTINGS", new ResourceLocation("lux/icons/settings/cog.png")) {
            @Override
            public boolean isHovered(int mouseX, int mouseY) {
                return mouseX >= x + dragX &&
                        mouseY >= y + dragY &&
                        mouseX < x + dragX + this.width &&
                        mouseY < y + dragY + this.height;
            }
        });
        this.draggedComponents.add(new UiImageTextButton(2, 10, 72, 90, 18, "COSMETICS", new ResourceLocation("lux/icons/settings/shirt.png")) {
            @Override
            public boolean isHovered(int mouseX, int mouseY) {
                return mouseX >= x + dragX &&
                        mouseY >= y + dragY &&
                        mouseX < x + dragX + this.width &&
                        mouseY < y + dragY + this.height;
            }
        });

        this.draggedComponents.add(new UiImageTextButton(3, 10, 154, 90, 18, "HUD EDITOR", new ResourceLocation("lux/icons/settings/hud.png")) {
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
    public void buttonClicked(UiButton button) {
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

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        windowDragged = false;
        super.mouseReleased(mouseX, mouseY, state);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        if(keyCode == mc.gameSettings.keyBindings[33].getKeyCode()) {
            mc.displayGuiScreen(null);
        }
        super.keyTyped(typedChar, keyCode);
    }
}
