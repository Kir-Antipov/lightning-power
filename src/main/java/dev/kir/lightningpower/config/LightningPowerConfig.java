package dev.kir.lightningpower.config;

import dev.kir.lightningpower.compat.cloth.LightningPowerClothConfig;
import net.fabricmc.loader.api.FabricLoader;

public interface LightningPowerConfig {
    LightningPowerConfig DEFAULT = FabricLoader.getInstance().isModLoaded("cloth-config") ? LightningPowerClothConfig.getInstance() : new LightningPowerConfig() { };

    default boolean accurateRedstonePower() {
        return false;
    }

    default long emittedEnergyAmountPerTick() {
        return 4096;
    }
}
