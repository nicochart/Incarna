package fr.factionbedrock.incarna.mixin;

import fr.factionbedrock.incarna.registry.IncarnaTeams;
import fr.factionbedrock.incarna.registry.IncarnaTrackedData;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class PlayerDataTrackerMixin
{
	@Inject(at = @At("RETURN"), method = "initDataTracker")
	private void init(DataTracker.Builder builder, CallbackInfo info)
	{
		builder.add(IncarnaTrackedData.TEAM, IncarnaTeams.DEFAULT.value());
	}
}