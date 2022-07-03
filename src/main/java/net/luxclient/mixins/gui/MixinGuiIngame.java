package net.luxclient.mixins.gui;

import net.luxclient.LuxClient;
import net.luxclient.events.Render2DEvent;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiIngame.class)
public class MixinGuiIngame {

    @Inject(method = "renderTooltip", at = @At(value = "RETURN"))
    public void renderOverlay(ScaledResolution sr, float partialTicks, CallbackInfo ci) {
        new Render2DEvent(partialTicks).call();
    }

}
