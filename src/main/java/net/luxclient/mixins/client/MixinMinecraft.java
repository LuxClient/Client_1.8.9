package net.luxclient.mixins.client;

import hex.event.Event;
import net.luxclient.LuxClient;
import net.luxclient.events.KeyPressEvent;
import net.luxclient.events.TickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Session;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MixinMinecraft {

    @Shadow public Session session;

    @Inject(method = "startGame", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiIngame;<init>(Lnet/minecraft/client/Minecraft;)V", shift = At.Shift.AFTER))
    public void startGame(CallbackInfo callbackInfo) {
        LuxClient.initClient();
    }

    @Inject(method = "shutdown", at = @At("HEAD"))
    public void shutdown(CallbackInfo callbackInfo) {
        LuxClient.shutdownClient();
    }

    @Inject(method = "createDisplay", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/Display;setTitle(Ljava/lang/String;)V", shift = At.Shift.AFTER))
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
