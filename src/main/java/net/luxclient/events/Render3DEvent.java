package net.luxclient.events;

import lombok.AllArgsConstructor;
import hex.event.Event;

@AllArgsConstructor
public class Render3DEvent extends Event {

    public final float partialTicks;

}
