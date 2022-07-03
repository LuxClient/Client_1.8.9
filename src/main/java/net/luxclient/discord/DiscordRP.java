package net.luxclient.discord;

import lombok.Getter;
import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;

public class DiscordRP {

    @Getter
    private static final DiscordRP instance = new DiscordRP();

    private long created = 0;

    private boolean running = true;

    public void startDiscordRPC() {
        this.created = System.currentTimeMillis();
        DiscordEventHandlers.Builder builder = new DiscordEventHandlers.Builder();
        builder.setReadyEventHandler(user -> DiscordRP.getInstance().update("Playing Minecraft 1.8.9", user.username));
        DiscordEventHandlers handlers = builder.build();
        DiscordRPC.discordInitialize("906992886074208256", handlers, true);
        new Thread(() -> {
            while(running) DiscordRPC.discordRunCallbacks();
        }).start();
    }

    public void shutdownDiscordRPC() {
        this.running = false;
        DiscordRPC.discordShutdown();
    }

    /**
     * Discord Update method.
     * @param firstLine String first line of the RPC
     * @param secondLine String second line of the RPC
     */
    public void update(String firstLine, String secondLine) {
        DiscordRichPresence.Builder b = new DiscordRichPresence.Builder(secondLine);
        b.setBigImage("logo", "Lux Client - Playing");
        b.setDetails(firstLine);
        b.setStartTimestamps(this.created);
        DiscordRPC.discordUpdatePresence(b.build());
    }
}
