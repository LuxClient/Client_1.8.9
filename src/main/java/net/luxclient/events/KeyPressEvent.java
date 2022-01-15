package net.luxclient.events;

import hex.event.Event;
import org.lwjgl.input.Keyboard;

public class KeyPressEvent extends Event {

    public final int keyCode;

    public KeyPressEvent(int keyCode) {
        this.keyCode = keyCode;
    }

    public boolean isPressed() {
        return Keyboard.isKeyDown(keyCode);
    }
}
