package dev.cosrnic.kloonmute.client;

import com.mojang.blaze3d.platform.InputConstants;
import felis.kittens.core.client.ClientEntrypoint;
import dev.cosrnic.kloonmute.Mod;
import felis.kittens.core.event.GameEvents;
import net.minecraft.client.KeyMapping;
import net.minecraft.network.chat.Component;
import org.lwjgl.glfw.GLFW;

public class Client implements ClientEntrypoint {

    public static KeyMapping key = new KeyMapping("key.kloonmute.mute_key", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_M, "key.categories.misc");

    @Override
    public void onClientInit() {
        Mod.LOGGER.info("Client has been initialized");

        GameEvents.Player.Tick.end.register(player -> {
            if (key.consumeClick()) {
                player.sendSystemMessage(Component.translatable("key.kloonmute.mute_key"));
            }
        });

    }
}
