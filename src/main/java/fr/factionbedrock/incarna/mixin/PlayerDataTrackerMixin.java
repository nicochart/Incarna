package fr.factionbedrock.incarna.mixin;

import fr.factionbedrock.incarna.registry.IncarnaSpecies;
import fr.factionbedrock.incarna.registry.IncarnaTeams;
import fr.factionbedrock.incarna.registry.IncarnaTrackedData;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static fr.factionbedrock.incarna.util.ExperienceHelper.MIN_INCARNA_EXPERIENCE;

@Mixin(Player.class)
public class PlayerDataTrackerMixin
{
	@Inject(at = @At("RETURN"), method = "defineSynchedData")
	private void init(SynchedEntityData.Builder builder, CallbackInfo info)
	{
		builder.define(IncarnaTrackedData.INCARNA_EXPERIENCE, MIN_INCARNA_EXPERIENCE);
		builder.define(IncarnaTrackedData.TEAM, IncarnaTeams.DEFAULT.name());
		builder.define(IncarnaTrackedData.SPECIES, IncarnaSpecies.DEFAULT.name());
	}
}