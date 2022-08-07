package net.luxclient.events;

import hex.event.Event;

public class TickEvent extends Event {
    public final State state;

    public TickEvent(State state) {
        this.state = state;
    }
}
