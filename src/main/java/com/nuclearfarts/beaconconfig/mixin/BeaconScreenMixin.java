package com.nuclearfarts.beaconconfig.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import com.nuclearfarts.beaconconfig.PrimaryEffectProvider;

import net.minecraft.client.gui.screen.ingame.AbstractContainerScreen;
import net.minecraft.client.gui.screen.ingame.BeaconScreen;
import net.minecraft.container.BeaconContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.chat.Component;

//exposes primaryEffect
@Mixin(BeaconScreen.class)
public abstract class BeaconScreenMixin extends AbstractContainerScreen<BeaconContainer> implements PrimaryEffectProvider {

	@Shadow
	private StatusEffect primaryEffect;
	
	public BeaconScreenMixin(BeaconContainer container_1, PlayerInventory playerInventory_1, Component component_1) {
		super(container_1, playerInventory_1, component_1);
		throw new UnsupportedOperationException("don't extend a mixin class.");
	}
	
	@Override
	public StatusEffect getPrimaryEffect() {
		return primaryEffect;
	}
}
