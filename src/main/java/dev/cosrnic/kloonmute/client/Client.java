package dev.cosrnic.kloonmute.client;

import felis.kittens.core.client.ClientEntrypoint;
import dev.cosrnic.kloonmute.Mod;

public class Client implements ClientEntrypoint {
    @Override
    public void onClientInit() {
        Mod.LOGGER.info("Client has been initialized");
    }
}
