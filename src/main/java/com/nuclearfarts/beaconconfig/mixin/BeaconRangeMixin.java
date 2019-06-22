package com.nuclearfarts.beaconconfig.mixin;

import net.minecraft.block.entity.BeaconBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import com.nuclearfarts.beaconconfig.BeaconConfig;

@Mixin(BeaconBlockEntity.class)
public abstract class BeaconRangeMixin {
	
	@ModifyConstant(method = "applyPlayerEffects()V", constant = @Constant(intValue = 10, ordinal = 0))
	private int getBeaconRangeGrowth(int i) {
		return BeaconConfig.INSTANCE.range_level_increase;
	}
	
	@ModifyConstant(method = "applyPlayerEffects()V", constant = @Constant(intValue = 10, ordinal = 1))
	private int getBeaconBaseRange(int i) {
		return BeaconConfig.INSTANCE.range_base;
	}
}
