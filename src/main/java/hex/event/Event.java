package hex.event;

import net.luxclient.util.ArrayHelper;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by Hexeption on 18/12/2016.
 */
public abstract class Event {

    /**
     *
     * Main events you may need:
     *
     * Minecraft:
     * - EventKeyboard
     * - EventMiddleClick
     * - EventTick
     *
     * EntityPlayerSP:
     * - EventUpdate
     * - EventPreMotionUpdates
     * - EventPostMotionUpdates
     *
     * GuiIngame:
     * - EventRender2D
     *
     * EntityRenderer:
     * - EventRender3D
     *
     */

    public enum State {
        PRE("PRE", 0),

        POST("POST", 1);

        private State(final String string, final int number) {

        }
    }

    public Event call() {
        call(this);
        return this;
    }

    private static final void call(final Event event) {

        final ArrayHelper<Data> dataList = EventManager.get(event.getClass());

        if (dataList != null) {
            for (final Data data : dataList) {

                try {
                    data.target.invoke(data.source, event);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}