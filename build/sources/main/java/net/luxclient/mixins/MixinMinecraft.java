package net.luxclient.mixins;

import net.luxclient.LuxClient;
import net.luxclient.ui.screens.UiMainMenu;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.Display;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MixinMinecraft {

    @Inject(method = "startGame", at = @At("RETURN"))
    public void startGame(CallbackInfo callbackInfo) {
        Minecraft.getMinecraft().displayGuiScreen(new UiMainMenu());
        LuxClient.initClient();
    }

    @Inject(method = "shutdown", at = @At("HEAD"))
    public void shutdown(CallbackInfo callbackInfo) {
        LuxClient.shutdownClient();
    }

    @Inject(method = "createDisplay", at = @At("RETURN"))
    public void createDisplay(CallbackInfo callbackInfo) {
        Display.setTitle(LuxClient.NAMEVER + " starting...");
    }

}
