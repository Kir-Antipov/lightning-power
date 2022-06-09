package dev.kir.lightningpower.energy;

import net.minecraft.block.Blocks;
import net.minecraft.block.LightningRodBlock;
import team.reborn.energy.api.EnergyStorage;

public final class LightningPowerEnergy {
    public static void init() { }

    static {
        EnergyStorage.SIDED.registerForBlocks((world, pos, state, blockEntity, direction) -> direction == state.get(LightningRodBlock.FACING).getOpposite() ? new LightningRodBlockEnergyStorage(state) : null, Blocks.LIGHTNING_ROD);
    }
}
