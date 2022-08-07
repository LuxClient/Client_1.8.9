package net.luxclient.module.impl;

import net.luxclient.hud.impl.FpsDisplayHud;
import net.luxclient.module.Category;
import net.luxclient.module.LuxModule;
import net.luxclient.module.LuxModuleData;

@LuxModuleData(name = "FPS Display", description = "Displays the user's frames per second!", category = Category.HUD, aliases = {"frames", "fps"})
public class FpsModule extends LuxModule {

    public FpsModule() {
        setHudComponents(new FpsDisplayHud(70, 15));
    }

}
