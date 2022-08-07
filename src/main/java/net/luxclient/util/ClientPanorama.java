package net.luxclient.util;

import net.minecraft.util.ResourceLocation;

import java.util.Calendar;
import java.util.Date;

public class ClientPanorama {

    private static final ResourceLocation BACKGROUND_NORMAL = new ResourceLocation("lux/panorama/normal/background.png");
    private static final ResourceLocation BACKGROUND_CHRISTMAS = new ResourceLocation("lux/panorama/christmas/background.png");

    private static final ResourceLocation[] TILES_NORMAL = new ResourceLocation[] {
            new ResourceLocation("lux/panorama/normal/panorama_0.png"),
            new ResourceLocation("lux/panorama/normal/panorama_1.png"),
            new ResourceLocation("lux/panorama/normal/panorama_2.png"),
            new ResourceLocation("lux/panorama/normal/panorama_3.png"),
            new ResourceLocation("lux/panorama/normal/panorama_4.png"),
            new ResourceLocation("lux/panorama/normal/panorama_5.png")
    };
    private static final ResourceLocation[] TILES_CHRISTMAS = new ResourceLocation[] {
            new ResourceLocation("lux/panorama/christmas/panorama_0.png"),
            new ResourceLocation("lux/panorama/christmas/panorama_1.png"),
            new ResourceLocation("lux/panorama/christmas/panorama_2.png"),
            new ResourceLocation("lux/panorama/christmas/panorama_3.png"),
            new ResourceLocation("lux/panorama/christmas/panorama_4.png"),
            new ResourceLocation("lux/panorama/christmas/panorama_5.png")
    };

    public static ResourceLocation[] getTiles() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        if(isChristmas(calendar))
            return TILES_CHRISTMAS;

        return TILES_NORMAL;
    }

    public static ResourceLocation getBackground() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        if(isChristmas(calendar))
            return BACKGROUND_CHRISTMAS;

        return BACKGROUND_NORMAL;
    }

    private static boolean isChristmas(Calendar calendar) {
        return calendar.get(2) + 1 == 12;
    }

}
