package net.luxclient.ui.screens.settings.tabs.mods;

import net.luxclient.LuxClient;
import net.luxclient.module.impl.ExampleModule;
import net.luxclient.module.impl.FullbrightModule;
import net.luxclient.ui.screens.settings.UiSettingsTab;
import org.lwjgl.opengl.GL11;

public class UiTabMods extends UiSettingsTab {

    private UiModButton button1;
    private UiModButton button2;

    @Override
    public void initComponents() {
        button1 = new UiModButton(LuxClient.getModuleManager().getModule(FullbrightModule.class), 115, 36) {
            @Override
            public boolean isHovered(int mouseX, int mouseY) {
                return mouseX >= x + dragX &&
                        mouseY >= y + dragY &&
                        mouseX < x + dragX + this.width &&
                        mouseY < y + dragY + this.height;
            }
        };
        button2 = new UiModButton(LuxClient.getModuleManager().getModule(ExampleModule.class), 190, 36) {
            @Override
            public boolean isHovered(int mouseX, int mouseY) {
                return mouseX >= x + dragX &&
                        mouseY >= y + dragY &&
                        mouseX < x + dragX + this.width &&
                        mouseY < y + dragY + this.height;
            }
        };
        super.initComponents();
    }

    @Override
    public void renderScreen(int mouseX, int mouseY, boolean ingame) {
        super.renderScreen(mouseX, mouseY, ingame);
        button1.render(mouseX, mouseY);
        button2.render(mouseX, mouseY);

        GL11.glPopMatrix();
    }

    @Override
    protected String getWindowName() {
        return "MODS";
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if(button1.isHovered(mouseX, mouseY)) button1.onClick();
        if(button2.isHovered(mouseX, mouseY)) button2.onClick();
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }
}
