package net.luxclient.mixins.client;

import net.luxclient.LuxClient;
import hex.event.Event;
import net.luxclient.events.KeyPressEvent;
import net.luxclient.events.TickEvent;
import net.luxclient.ui.screens.UiMainMenu;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;
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

    @Inject(method = "runTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/profiler/Profiler;startSection(Ljava/lang/String;)V", shift = At.Shift.BEFORE))
    private void preTick(CallbackInfo ci) {
        new TickEvent(Event.State.PRE).call();
    }

    @Inject(method = "runTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/profiler/Profiler;endSection()V", shift = At.Shift.BEFORE))
    private void postTick(CallbackInfo ci) {
        new TickEvent(Event.State.POST).call();
    }

    @Inject(method = "runTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;dispatchKeypresses()V", shift = At.Shift.AFTER))
    private void keyPress(CallbackInfo ci) {
        new KeyPressEvent(Keyboard.getEventKey()).call();
    }

}
