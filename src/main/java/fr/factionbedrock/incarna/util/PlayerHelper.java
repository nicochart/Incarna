package fr.factionbedrock.incarna.util;

import fr.factionbedrock.incarna.choice.IncarnaChoice;
import fr.factionbedrock.incarna.choice.IncarnaSpecie;
import fr.factionbedrock.incarna.choice.IncarnaTeam;
import fr.factionbedrock.incarna.power.AbilityPower;
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
    public static int MIN_INCARNA_EXPERIENCE = 0;
    public static int MAX_INCARNA_EXPERIENCE = 10713600; //124 days in seconds
    public static int getPlayerIncarnaExperience(PlayerEntity player) {return player.getDataTracker().get(IncarnaTrackedData.INCARNA_EXPERIENCE);}
    public static IncarnaTeam getPlayerTeam(PlayerEntity player) {return IncarnaTeams.TEAM_NAMES.get(player.getDataTracker().get(IncarnaTrackedData.TEAM));}
    public static IncarnaSpecie getPlayerSpecies(PlayerEntity player) {return IncarnaSpecies.SPECIES_NAMES.get(player.getDataTracker().get(IncarnaTrackedData.SPECIES));}

    public static int getPlayerIncarnaLevel(PlayerEntity player)
    {
        int xp = getPlayerIncarnaExperience(player);
        if (xp < 1000) {return 1;}
        else if (xp < 4000) {return 2;}
        else if (xp < 12000) {return 3;}
        else if (xp < 36000) {return 4;}
        else if (xp < 120000) {return 5;}
        else if (xp < 400000) {return 6;}
        else if (xp < 1200000) {return 7;}
        else if (xp < 3000000) {return 8;}
        else if (xp < 6000000) {return 9;}
        else {return 10;}
    }

    public static boolean arePlayersInSameTeam(PlayerEntity player1, PlayerEntity player2) {return getPlayerTeam(player1) == getPlayerTeam(player2);}

    public static void deltaPlayerIncarnaExperience(PlayerEntity player, ExperienceDeltaReason reason)
    {
        int previousExperience = getPlayerIncarnaExperience(player);
        player.getDataTracker().set(IncarnaTrackedData.INCARNA_EXPERIENCE, Math.clamp(previousExperience + reason.delta(), MIN_INCARNA_EXPERIENCE, MAX_INCARNA_EXPERIENCE));
    }

    public static void updatePlayerChoice(PlayerEntity player, TrackedData<String> choiceTrackedData, IncarnaChoice previousChoice, IncarnaChoice newChoice)
    {
        player.getDataTracker().set(choiceTrackedData, newChoice.name());
        PlayerHelper.onPlayerChangeTeamOrSpecies(player, previousChoice, newChoice);
    }

    public static void onPlayerChangeTeamOrSpecies(PlayerEntity player, IncarnaChoice previousChoice, IncarnaChoice newChoice)
    {
        String folder = newChoice instanceof IncarnaTeam ? "team/" : "species/";
        if (player instanceof ServerPlayerEntity serverPlayer)
        {
            IncarnaHelper.runFunction(serverPlayer, folder + newChoice.name());
            removeModifiersOnPlayerChangeTeamOrSpecies(serverPlayer, previousChoice);
        }
        resetPlayerIncarnaExperience(player);
    }

    public static boolean canUseAbility(PlayerEntity player) {return !player.hasStatusEffect(IncarnaMobEffects.ABILITY_COOLDOWN) && getPlayerSpecies(player).hasAbility();}

    public static void onPlayerUseAbility(ServerPlayerEntity player)
    {
        for (IncarnaPower power : PlayerHelper.getAllPowersFrom(player))
        {
            if (power instanceof AbilityPower abilityPower)
            {
                abilityPower.tryTick(player, PlayerHelper.getPlayerIncarnaLevel(player));
            }
        }
        IncarnaSpecie playerSpecie = PlayerHelper.getPlayerSpecies(player);
        IncarnaHelper.runFunction(player, "ability/" + playerSpecie.name());
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

    public static void resetPlayerIncarnaExperience(PlayerEntity player)
    {
        player.getDataTracker().set(IncarnaTrackedData.INCARNA_EXPERIENCE, 0);
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
        PlayerHelper.onPlayerChangeTeamOrSpecies(player, previousTeam, IncarnaTeams.DEFAULT);
    }

    public static void resetPlayerSpecies(PlayerEntity player)
    {
        IncarnaSpecie previousSpecies = IncarnaSpecies.SPECIES_NAMES.get(player.getDataTracker().get(IncarnaTrackedData.SPECIES));
        player.getDataTracker().set(IncarnaTrackedData.SPECIES, IncarnaSpecies.DEFAULT.name());
        PlayerHelper.onPlayerChangeTeamOrSpecies(player, previousSpecies, IncarnaSpecies.DEFAULT);
    }
}
