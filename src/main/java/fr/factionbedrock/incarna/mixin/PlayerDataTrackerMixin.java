package fr.factionbedrock.incarna.mixin;

import fr.factionbedrock.incarna.registry.IncarnaSpecies;
import fr.factionbedrock.incarna.registry.IncarnaTeams;
import fr.factionbedrock.incarna.registry.IncarnaTrackedData;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static fr.factionbedrock.incarna.util.PlayerHelper.MIN_INCARNA_EXPERIENCE;

@Mixin(PlayerEntity.class)
public class PlayerDataTrackerMixin
{
	@Inject(at = @At("RETURN"), method = "initDataTracker")
	private void init(DataTracker.Builder builder, CallbackInfo info)
	{
		builder.add(IncarnaTrackedData.INCARNA_EXPERIENCE, MIN_INCARNA_EXPERIENCE);
		builder.add(IncarnaTrackedData.TEAM, IncarnaTeams.DEFAULT.name());
		builder.add(IncarnaTrackedData.SPECIES, IncarnaSpecies.DEFAULT.name());
	}
}