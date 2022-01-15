package net.luxclient.events;

import hex.event.Event;

public class Render2DEvent extends Event {

    public final float partialTicks;

    public Render2DEvent(float partialTicks) {
        this.partialTicks = partialTicks;
    }
}
