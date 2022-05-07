package net.luxclient.module;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
public abstract class LuxModule {

    private String name;

    private String description;

    private Category category;

    @Setter(value= AccessLevel.NONE)
    private boolean enabled = false;

    @Setter(value=AccessLevel.NONE)
    private boolean forceDisabled = false;

    private int keyCode = -1;

    //private HudComponent hudComponent;

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
        if (isEnabled()) {
            onDisable();
            setEnabled(false);
        } else {
            onEnable();
            setEnabled(true);
        }
    }

    /*public boolean hasHud() {
        return hudComponent != null;
    }*/

    public void onEnable(){System.out.println("Called wrong lol");}
    public void onDisable(){}
}
