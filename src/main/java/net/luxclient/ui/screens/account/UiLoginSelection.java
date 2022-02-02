package net.luxclient.ui.screens.account;

import net.luxclient.LuxClient;
import net.luxclient.ui.UiScreen;
import net.luxclient.ui.components.buttons.UiButton;
import net.luxclient.ui.components.buttons.UiImageTextButton;
import net.luxclient.util.ClientGuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

import java.awt.*;

public class UiLoginSelection extends UiScreen {

    @Override
    public void renderScreen(int mouseX, int mouseY, boolean ingame) {
        ClientGuiUtils.drawRoundedRect(this.width / 2 - 90, this.height / 2 - 50, 180, 115, 3, new Color(0, 0, 0, 107));
        LuxClient.Fonts.titleThin.drawCenteredTextScaled("LOGIN", this.width / 2, this.height / 2 - 45, -1, 0.6);
    }

    @Override
    public void initComponents() {
        this.componentList.add(new UiImageTextButton(0, this.width / 2 - 80, this.height / 2 - 30, 160, 25, "MOJANG", new ResourceLocation("lux/icons/login/mojang.png")));
        this.componentList.add(new UiImageTextButton(1, this.width / 2 - 80, this.height / 2, 160, 25, "MICROSOFT", new ResourceLocation("lux/icons/login/microsoft.png")));
        this.componentList.add(new UiImageTextButton(2, this.width / 2 - 80, this.height / 2 + 30, 160, 25, "CRACKED", new ResourceLocation("lux/icons/login/cracked.png")));
    }

    @Override
    public void buttonClicked(UiButton button) {
        LoginMethod method = null;

        if(button.getId() == 0)
            method = LoginMethod.MOJANG;

        if (button.getId() == 1)
            method = LoginMethod.MICROSOFT;

        if(button.getId() == 2)
            method = LoginMethod.CRACKED;

        Minecraft.getMinecraft().displayGuiScreen(new UiLogin(method));
    }
}
