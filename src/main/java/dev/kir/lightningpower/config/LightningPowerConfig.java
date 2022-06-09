package dev.kir.lightningpower.config;

public interface LightningPowerConfig {
    LightningPowerConfig DEFAULT = new LightningPowerConfig() { };

    default boolean accurateRedstonePower() {
        return false;
    }

    default long emittedEnergyAmountPerTick() {
        return 4096;
    }
}
