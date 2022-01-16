package net.luxclient.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import hex.event.EventManager;
import hex.event.EventTarget;
import lombok.SneakyThrows;
import net.luxclient.events.KeyPressEvent;
import net.luxclient.util.gson.ExcludeStrategy;
import org.reflections.Reflections;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Set;

public class ModuleManager {

    private final static String toCheck = "net.luxclient.module.impl";

    private final File dataFolder;
    private final ArrayList<LuxModule> modules = new ArrayList<>();
    private final Set<Class<?>> classes;
    private final Gson gson;

    public ModuleManager() {
        dataFolder = new File("config" + File.separatorChar + "LuxClient");
        gson = (new GsonBuilder()).setPrettyPrinting()
                .addDeserializationExclusionStrategy(new ExcludeStrategy()).addSerializationExclusionStrategy(new ExcludeStrategy()).create();
        Reflections reflection = new Reflections(toCheck);
        classes = reflection.getTypesAnnotatedWith(LuxModuleData.class);
        EventManager.register(this);
    }

    public void loadModules() {
        if (!dataFolder.exists()) {
            loadDefaults();
            return;
        }
        for (Class<?> clazz : classes) {
            LuxModuleData luxAnnotation = clazz.getAnnotation(LuxModuleData.class);
            File config = new File(dataFolder, luxAnnotation.fileName() + ".json");
            if (!config.exists()) {
                try {
                    LuxModule module = (LuxModule) clazz.newInstance();
                    if (module.isEnabled())
                        module.onEnable();
                    saveModule(module);
                    modules.add(module);
                } catch (Exception e) {
                    e.printStackTrace();
                }
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
    public void saveModule(LuxModule module) {
        String fileName = module.getClass().getAnnotation(LuxModuleData.class).fileName();
        File config = new File(dataFolder, fileName + ".json");
        config.createNewFile();

        Writer fileWriter = new FileWriter(config);
        gson.toJson(module, fileWriter);
        fileWriter.close();
    }

    @EventTarget
    public void onKeyEvent(KeyPressEvent e) {
        if (!e.isPressed() || e.getKeyCode() == -1)
            return;
        for (LuxModule module: modules) {
            if (module.getKeyCode() == e.getKeyCode()) {
                module.toggleEnabled();
            }
        }
    }

}
