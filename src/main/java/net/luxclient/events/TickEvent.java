package net.luxclient.events;

import lombok.AllArgsConstructor;
import hex.event.Event;

@AllArgsConstructor
public class TickEvent extends Event {
    public final State state;
}
