package dev.kir.lightningpower.energy;

import dev.kir.lightningpower.LightningPower;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LightningRodBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import team.reborn.energy.api.EnergyStorage;
import team.reborn.energy.api.EnergyStorageUtil;

@SuppressWarnings("UnstableApiUsage")
public class LightningRodBlockEnergyStorage implements EnergyStorage {
    public static final int MAX_POWER_LEVEL = 8;
    public static final long ENERGY_PER_TICK = LightningPower.getConfig().emittedEnergyAmountPerTick();
    public static final IntProperty POWER_LEVEL = IntProperty.of("power_level", 0, MAX_POWER_LEVEL);

    private BlockState state;
    private int powerLevel = -1;
    private long tickEnergyAmount = -1;

    public LightningRodBlockEnergyStorage(BlockState state) {
        this.updateState(state);
    }

    private void updateState(BlockState state) {
        this.state = state;
        this.updatePowerLevel(state.get(POWER_LEVEL));
    }

    private void updatePowerLevel(int powerLevel) {
        if (this.powerLevel == powerLevel) {
            return;
        }

        this.tickEnergyAmount = powerLevel == 0 ? 0 : ENERGY_PER_TICK;
        this.powerLevel = powerLevel;
    }

    @Override
    public boolean supportsInsertion() {
        return false;
    }

    @Override
    public boolean supportsExtraction() {
        return true;
    }

    @Override
    public long insert(long maxAmount, TransactionContext context) {
        return 0;
    }

    @Override
    public long extract(long maxAmount, TransactionContext context) {
        long extracted = Math.max(0, Math.min(this.tickEnergyAmount, maxAmount));
        context.addCloseCallback((ctx, result) -> {
            if (result.wasCommitted()) {
                this.tickEnergyAmount -= extracted;
            }
        });
        return extracted;
    }

    @Override
    public long getAmount() {
        return Math.max(this.state.get(POWER_LEVEL) - 1, 0) * ENERGY_PER_TICK + this.tickEnergyAmount;
    }

    @Override
    public long getCapacity() {
        return ENERGY_PER_TICK * MAX_POWER_LEVEL;
    }

    public static void tickEnergyTransfer(ServerWorld world, BlockPos pos, BlockState state) {
        if (!state.isOf(Blocks.LIGHTNING_ROD)) {
            return;
        }

        EnergyStorage energyStorage = EnergyStorage.SIDED.find(world, pos, state, null, state.get(LightningRodBlock.FACING).getOpposite());
        if (!(energyStorage instanceof LightningRodBlockEnergyStorage)) {
            return;
        }
        LightningRodBlockEnergyStorage rod = (LightningRodBlockEnergyStorage)energyStorage;
        rod.updateState(state);

        for (Direction direction : Direction.values()) {
            EnergyStorage target = EnergyStorage.SIDED.find(world, pos.offset(direction), direction.getOpposite());
            if (target != null && target.supportsInsertion()) {
                EnergyStorageUtil.move(rod, target, Long.MAX_VALUE, null);
                if (rod.tickEnergyAmount <= 0) {
                    break;
                }
            }
        }
        rod.tickEnergyAmount = 0;
    }
}
