package net.luxclient.module.impl;

import com.google.gson.annotations.Expose;
import net.luxclient.module.Category;
import net.luxclient.module.LuxModule;
import net.luxclient.module.LuxModuleData;
import net.minecraft.client.Minecraft;

@LuxModuleData(name = "Fullbright", description = "Fullbright module", category = Category.MISC, aliases = {"brightness", "light"})
public class FullbrightModule extends LuxModule {

    @Expose
    public int gamma = 10;

    @Override
    public void onEnable() {
        Minecraft.getMinecraft().gameSettings.gammaSetting = gamma;
    }

    @Override
    public void onDisable() {
        Minecraft.getMinecraft().gameSettings.gammaSetting = 1;
    }
}
