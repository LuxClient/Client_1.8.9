package net.luxclient.events;

import hex.event.Event;
import lombok.Getter;
import org.lwjgl.input.Keyboard;

public class KeyPressEvent extends Event {

    @Getter
    public final int keyCode;

    public KeyPressEvent(int keyCode) {
        this.keyCode = keyCode;
    }

    public boolean isPressed() {
        return Keyboard.isKeyDown(keyCode);
    }
}
