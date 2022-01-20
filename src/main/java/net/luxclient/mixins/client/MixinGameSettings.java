package net.luxclient.mixins.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import org.apache.commons.lang3.ArrayUtils;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.File;

@Mixin(GameSettings.class)
public class MixinGameSettings {

    @Shadow public KeyBinding[] keyBindings;
    private static KeyBinding CLIENT_MOD_SCREEN;

    private void initCLientKeys() {
        CLIENT_MOD_SCREEN = new KeyBinding("Client Settings Screen", Keyboard.KEY_RSHIFT, "Lux Client");
        this.keyBindings = ArrayUtils.add(this.keyBindings, CLIENT_MOD_SCREEN);
    }

    @Inject(method = "<init>(Lnet/minecraft/client/Minecraft;Ljava/io/File;)V", at = @At("RETURN"))
    public void constructor(Minecraft mcIn, File optionsFileIn, CallbackInfo ci) {
        this.initCLientKeys();
    }

    @Inject(method = "<init>()V", at = @At("RETURN"))
    public void constructor(CallbackInfo ci) {
        this.initCLientKeys();
    }
}
