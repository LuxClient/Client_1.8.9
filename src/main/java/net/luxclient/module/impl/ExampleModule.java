package net.luxclient.module.impl;

import hex.event.EventManager;
import hex.event.EventTarget;
import net.luxclient.events.TickEvent;
import net.luxclient.module.Category;
import net.luxclient.module.LuxModule;
import net.luxclient.module.LuxModuleData;
import net.luxclient.util.gson.Exclude;

@LuxModuleData(fileName = "testModule") //What name the modules json should be stored under
public class ExampleModule extends LuxModule {

    //NOTE if u change values or remove/add variables you need to delete its json (or just the whole folder if u dont care about your configs)
    //This is true for all modules not just this one

    public int saveThis = 5; //This value will get saved and loaded from the modules json

    @Exclude
    public int dontSaveThis = 21; //This value wont get saved nor loaded from the json due to having the @Exclude annotation

    public static int wontSave = 5; //This value wont get saved nor loaded from the json due to being static

    public ExampleModule() {
        setName("Test Module");
        setDescription("Just a simple module to test with");
        setCategory(Category.MISC);
        setKeyCode(21); //Key is Y
    }

    @Override
    public void onEnable(){
        EventManager.register(this);
    }

    @Override
    public void onDisable() {
        EventManager.unregister(this);
    }

    @EventTarget
    public void onTick(TickEvent e) {
        System.out.println("Ticked it did");
    }


}
