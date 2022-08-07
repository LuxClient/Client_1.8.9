package net.luxclient.module.impl;

import com.google.gson.annotations.Expose;
import hex.event.EventManager;
import net.luxclient.hud.impl.ExampleHud;
import net.luxclient.module.Category;
import net.luxclient.module.LuxModule;
import net.luxclient.module.LuxModuleData;

@LuxModuleData(name = "Example",
        description = "Just a quick example",
        category = Category.MISC,
        aliases = {"example", "examples"})
public class ExampleModule extends LuxModule {

    //NOTE if u change values or remove/add variables you need to delete its json (or just the whole folder if u don't care about your configs)
    //This is true for all modules not just this one
    @Expose
    public int saveThis = 5; //This value will get saved and loaded from the modules json

    //This value won't get saved nor loaded from the json due to missing the @Expose annotation
    public int dontSaveThis = 21;

    //This value can't get saved nor loaded from the json due to being static
    public static int wontSave = 5;

    public ExampleModule() {
        super.setHudComponents(new ExampleHud(80, 15)/*, new ExampleHud(), ExampleHud()*/); //Able to register multiple hud components
    }

    @Override
    public void onEnable(){
        EventManager.register(this);
        //System.out.println("Enabled");
    }

    @Override
    public void onDisable() {
        EventManager.unregister(this);
        //System.out.println("Disabled");
    }

    /*@EventTarget
    public void onTick(TickEvent e) {
        System.out.println("Ticked it did");
    }*/


}
