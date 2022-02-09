package net.luxclient.ui.screens.account;

import net.luxclient.LuxClient;
import net.luxclient.ui.UiScreen;
import net.luxclient.ui.components.buttons.UiButton;
import net.luxclient.util.ClientGuiUtils;
import org.lwjgl.input.Keyboard;

import java.awt.Color;
import java.io.IOException;

public class UiLogin extends UiScreen {

    private LoginMethod loginMethod;

    public UiLogin(LoginMethod loginMethod) {
        this.loginMethod = loginMethod;
    }

    @Override
    public void renderScreen(int mouseX, int mouseY, boolean ingame) {
        Color background = new Color(0, 0, 0, 107);

        if(loginMethod == LoginMethod.MICROSOFT) {
            ClientGuiUtils.drawRoundedRect(this.width / 2 - 50, this.height / 2 - 25, 100, 50, 3, background);
            LuxClient.Fonts.text.drawCenteredString("LOGGING IN", this.width / 2, this.height / 2 - 18, -1);

            LuxClient.Fonts.text.drawCenteredString("[loading annimation]", this.width / 2, this.height / 2 - 7, -1);

        } else if(loginMethod == LoginMethod.MOJANG) {
            ClientGuiUtils.drawRoundedRect(this.width / 2 - 80, this.height / 2 - 45, 160, 90, 3, background);
            LuxClient.Fonts.titleThin.drawStringScaled("LOGIN WITH MOJANG", this.width / 2 - 75, this.height / 2 - 40, -1, 0.6);

        } else {
            ClientGuiUtils.drawRoundedRect(this.width / 2 - 75, this.height / 2 - 25, 150, 70, 3, background);
            LuxClient.Fonts.titleThin.drawStringScaled("SELECT NAME", this.width / 2 - 70, this.height / 2 - 20, -1, 0.6);

        }
    }

    @Override
    public void initComponents() {
        if(loginMethod == LoginMethod.MOJANG) {

            this.componentList.add(new UiButton(1, this.width / 2 - 75, this.height / 2 + 26, 72, 12, "LOGIN"));
            this.componentList.add(new UiButton(0, this.width / 2 + 2, this.height / 2 + 26, 72, 12, "BACK"));

        } else if(loginMethod == LoginMethod.MICROSOFT) {

            this.componentList.add(new UiButton(0, this.width / 2 - 25, this.height / 2 + 10, 50, 12, "BACK"));

        } else {

            this.componentList.add(new UiButton(2, this.width / 2 - 70, this.height / 2 + 26, 67, 12, "SELECT"));
            this.componentList.add(new UiButton(0, this.width / 2 + 2, this.height / 2 + 26, 67, 12, "BACK"));
            
        }
    }

    @Override
    public void buttonClicked(UiButton button) {
        if(button.getId() == 0) {
            this.mc.displayGuiScreen(new UiLoginSelection());
        }

        if(button.getId() == 1) {
            //login with mojang
        }

        if(button.getId() == 2) {
            //cracked login
        }
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        if(keyCode == Keyboard.KEY_ESCAPE) {
            mc.displayGuiScreen(new UiLoginSelection());

        }
    }
}
