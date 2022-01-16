package net.luxclient.mixins.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityPlayer.class)
public class MixinEntityPlayer {

    @Inject(method = "attackTargetEntityWithCurrentItem", at = @At("RETURN"))
    public void attackTargetEntityWithCurrentItem(Entity targetEntity, CallbackInfo callbackInfo) {
        if (targetEntity != null) {
            System.out.println(Minecraft.class.getName());
            //new AttackEntityEvent(targetEntity).post();
        }
    }

}
