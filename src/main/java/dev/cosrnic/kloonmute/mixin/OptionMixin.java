package dev.cosrnic.kloonmute.mixin;

import dev.cosrnic.kloonmute.client.Client;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Options;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Mixin(Options.class)
public class OptionMixin {
    @Shadow
    public KeyMapping[] keyMappings;

    @Inject(at = @At("HEAD"), method = "load")
    public void loadKeys(CallbackInfo ci) {
        List<KeyMapping> newKeys = new ArrayList<>(Arrays.stream(keyMappings).toList());

        newKeys.add(Client.key);
        keyMappings = newKeys.toArray(new KeyMapping[0]);
    }

}
