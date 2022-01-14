package net.luxclient.events;

import lombok.AllArgsConstructor;
import hex.event.Event;
import org.lwjgl.input.Keyboard;

@AllArgsConstructor
public class KeyPressEvent extends Event {
    public final int keyCode;

    public boolean isPressed() {
        return Keyboard.isKeyDown(keyCode);
    }
}
