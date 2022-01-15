package net.luxclient.ui.screens;

import net.luxclient.LuxClient;
import net.luxclient.ui.UiScreen;
import net.luxclient.ui.components.buttons.UiButton;
import net.luxclient.ui.components.buttons.UiImageButton;
import net.minecraft.client.gui.*;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class UiMainMenu extends UiScreen {

    @Override
    public void renderScreen(int mouseX, int mouseY, boolean ingame) {
        this.mc.getTextureManager().bindTexture(new ResourceLocation("lux/logo/logo.png"));
        GL11.glColor3f(1.0F, 1.0F, 1.0F);
        Gui.drawModalRectWithCustomSizedTexture(this.width / 2 - 25, this.height/ 2 - 30 - 50, 0, 0, 50, 50, 50, 50);
        this.drawCenteredString(mc.fontRendererObj, LuxClient.NAME.toUpperCase(), this.width / 2, this.height / 2 - 27, -1);
    }

    @Override
    public void initComponents() {
        this.componentList.add(new UiButton(0, this.width / 2 - 82, this.height / 2 - 10, 164, 18, "Singleplayer"));
        this.componentList.add(new UiButton(1, this.width / 2 - 82, this.height / 2 + 12 , 164, 18, "Multiplayer"));

        int buttonY = this.height - 24;
        this.componentList.add(new UiImageButton(2, this.width / 2 - 9, buttonY, new ResourceLocation("lux/icons/main_menu/lux_settings.png")));
        this.componentList.add(new UiImageButton(3, this.width / 2 - 9 - 18 - 3, buttonY, new ResourceLocation("lux/icons/main_menu/settings.png")));
        this.componentList.add(new UiImageButton(4, this.width / 2 - 9 + 18 + 3, buttonY, new ResourceLocation("lux/icons/main_menu/language.png")));
        this.componentList.add(new UiImageButton(5, this.width / 2 - 9 - 18 - 3 - 18 - 3, buttonY, new ResourceLocation("lux/icons/main_menu/store.png")));
        this.componentList.add(new UiImageButton(6, this.width / 2 - 9 + 18 + 3 + 18 + 3, buttonY, new ResourceLocation("lux/icons/main_menu/close.png")));

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
                break;

            case 3:
                this.mc.displayGuiScreen(new GuiOptions(this, mc.gameSettings));
                break;

            case 4:
                this.mc.displayGuiScreen(new GuiLanguage(this, mc.gameSettings, mc.getLanguageManager()));
                break;

            case 5:
                try {
                    Desktop.getDesktop().browse(new URI("https://www.luxclient.net/store/"));
                } catch (URISyntaxException | IOException e) {
                    System.err.println("Unable to open URL!");
                }
                break;

            case 6:
                this.mc.shutdown();

            default:
                break;
        }
    }

}
