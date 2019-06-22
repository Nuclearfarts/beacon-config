package com.nuclearfarts.beaconconfig;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import net.fabricmc.fabric.api.resource.SimpleResourceReloadListener;
import net.minecraft.block.entity.BeaconBlockEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;

public class ConfigResourceListener implements SimpleResourceReloadListener<BeaconConfig> {

	private static final Gson GSON = new GsonBuilder()
			.registerTypeAdapter(StatusEffect.class, new StatusEffectDeserializer())
			.registerTypeAdapter(BeaconConfig.class, new BeaconConfig.Creator())
			.create();
	public static final Identifier CONFIG_ID = new Identifier("beaconconfig", "config/beaconconfig.json");
	
	@Override
	public Identifier getFabricId() {
		return new Identifier("beaconconfig", "configlistener");
	}

	@Override
	public CompletableFuture<BeaconConfig> load(ResourceManager manager, Profiler profiler, Executor executor) {
		return CompletableFuture.supplyAsync(() -> {
			try {
				return GSON.fromJson(new InputStreamReader(manager.getResource(CONFIG_ID).getInputStream()), BeaconConfig.class);
			} catch (JsonSyntaxException | JsonIOException | IOException e) {
				e.printStackTrace();
				return new BeaconConfig();
			}
		}, executor);
	}

	@Override
	public CompletableFuture<Void> apply(BeaconConfig data, ResourceManager manager, Profiler profiler, Executor executor) {
		return CompletableFuture.runAsync(() -> {
			System.arraycopy(data.effects, 0, BeaconBlockEntity.EFFECTS_BY_LEVEL, 0, 4);
		}, executor);
	}

}
