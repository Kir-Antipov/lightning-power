package dev.kir.lightningpower;

import dev.kir.lightningpower.config.LightningPowerConfig;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public class LightningPower implements ModInitializer {
    public static final String MOD_ID = "lightning_power";

    public static Identifier locate(String location) {
        return new Identifier(MOD_ID, location);
    }

    public static LightningPowerConfig getConfig() {
        return LightningPowerConfig.DEFAULT;
    }

    @Override
    public void onInitialize() {
    }
}
