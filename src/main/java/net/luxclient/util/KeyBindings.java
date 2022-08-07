package net.luxclient.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import org.apache.commons.lang3.ArrayUtils;
import org.lwjgl.input.Keyboard;

public class  KeyBindings {
    public static KeyBinding CLIENT_MOD_SCREEN = new KeyBinding("Client Settings Screen", Keyboard.KEY_RSHIFT, "Lux Client");

    public KeyBindings() {
        registerKeyBindings(CLIENT_MOD_SCREEN);
    }

    public void registerKeyBindings(KeyBinding key) {
        Minecraft.getMinecraft().gameSettings.keyBindings = ArrayUtils.add(Minecraft.getMinecraft().gameSettings.keyBindings, key);
    }
}
