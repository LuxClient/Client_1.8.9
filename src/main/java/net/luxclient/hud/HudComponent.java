package net.luxclient.hud;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.Setter;
import net.luxclient.LuxClient;
import net.luxclient.util.font.CustomFontRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;

import java.awt.Color;

public class HudComponent {

    protected Minecraft mc = Minecraft.getMinecraft();
    protected LuxClient luxClient = LuxClient.getInstance();
    protected CustomFontRenderer font = LuxClient.Fonts.hud;

    private ScaledResolution res = new ScaledResolution(mc);

    @Getter
    @Expose
    protected int x = 3, y = 3;
    @Getter @Setter
    @Expose
    protected int width, height;
    @Getter @Setter
    @Expose
    protected int backgroundColor = new Color(0, 0, 0, 120).getRGB();

    public HudComponent(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void render() {
        System.out.println("Hud Component");
    }
    public void renderDummy() {
        this.render();
    }

    public double getRelativeX() {
        return res.getScaledWidth() / x;
    }

    public double getRelativeY() {
        return res.getScaledHeight() / y;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setPosition(double x, double y) {
        this.x = (int) (x * res.getScaledWidth());
        this.y = (int) (y * res.getScaledHeight());
    }

}
