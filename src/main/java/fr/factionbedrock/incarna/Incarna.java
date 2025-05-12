package fr.factionbedrock.incarna;

import fr.factionbedrock.incarna.client.IncarnaClient;
import fr.factionbedrock.incarna.event.PlayerEvents;
import fr.factionbedrock.incarna.registry.*;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Incarna implements ModInitializer, ClientModInitializer
{
	public static final String MOD_ID = "incarna";
	
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override public void onInitialize()
	{
		IncarnaBlocks.load();
		IncarnaItems.load();
		IncarnaBlockEntities.load();
		IncarnaMobEffects.load();
		IncarnaTrackedData.load();
		PlayerEvents.registerUseItemCallback();
	}

	@Override public void onInitializeClient()
	{
		IncarnaClient.registerBlockEntityRenderers();
	}

	public static Identifier id(String path) {return Identifier.of(MOD_ID, path);}
}