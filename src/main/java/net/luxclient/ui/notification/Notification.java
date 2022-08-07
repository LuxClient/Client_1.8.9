package net.luxclient.ui.notification;

import net.luxclient.LuxClient;
import net.luxclient.util.ClientGuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;

import java.awt.*;

public class Notification {

    protected String name;
    protected NotificationType type;

    private int livingTicks = 150;

    public Notification(String name, NotificationType type) {
        this.name = name;
        this.type = type;
    }

    public void renderNotification() {
        Color bottomC;
        String prefix;
        if(type == NotificationType.INFO) {
            bottomC = new Color(62, 222, 179, 150);
            prefix = "Info: ";

        } else if (type == NotificationType.WARNING) {
            bottomC = new Color(255, 223, 126, 150);
            prefix = "Warning: ";

        } else {
            bottomC = new Color(245, 86, 86, 150);
            prefix = "Error: ";
        }

        ScaledResolution res = new ScaledResolution(Minecraft.getMinecraft());
        int width = (int) (LuxClient.Fonts.text.getWidth(prefix + this.name) * 1.1);

        ClientGuiUtils.drawRoundedRect((res.getScaledWidth() - width - 2) / 2, 20, width + 4, LuxClient.Fonts.text.FONT_HEIGHT + 6, 2, ClientGuiUtils.brandingSecondBackgroundColor);
        ClientGuiUtils.drawLine((res.getScaledWidth() - width - 2) / 2 + 1, (res.getScaledWidth() - width - 2) / 2 + width + 3, LuxClient.Fonts.text.FONT_HEIGHT + 25, 3.0F, bottomC.getRGB(), true);
        LuxClient.Fonts.text.drawCenteredString(prefix + this.name, res.getScaledWidth() / 2, 23, -1);

        --livingTicks;
    }

    public boolean isLiving() {
        return this.livingTicks > 0;
    }

}
