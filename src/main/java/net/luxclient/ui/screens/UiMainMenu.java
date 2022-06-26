package net.luxclient.ui.screens;

import net.luxclient.LuxClient;
import net.luxclient.settings.SettingTabsManager;
import net.luxclient.ui.UiScreen;
import net.luxclient.ui.components.UiAccountPanel;
import net.luxclient.ui.components.buttons.UiButton;
import net.luxclient.ui.components.buttons.UiImageButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class UiMainMenu extends UiScreen {

    private UiAccountPanel accountPanel;

    @Override
    public void renderScreen(int mouseX, int mouseY, boolean ingame) {
        this.mc.getTextureManager().bindTexture(new ResourceLocation("lux/logo.png"));
        GL11.glColor3f(1.0F, 1.0F, 1.0F);
        Gui.drawModalRectWithCustomSizedTexture(this.width / 2 - 26, this.height / 2 - 80, 0, 0, 52, 52, 52, 52);

        LuxClient.Fonts.titleBold.drawCenteredTextScaled(LuxClient.NAME.toUpperCase(), this.width / 2, this.height / 2 - 28, -1, 0.6F);
    }

    @Override
    public void initComponents() {
        this.componentList.add(new UiButton(0, this.width / 2 - 70, this.height / 2 - 10, 140, 18, "Singleplayer"));
        this.componentList.add(new UiButton(1, this.width / 2 - 70, this.height / 2 + 12 , 140, 18, "Multiplayer"));

        int buttonY = this.height - 23;

        int displayTextX = 5;
        int displayTextY = this.height - 35;
        this.componentList.add(new UiImageButton(5, 5, buttonY, new ResourceLocation("lux/icons/main_menu/cart.png"), displayTextX, displayTextY, "Store"));
        this.componentList.add(new UiImageButton(7, 28, buttonY, new ResourceLocation("lux/icons/main_menu/changelog.png"), displayTextX, displayTextY, "Patch Notes"));
        this.componentList.add(new UiImageButton(2, 51, buttonY, new ResourceLocation("lux/icons/main_menu/bulb.png"), displayTextX, displayTextY, "Lux Setings"));
        this.componentList.add(new UiImageButton(3, 74, buttonY, new ResourceLocation("lux/icons/main_menu/cog.png"), displayTextX, displayTextY, "Game Settings"));

        this.componentList.add(new UiImageButton(6, this.width - 23, 5, new ResourceLocation("lux/icons/close.png"), this.width - 21, 28, "Quit"));

        this.accountPanel = new UiAccountPanel(this.width - 128, 5, Minecraft.getMinecraft().getSession());
        this.componentList.add(accountPanel);
    }

    @Override
    public void buttonClicked(UiButton button) {
        switch (button.getId()) {
            case 0:
                this.mc.displayGuiScreen(new GuiSelectWorld(this));
                break;

            case 1:
                this.mc.displayGuiScreen(new GuiMultiplayer(this));
                break;

            case 2:
                SettingTabsManager.openCurrentScreen();
                break;

            case 3:
                this.mc.displayGuiScreen(new GuiOptions(this, mc.gameSettings));
                break;

            case 4:
                this.mc.displayGuiScreen(new GuiLanguage(this, mc.gameSettings, mc.getLanguageManager()));
                break;

            case 5:
                String url = "https://www.youtube.com/watch?v=dQw4w9WgXcQ";
                try {
                    Desktop.getDesktop().browse(new URI(url));
                } catch (URISyntaxException | IOException e) {
                    System.err.println("Unable to open link: " + url);
                }
                break;

            case 6:
                this.mc.shutdown();
                break;

            case 7:
                this.mc.displayGuiScreen(new UiPatchNotes());
                break;

            default:
                break;
        }
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        this.accountPanel.mouseClicked(mouseButton, mouseX, mouseY);
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    protected boolean renderVersionString() {
        return false;
    }

}
