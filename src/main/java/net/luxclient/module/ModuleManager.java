package net.luxclient.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import hex.event.EventManager;
import hex.event.EventTarget;
import lombok.Getter;
import lombok.SneakyThrows;
import net.luxclient.LuxClient;
import net.luxclient.events.Render2DEvent;
import net.luxclient.hud.HudComponent;
import net.luxclient.ui.screens.settings.UiHudEditor;
import net.luxclient.ui.screens.settings.UiSettingsTab;
import net.minecraft.client.Minecraft;
import org.reflections.Reflections;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ModuleManager {

    private final static String toCheck = "net.luxclient.module.impl";

    private final File dataFolder;
    @Getter
    private final ArrayList<LuxModule> modules = new ArrayList<>();
    private final Set<Class<?>> classes;
    private final Gson gson;

    // This list contains all modules whose values changed while the settings-gui was open. After the gui closed it is emptied
    @Getter
    private final List<LuxModule> modulesChanged = new ArrayList<>();

    public ModuleManager() {
        dataFolder = new File("LuxClient");
        gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        Reflections reflection = new Reflections(toCheck);
        classes = reflection.getTypesAnnotatedWith(LuxModuleData.class);

        EventManager.register(this);
    }

    public void unregisterEvents() {
        EventManager.unregister(this);
    }

    public void loadModules() {
        if (!dataFolder.exists()) {
            loadDefaults();
            return;
        }
        for (Class<?> clazz : classes) {
            File config = new File(dataFolder, clazz.getAnnotation(LuxModuleData.class).name() + ".json");
            if (!config.exists()) {
                modules.add(makeDefault(clazz));
                return;
            }
            try {
                FileReader reader = new FileReader(config);

                LuxModule module = (LuxModule) gson.fromJson(reader, clazz);
                if (module.isEnabled())
                    module.onEnable();
                modules.add(module);
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
                if (e instanceof IOException) return;
                if (e instanceof JsonSyntaxException) {
                    LuxClient.LOGGER.error("Module " + clazz.getAnnotation(LuxModuleData.class).name() + " has an invalid config file. Returning to default configuration");
                    modules.add(makeDefault(clazz));
                    return;
                }
                LuxClient.LOGGER.error("Failed to load module " + clazz.getAnnotation(LuxModuleData.class).name());
            }
        }
    }

    @SneakyThrows
    private void loadDefaults() {
        dataFolder.mkdirs();
        for (Class<?> clazz : classes) {
            LuxModule module = (LuxModule) clazz.newInstance();
            saveModule(module);
        }
    }

    @SneakyThrows
    private LuxModule makeDefault(Class<?> clazz) {
        LuxModule module = (LuxModule) clazz.newInstance();
        if (module.isEnabled())
            module.onEnable();
        saveModule(module);
        return module;
    }

    @SneakyThrows
    public void saveModule(LuxModule module) {
        File config = new File(dataFolder, module.getName() + ".json");
        config.createNewFile();

        Writer fileWriter = new FileWriter(config);
        gson.toJson(module, fileWriter);
        fileWriter.close();
    }

    public void saveModules() {
        modulesChanged.forEach(this::saveModule);
    }

    public LuxModule getModule(String name) {
        for (LuxModule module : modules) {
            if (module.getAliases().contains(name.toLowerCase()) || module.getName().equalsIgnoreCase(name))
                return module;
            }
        return null;
    }

    public LuxModule getModule(Class<? extends LuxModule> clazz) {
        for (LuxModule module : modules) {
            if (module.getClass().equals(clazz)) {
                return module;
            }
        }
        return null;
    }

    @EventTarget
    public void onRender2D(Render2DEvent e) {
        if (!(Minecraft.getMinecraft().currentScreen instanceof UiHudEditor ||
                Minecraft.getMinecraft().currentScreen instanceof UiSettingsTab)) {
            renderHud();
        }
    }

    public void renderHud() {
        for (LuxModule module : modules) {
            if (!module.isEnabled() || module.getHudComponents() == null)
                continue;

            for (HudComponent component : module.getHudComponents()) {
                component.render();
            }
        }
    }

}
