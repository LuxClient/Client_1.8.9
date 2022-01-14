package net.luxclient.events;

import lombok.AllArgsConstructor;
import hex.event.Event;

@AllArgsConstructor
public class Render2DEvent extends Event {

    public final float partialTicks;

}
