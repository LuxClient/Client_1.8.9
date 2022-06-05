package net.luxclient.hud;

import net.luxclient.LuxClient;
import net.minecraft.client.Minecraft;

public abstract class HudComponent {

    Minecraft mc = Minecraft.getMinecraft();
    LuxClient luxClient = LuxClient.getInstance();


    abstract public void render();

    abstract public void renderDummy();

}
