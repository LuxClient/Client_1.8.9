package net.luxclient.hud.impl;

import net.luxclient.hud.HudComponent;

public class ExampleHud extends HudComponent {

    private long timer = 0;

    @Override
    public void render() {
        if (timer <= System.currentTimeMillis()) {
            timer = System.currentTimeMillis() + 5000;
            System.out.println("Rendering");
        }
    }

    @Override
    public void renderDummy() {
        if (timer <= System.currentTimeMillis()) {
            timer = System.currentTimeMillis() + 5000;
            System.out.println("Rendering Dummy");
        }
    }
}
