package com.nuclearfarts.beaconconfig;

import java.lang.reflect.Type;

import com.google.gson.InstanceCreator;

import net.minecraft.block.entity.BeaconBlockEntity;
import net.minecraft.entity.effect.StatusEffect;

public class BeaconConfig {
	public static final BeaconConfig INSTANCE = new BeaconConfig();
	
	public StatusEffect[][] effects = BeaconBlockEntity.EFFECTS_BY_LEVEL;
	public int range_level_increase = 10;
	public int range_base = 10;
	
	public static class Creator implements InstanceCreator<BeaconConfig> {
		@Override
		public BeaconConfig createInstance(Type type) {
			return INSTANCE;
		}
	}
}
