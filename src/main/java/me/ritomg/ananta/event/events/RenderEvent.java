package me.ritomg.ananta.event.events;

import me.ritomg.ananta.event.AnantaMainEvent;

public class RenderEvent extends AnantaMainEvent {

    float partialTicks;

    public RenderEvent(float partialTicks) {
        super();
        this.partialTicks = partialTicks;
    }

    public float getPartialTicks() {
        return partialTicks;
    }
}
