package fr.factionbedrock.incarna.util;

import fr.factionbedrock.incarna.choice.IncarnaChoice;
import fr.factionbedrock.incarna.choice.IncarnaSpecie;
import fr.factionbedrock.incarna.choice.IncarnaTeam;
import fr.factionbedrock.incarna.power.IncarnaPower;
import fr.factionbedrock.incarna.registry.IncarnaMobEffects;
import fr.factionbedrock.incarna.registry.IncarnaSpecies;
import fr.factionbedrock.incarna.registry.IncarnaTeams;
import fr.factionbedrock.incarna.registry.IncarnaTrackedData;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.ArrayList;
import java.util.List;

public class PlayerHelper
{
    public static IncarnaTeam getPlayerTeam(PlayerEntity player) {return IncarnaTeams.TEAM_NAMES.get(player.getDataTracker().get(IncarnaTrackedData.TEAM));}
    public static IncarnaSpecie getPlayerSpecies(PlayerEntity player) {return IncarnaSpecies.SPECIES_NAMES.get(player.getDataTracker().get(IncarnaTrackedData.SPECIES));}

    public static void updatePlayerChoice(PlayerEntity player, TrackedData<String> choiceTrackedData, IncarnaChoice previousChoice, IncarnaChoice newChoice)
    {
        player.getDataTracker().set(choiceTrackedData, newChoice.name());
        if (player instanceof ServerPlayerEntity serverPlayer) {PlayerHelper.onPlayerChangeTeamOrSpecies(serverPlayer, previousChoice, newChoice);}
    }

    public static void onPlayerChangeTeamOrSpecies(ServerPlayerEntity player, IncarnaChoice previousChoice, IncarnaChoice newChoice)
    {
        String folder = newChoice instanceof IncarnaTeam ? "team/" : "species/";
        IncarnaHelper.runFunction(player, folder + newChoice.name());
        removeModifiersOnPlayerChangeTeamOrSpecies(player, previousChoice);
    }

    public static boolean canUseAbility(PlayerEntity player) {return !player.hasStatusEffect(IncarnaMobEffects.ABILITY_COOLDOWN);}

    public static void onPlayerUseAbility(ServerPlayerEntity player)
    {
        player.addStatusEffect(new StatusEffectInstance(IncarnaMobEffects.ABILITY_COOLDOWN, 600));
    }

    public static List<IncarnaPower> getAllPowersFrom(PlayerEntity player)
    {
        IncarnaTeam playerTeam = getPlayerTeam(player);
        IncarnaSpecie playerSpecies = getPlayerSpecies(player);

        List<IncarnaPower> powerList = new ArrayList<>();
        powerList.addAll(playerTeam.powers());
        powerList.addAll(playerSpecies.powers());
        return powerList;
    }

    public static void removeModifiersOnPlayerChangeTeamOrSpecies(ServerPlayerEntity player, IncarnaChoice previousChoice)
    {
        for (IncarnaPower power : previousChoice.powers())
        {
            power.onRemovedFromPlayer(player);
        }
    }

    public static void resetPlayerChoices(PlayerEntity player)
    {
        resetPlayerTeam(player);
        resetPlayerSpecies(player);
    }

    public static void resetPlayerTeam(PlayerEntity player)
    {
        IncarnaTeam previousTeam = IncarnaTeams.TEAM_NAMES.get(player.getDataTracker().get(IncarnaTrackedData.TEAM));
        player.getDataTracker().set(IncarnaTrackedData.TEAM, IncarnaTeams.DEFAULT.name());
        if (player instanceof ServerPlayerEntity serverPlayer) {PlayerHelper.onPlayerChangeTeamOrSpecies(serverPlayer, previousTeam, IncarnaTeams.DEFAULT);}
    }

    public static void resetPlayerSpecies(PlayerEntity player)
    {
        IncarnaSpecie previousSpecies = IncarnaSpecies.SPECIES_NAMES.get(player.getDataTracker().get(IncarnaTrackedData.SPECIES));
        player.getDataTracker().set(IncarnaTrackedData.SPECIES, IncarnaSpecies.DEFAULT.name());
        if (player instanceof ServerPlayerEntity serverPlayer) {PlayerHelper.onPlayerChangeTeamOrSpecies(serverPlayer, previousSpecies, IncarnaSpecies.DEFAULT);}
    }
}
