package fr.factionbedrock.incarna.mixin;

import fr.factionbedrock.incarna.registry.ExperienceDeltaReasons;
import fr.factionbedrock.incarna.registry.IncarnaTrackedData;
import fr.factionbedrock.incarna.util.ExperienceDeltaReason;
import fr.factionbedrock.incarna.util.PlayerHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public class PlayerDeathMixin
{
    @Inject(at = @At("HEAD"), method = "onDeath")
    private void applyOnDeath(DamageSource damageSource, CallbackInfo info)
    {
        ServerPlayerEntity deadPlayer = (ServerPlayerEntity) (Object) this;
        PlayerHelper.deltaPlayerIncarnaExperience(deadPlayer, ExperienceDeltaReasons.DEATH);
        Entity attackerEntity = damageSource.getAttacker();

        if (attackerEntity instanceof PlayerEntity attackerPlayer)
        {
            ExperienceDeltaReason deltaReason = PlayerHelper.arePlayersInSameTeam(deadPlayer, attackerPlayer) ? ExperienceDeltaReasons.ALLY_PLAYER_KILL : ExperienceDeltaReasons.NON_ALLY_PLAYER_KILL;
            PlayerHelper.deltaPlayerIncarnaExperience(attackerPlayer, deltaReason);
        }
    }

    @Inject(at = @At("HEAD"), method = "copyFrom")
    private void applyOnCopyFrom(ServerPlayerEntity oldPlayer, boolean alive, CallbackInfo info)
    {
        ServerPlayerEntity newPlayer = (ServerPlayerEntity) (Object) this;
        int incarna_experience = oldPlayer.getDataTracker().get(IncarnaTrackedData.INCARNA_EXPERIENCE);
        String team = oldPlayer.getDataTracker().get(IncarnaTrackedData.TEAM);
        String species = oldPlayer.getDataTracker().get(IncarnaTrackedData.SPECIES);
        newPlayer.getDataTracker().set(IncarnaTrackedData.INCARNA_EXPERIENCE, incarna_experience);
        newPlayer.getDataTracker().set(IncarnaTrackedData.TEAM, team);
        newPlayer.getDataTracker().set(IncarnaTrackedData.SPECIES, species);
    }
}