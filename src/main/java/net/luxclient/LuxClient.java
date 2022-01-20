package net.luxclient;

import hex.event.EventManager;
import hex.event.EventTarget;
import net.luxclient.events.TickEvent;
import net.luxclient.module.ModuleManager;
import net.luxclient.ui.components.buttons.UiButton;
import net.luxclient.ui.screens.settings.UiSettingsTab;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.Display;

public class LuxClient {

    private static LuxClient instance;
    public static final String NAME = "Lux Client",
                                VERSION = "3.0 Beta",
                                NAMEVER = NAME + " v" + VERSION;

    private static ModuleManager moduleManager;

    public static void initClient() {
        instance = new LuxClient();
        EventManager.register(instance);

        moduleManager = new ModuleManager();
        moduleManager.loadModules();

        Display.setTitle(NAMEVER);
    }

    public static void shutdownClient() {
        EventManager.unregister(instance);
    }

    @EventTarget
    public void onTick(TickEvent event) {
        if(Minecraft.getMinecraft().gameSettings.keyBindings[33].isKeyDown()) {
            //Minecraft.getMinecraft().displayGuiScreen();
        }
    }

    public static ModuleManager getModuleManager() {
        return moduleManager;
    }

    public static LuxClient getInstance() {
        return instance;
    }
}
