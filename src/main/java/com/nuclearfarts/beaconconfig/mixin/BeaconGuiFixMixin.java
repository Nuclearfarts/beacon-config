package com.nuclearfarts.beaconconfig.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import com.nuclearfarts.beaconconfig.PrimaryEffectProvider;

import net.minecraft.client.gui.screen.ingame.BeaconScreen;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.entity.effect.StatusEffect;

@Mixin(targets = "net.minecraft.client.gui.screen.ingame.BeaconScreen$EffectButtonWidget")
public abstract class BeaconGuiFixMixin {
	/** BeaconScreen.this */
	@Shadow
	private BeaconScreen field_2811;
	@Shadow
	private StatusEffect effect;
	@Shadow
	private boolean primary;
	
	@Overwrite //i doubt any other mod will be messing with the beacon GUI's tooltip function
	public void renderToolTip(int int_1, int int_2) {
        String string_1 = I18n.translate(this.effect.getTranslationKey());
        if (!this.primary && this.effect == ((PrimaryEffectProvider)field_2811).getPrimaryEffect()) {
           string_1 = string_1 + " II";
        }

        field_2811.renderTooltip(string_1, int_1, int_2);
     }
}
