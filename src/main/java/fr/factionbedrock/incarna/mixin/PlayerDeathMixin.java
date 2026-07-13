package fr.factionbedrock.incarna.mixin;

import fr.factionbedrock.incarna.registry.ExperienceDeltaReasons;
import fr.factionbedrock.incarna.registry.IncarnaTrackedData;
import fr.factionbedrock.incarna.util.ExperienceDeltaReason;
import fr.factionbedrock.incarna.util.PlayerHelper;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayer.class)
public class PlayerDeathMixin
{
    @Inject(at = @At("HEAD"), method = "die")
    private void applyOnDeath(DamageSource damageSource, CallbackInfo info)
    {
        ServerPlayer deadPlayer = (ServerPlayer) (Object) this;
        PlayerHelper.deltaPlayerIncarnaExperience(deadPlayer, ExperienceDeltaReasons.DEATH);
        Entity attackerEntity = damageSource.getEntity();

        if (attackerEntity instanceof Player attackerPlayer)
        {
            ExperienceDeltaReason deltaReason = PlayerHelper.arePlayersInSameTeam(deadPlayer, attackerPlayer) ? ExperienceDeltaReasons.ALLY_PLAYER_KILL : ExperienceDeltaReasons.NON_ALLY_PLAYER_KILL;
            PlayerHelper.deltaPlayerIncarnaExperience(attackerPlayer, deltaReason);
        }
    }

    @Inject(at = @At("HEAD"), method = "restoreFrom")
    private void applyOnCopyFrom(ServerPlayer oldPlayer, boolean alive, CallbackInfo info)
    {
        ServerPlayer newPlayer = (ServerPlayer) (Object) this;
        int incarna_experience = oldPlayer.getEntityData().get(IncarnaTrackedData.INCARNA_EXPERIENCE);
        String team = oldPlayer.getEntityData().get(IncarnaTrackedData.TEAM);
        String species = oldPlayer.getEntityData().get(IncarnaTrackedData.SPECIES);
        newPlayer.getEntityData().set(IncarnaTrackedData.INCARNA_EXPERIENCE, incarna_experience);
        newPlayer.getEntityData().set(IncarnaTrackedData.TEAM, team);
        newPlayer.getEntityData().set(IncarnaTrackedData.SPECIES, species);
    }
}