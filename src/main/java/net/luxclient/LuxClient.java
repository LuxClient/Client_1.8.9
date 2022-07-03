package net.luxclient;

import hex.event.EventManager;
import hex.event.EventTarget;
import lombok.Getter;
import net.arikia.dev.drpc.DiscordRPC;
import net.luxclient.discord.DiscordRP;
import net.luxclient.events.TickEvent;
import net.luxclient.module.ModuleManager;
import net.luxclient.settings.SettingTabsManager;
import net.luxclient.ui.notification.NotificationHandler;
import net.luxclient.ui.screens.UiSplashScreen;
import net.luxclient.util.KeyBindings;
import net.luxclient.util.font.CustomFontRenderer;
import net.minecraft.client.Minecraft;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.Display;

public class LuxClient {

    @Getter
    private static LuxClient instance;
    public static final String NAME = "Lux Client",
                                VERSION = "3.0 Beta",
                                NAMEVER = NAME + " v" + VERSION;
    @Getter
    private static ModuleManager moduleManager;
    @Getter
    private static NotificationHandler notificationHandler;

    public static DiscordRP discord = new DiscordRP();

    public static final Logger LOGGER = LogManager.getLogger("LuxClient");

    private LuxClient() {
        instance = this;
        moduleManager = new ModuleManager();
        moduleManager.loadModules();
        notificationHandler = new NotificationHandler();
        discord.startDiscordRPC();

        new KeyBindings();

        UiSplashScreen.update("initialize client");
        EventManager.register(instance);

        Display.setTitle(NAMEVER);
    }

    public static void initClient() {
        new LuxClient();
    }

    public static void shutdownClient() {
        EventManager.unregister(instance);
        discord.shutdownDiscordRPC();
    }

    @EventTarget
    public void onTick(TickEvent event) {
        if(Minecraft.getMinecraft().gameSettings.keyBindings[33].isKeyDown()) {
            SettingTabsManager.openCurrentScreen();
        }
    }

    public static class Fonts {

        public static final CustomFontRenderer titleBold = new CustomFontRenderer("title_bold", 30);
        public static final CustomFontRenderer titleThin = new CustomFontRenderer("title_thin", 30);
        public static final CustomFontRenderer text = new CustomFontRenderer("normal", 12);
        public static final CustomFontRenderer textBold = new CustomFontRenderer("normal_bold", 12);
        public static final CustomFontRenderer hud = new CustomFontRenderer("mods", 15);

    }

}
