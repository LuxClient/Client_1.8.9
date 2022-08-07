package net.luxclient.module;

import com.google.gson.annotations.Expose;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import net.luxclient.LuxClient;
import net.luxclient.hud.HudComponent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public abstract class LuxModule {

    @Setter(value = AccessLevel.NONE) @Expose
    private boolean enabled = false;

    @Setter(value = AccessLevel.NONE)
    private boolean forceDisabled = false;

    protected List<HudComponent> hudComponents;

    public void setEnabled(boolean enabled) {
        if (this.forceDisabled)
            this.enabled = false;
        else
            this.enabled = enabled;
    }

    public void setForceDisabled(boolean forceDisabled) {
        this.forceDisabled = forceDisabled;
        if (forceDisabled)
            setEnabled(false);
    }

    public void toggleEnabled() {
        LuxClient.getModuleManager().getModulesChanged().add(this);
        if (isEnabled()) {
            onDisable();
            setEnabled(false);
        } else {
            onEnable();
            setEnabled(true);
        }
    }

    public String getName() {
        return getClass().getAnnotation(LuxModuleData.class).name();
    }

    public String getDescription() {
        return getClass().getAnnotation(LuxModuleData.class).description();
    }

    public Category getCategory() {
        return getClass().getAnnotation(LuxModuleData.class).category();
    }

    public List<String> getAliases() {
        String[] aliases = this.getClass().getAnnotation(LuxModuleData.class).aliases();
        return aliases == null ? new ArrayList<>() : Arrays.asList(aliases);
    }

    public void setHudComponents(HudComponent... hudComponents) {
        this.hudComponents = Arrays.asList(hudComponents);
    }

    public void addHudComponent(HudComponent hudComponent) {
        if (hudComponents == null)
            hudComponents = new ArrayList<>();
        hudComponents.add(hudComponent);
    }

    public void removeHudComponent(HudComponent hudComponent) {
        if (hudComponents == null)
            return;
        hudComponents.remove(hudComponent);
    }


    /*public boolean hasHud() {
        return hudComponent != null;
    }*/

    public void onEnable(){}
    public void onDisable(){}
}
