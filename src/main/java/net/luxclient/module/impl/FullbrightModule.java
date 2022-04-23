package net.luxclient.module.impl;

import net.luxclient.module.Category;
import net.luxclient.module.LuxModule;
import net.luxclient.module.LuxModuleData;
import net.minecraft.client.Minecraft;

@LuxModuleData(fileName = "Fullbright")
public class FullbrightModule extends LuxModule {

    public int gamma = 10;

    public FullbrightModule() {
        this.setName("Fullbright");
        this.setDescription("Increases the players gamma value.");
        this.setCategory(Category.MISC);
    }

    @Override
    public void onEnable() {
        Minecraft.getMinecraft().gameSettings.gammaSetting = gamma;
    }

    @Override
    public void onDisable() {
        Minecraft.getMinecraft().gameSettings.gammaSetting = 1;
    }
}
