package dev.cosrnic.kloonmute.client;

import com.mojang.blaze3d.platform.InputConstants;
import felis.kittens.core.client.ClientEntrypoint;
import felis.kittens.core.event.GameEvents;
import net.minecraft.ChatFormatting;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.Options;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import org.lwjgl.glfw.GLFW;

public class Client implements ClientEntrypoint {

    public static KeyMapping key = new KeyMapping("key.kloonmute.mute_key", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_M, "key.categories.misc");

    private double recordedSoundLevel;

    @Override
    public void onClientInit() {

        GameEvents.Player.Tick.end.register(player -> {
            if (key.consumeClick()) {
                toggleMute();
            }
        });

    }

    private void toggleMute() {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;

        Options options = mc.options;
        OptionInstance<Double> masterVolume = options.getSoundSourceOptionInstance(SoundSource.MASTER);
        double soundLevel = masterVolume.get();

        if (soundLevel == 0) {
            if (this.recordedSoundLevel == 0) {
                mc.player.sendSystemMessage(Component.translatable("kloonmute.error").withStyle(ChatFormatting.RED));
            } else {
                masterVolume.set(recordedSoundLevel);
                mc.player.sendSystemMessage(Component.translatable("kloonmute.unmuted").withStyle(ChatFormatting.DARK_GRAY));
            }
        } else {
            mc.player.sendSystemMessage(Component.translatable("kloonmute.muted").withStyle(ChatFormatting.LIGHT_PURPLE));

            recordedSoundLevel = soundLevel;
            masterVolume.set(0.0);
        }


    }

}
