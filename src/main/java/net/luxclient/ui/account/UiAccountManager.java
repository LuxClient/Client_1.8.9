package net.luxclient.ui.account;

import fr.litarvan.openauth.microsoft.MicrosoftAuthResult;
import fr.litarvan.openauth.microsoft.MicrosoftAuthenticationException;
import fr.litarvan.openauth.microsoft.MicrosoftAuthenticator;
import net.luxclient.LuxClient;
import net.luxclient.mixins.client.AccesorMinecraft;
import net.luxclient.ui.UiScreen;
import net.luxclient.ui.components.buttons.UiButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.util.Session;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Objects;

public class UiAccountManager extends UiScreen {

    private GuiScreen parent;

    public UiAccountManager(GuiScreen parent) {
        this.parent = parent;
    }

    private GuiTextField emailFiled;
    private GuiTextField passwordField;

    @Override
    public void renderScreen(int mouseX, int mouseY, boolean ingame) {
        LuxClient.Fonts.text.drawString("Email", this.width / 2 - 60, this.height / 2 - 60, -1);
        LuxClient.Fonts.text.drawString("Password", this.width / 2 - 60, this.height / 2 - 20, -1);

        String password = passwordField.getText();
        passwordField.setText(password.replaceAll(".", "*"));
        emailFiled.drawTextBox();
        passwordField.drawTextBox();

        passwordField.setText(password);
    }

    @Override
    public void initComponents() {
        emailFiled = new GuiTextField(0, mc.fontRendererObj, this.width / 2 - 60, this.height / 2 - 50, 120, 20);
        passwordField = new GuiTextField(1, mc.fontRendererObj, this.width / 2 - 60, this.height / 2 - 10, 120, 20);
        emailFiled.setFocused(true);

        this.componentList.add(new UiButton(0, this.width / 2 + 2, this.height / 2 + 30, 58, 18, "Login"));
        this.componentList.add(new UiButton(1, this.width / 2 - 60, this.height / 2 + 30, 58, 18, "Cancel"));
    }

    @Override
    public void buttonClicked(UiButton button) {
        if(button.getId() == 0) {
            if (emailFiled.getText().equals("") && passwordField.getText().equals("")) return;
            this.login(emailFiled.getText(), passwordField.getText());
            mc.displayGuiScreen(parent);
        }
        if(button.getId() == 1) {
            mc.displayGuiScreen(parent);
        }
    }

    private void login(String email, String password) {
        MicrosoftAuthenticator auth = new MicrosoftAuthenticator();
        MicrosoftAuthResult result = null;
        try {
            result = auth.loginWithCredentials(email, password);
        } catch (MicrosoftAuthenticationException e) {
            LuxClient.LOGGER.error("Unable to log in!");
        }
        if(result == null) return;
        Session s = new Session(result.getProfile().getName(), result.getProfile().getId(), result.getAccessToken(), Session.Type.LEGACY.name());

        ((AccesorMinecraft) Minecraft.getMinecraft()).setSession(s);

        /*try {
            Field field = Minecraft.class.getDeclaredField("session");
            field.setAccessible(true);
            field.set(Minecraft.getMinecraft(), s);

        } catch (NoSuchFieldException | IllegalAccessException e) {
            LuxClient.LOGGER.error("Unable to update session!");
        }*/
    }

    @Override
    public void updateScreen() {
        emailFiled.updateCursorCounter();
        passwordField.updateCursorCounter();
        super.updateScreen();
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        emailFiled.mouseClicked(mouseX, mouseY, mouseButton);
        passwordField.mouseClicked(mouseX, mouseY, mouseButton);
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);
        emailFiled.textboxKeyTyped(typedChar, keyCode);
        passwordField.textboxKeyTyped(typedChar, keyCode);
    }
}
