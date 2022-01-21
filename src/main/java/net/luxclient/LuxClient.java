package net.luxclient;

import hex.event.EventManager;
import hex.event.EventTarget;
import net.luxclient.events.TickEvent;
import net.luxclient.module.ModuleManager;
import net.luxclient.util.font.CustomFontRenderer;
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

    public static class Fonts {

        public static final CustomFontRenderer titleBold = new CustomFontRenderer("title_bold", 30);
        public static final CustomFontRenderer titleThin = new CustomFontRenderer("title_thin", 30);
        public static final CustomFontRenderer text = new CustomFontRenderer("normal", 16);
        public static final CustomFontRenderer textBold = new CustomFontRenderer("normal_bold", 16);

    }

}
