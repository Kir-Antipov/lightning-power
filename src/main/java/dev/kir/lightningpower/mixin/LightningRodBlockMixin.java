package dev.kir.lightningpower.mixin;

import dev.kir.lightningpower.LightningPower;
import dev.kir.lightningpower.energy.LightningRodBlockEnergyStorage;
import net.minecraft.block.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LightningRodBlock.class)
public abstract class LightningRodBlockMixin extends RodBlock {
    @Shadow
    public static @Final BooleanProperty POWERED;

    private static final int MAX_POWER_LEVEL = LightningRodBlockEnergyStorage.MAX_POWER_LEVEL;
    private static final IntProperty POWER_LEVEL = LightningRodBlockEnergyStorage.POWER_LEVEL;
    private static final boolean ACCURATE_REDSTONE_POWER = LightningPower.getConfig().accurateRedstonePower();

    private LightningRodBlockMixin(Settings settings) {
        super(settings);
    }

    @Shadow
    protected abstract void updateNeighbors(BlockState state, World world, BlockPos pos);

    @Inject(method = "<init>", at = @At("RETURN"))
    private void init(Settings settings, CallbackInfo ci) {
        this.setDefaultState(this.getDefaultState().with(POWER_LEVEL, 0));
    }

    @Inject(method = "appendProperties", at = @At("RETURN"))
    private void appendProperties(StateManager.Builder<Block, BlockState> builder, CallbackInfo ci) {
        builder.add(POWER_LEVEL);
    }

    @ModifyArg(method = "setPowered", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;setBlockState(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;I)Z", ordinal = 0), index = 1)
    private BlockState setPowered(BlockState state) {
        return state.with(POWER_LEVEL, MAX_POWER_LEVEL);
    }

    @ModifyArg(method = "setPowered", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;createAndScheduleBlockTick(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/Block;I)V", ordinal = 0), index = 2)
    private int createAndScheduleBlockTick(int ticks) {
        return 1;
    }

    @Inject(method = "scheduledTick", at = @At("HEAD"), cancellable = true)
    private void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {
        if (state.get(POWERED)) {
            LightningRodBlockEnergyStorage.tickEnergyTransfer(world, pos, state);
            int newPowerLevel = state.get(POWER_LEVEL) - 1;
            BlockState newState = state.with(POWER_LEVEL, newPowerLevel);
            if (newPowerLevel == 0) {
                newState = newState.with(POWERED, false);
            } else {
                world.createAndScheduleBlockTick(pos, this, 1);
            }

            world.setBlockState(pos, newState, 3);
            if (ACCURATE_REDSTONE_POWER || newPowerLevel == 0) {
                this.updateNeighbors(state, world, pos);
            }
        }
        ci.cancel();
    }

    @Inject(method = "onBlockAdded", at = @At("HEAD"), cancellable = true)
    private void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify, CallbackInfo ci) {
        if (!state.isOf(oldState.getBlock())) {
            if (state.get(POWERED) && !world.getBlockTickScheduler().isQueued(pos, this)) {
                world.createAndScheduleBlockTick(pos, this, 1);
            }
            ci.cancel();
        }
    }

    @Inject(method = "getWeakRedstonePower", at = @At("HEAD"), cancellable = true)
    private void getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction, CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(getRedstonePower(state));
    }

    @Inject(method = "getStrongRedstonePower", at = @At("HEAD"), cancellable = true)
    private void getStrongRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction, CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(state.get(FACING) == direction ? getRedstonePower(state) : 0);
    }

    private static int getRedstonePower(BlockState state) {
        if (ACCURATE_REDSTONE_POWER) {
            return MathHelper.clamp((int)(15F * state.get(POWER_LEVEL) / MAX_POWER_LEVEL), 0, 15);
        }

        return state.get(POWERED) ? 15 : 0;
    }
}
