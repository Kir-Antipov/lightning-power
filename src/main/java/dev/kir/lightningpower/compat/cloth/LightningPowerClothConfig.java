package dev.kir.lightningpower.compat.cloth;

import dev.kir.lightningpower.LightningPower;
import dev.kir.lightningpower.config.LightningPowerConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;

@Config(name = LightningPower.MOD_ID)
public class LightningPowerClothConfig implements LightningPowerConfig, ConfigData {
    @ConfigEntry.Gui.Excluded
    private static final LightningPowerClothConfig INSTANCE;

    @ConfigEntry.Gui.RequiresRestart
    @ConfigEntry.Gui.Tooltip
    public boolean accurateRedstonePower = LightningPowerConfig.super.accurateRedstonePower();

    @ConfigEntry.Gui.RequiresRestart
    public long emittedEnergyAmountPerTick = LightningPowerConfig.super.emittedEnergyAmountPerTick();

    public static LightningPowerConfig getInstance() {
        return INSTANCE;
    }

    private LightningPowerClothConfig() { }

    @Override
    public boolean accurateRedstonePower() {
        return this.accurateRedstonePower;
    }

    @Override
    public long emittedEnergyAmountPerTick() {
        return this.emittedEnergyAmountPerTick;
    }

    static {
        INSTANCE = AutoConfig.register(LightningPowerClothConfig.class, GsonConfigSerializer::new).getConfig();
    }
}
