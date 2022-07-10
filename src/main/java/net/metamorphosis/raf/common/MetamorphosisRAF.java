package net.metamorphosis.raf.common;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.metamorphosis.raf.command.RerollCommand;
import net.metamorphosis.raf.common.registry.*;

public class MetamorphosisRAF implements ModInitializer{
    public static final String MOD_ID = "metamorphosis-raf";

    @Override
    public void onInitialize() {
        ModScaleTypes.init();
        ModPowers.init();
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated, registryAccess) -> {
            RerollCommand.register(dispatcher);
        });
    }

}
