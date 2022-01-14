package net.luxclient;

import org.lwjgl.opengl.Display;

public class LuxClient {

    public static final String NAME = "Lux Client",
                                VERSION = "3.0 Beta",
                                NAMEVER = NAME + " v" + VERSION;

    public static void initClient() {
        Display.setTitle(NAMEVER);
    }

    public static void shutdownClient() {

    }

}
