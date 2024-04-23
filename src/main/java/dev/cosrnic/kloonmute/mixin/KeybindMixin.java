package dev.cosrnic.kloonmute.mixin;

import net.minecraft.client.Minecraft;
import dev.cosrnic.kloonmute.Mod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class KeybindMixin {
    @Inject(method = "<init>", at = @At("TAIL"))
    private void printHell(CallbackInfo ci) {
        Mod.LOGGER.info("Hello from example mixin");
    }
}
