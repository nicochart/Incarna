package fr.factionbedrock.incarna.util;

import fr.factionbedrock.incarna.choice.IncarnaChoice;
import fr.factionbedrock.incarna.choice.IncarnaSpecie;
import fr.factionbedrock.incarna.choice.IncarnaTeam;
import fr.factionbedrock.incarna.config.ServerLoadedConfig;
import fr.factionbedrock.incarna.power.AbilityPower;
import fr.factionbedrock.incarna.power.IncarnaPower;
import fr.factionbedrock.incarna.registry.IncarnaMobEffects;
import fr.factionbedrock.incarna.registry.IncarnaSpecies;
import fr.factionbedrock.incarna.registry.IncarnaTeams;
import fr.factionbedrock.incarna.registry.IncarnaTrackedData;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;

public class PlayerHelper
{
    public static int getPlayerIncarnaExperience(Player player) {return player.getEntityData().get(IncarnaTrackedData.INCARNA_EXPERIENCE);}
    public static IncarnaTeam getPlayerTeam(Player player) {return IncarnaTeams.TEAM_NAMES.get(player.getEntityData().get(IncarnaTrackedData.TEAM));}
    public static IncarnaSpecie getPlayerSpecies(Player player) {return IncarnaSpecies.SPECIES_NAMES.get(player.getEntityData().get(IncarnaTrackedData.SPECIES));}

    public static int getPlayerIncarnaLevel(Player player)
    {
        return getPlayerIncarnaLevelFromExperience(getPlayerIncarnaExperience(player));
    }

    public static int getPlayerIncarnaLevelFromExperience(int xp)
    {
        if (xp < ExperienceHelper.LEVEL_1) {return 1;}
        else if (xp < ExperienceHelper.LEVEL_2) {return 2;}
        else if (xp < ExperienceHelper.LEVEL_3) {return 3;}
        else if (xp < ExperienceHelper.LEVEL_4) {return 4;}
        else if (xp < ExperienceHelper.LEVEL_5) {return 5;}
        else if (xp < ExperienceHelper.LEVEL_6) {return 6;}
        else if (xp < ExperienceHelper.LEVEL_7) {return 7;}
        else if (xp < ExperienceHelper.LEVEL_8) {return 8;}
        else if (xp < ExperienceHelper.LEVEL_9) {return 9;}
        else {return 10;}
    }

    public static ExperienceLevelProgressionInfo getPlayerIncarnaLevelProgressionInfo(Player player)
    {
        int xp = getPlayerIncarnaExperience(player);
        if (xp < ExperienceHelper.LEVEL_1) {return new ExperienceLevelProgressionInfo(ExperienceHelper.LEVEL_0, ExperienceHelper.LEVEL_1, xp);}
        else if (xp < ExperienceHelper.LEVEL_2) {return new ExperienceLevelProgressionInfo(ExperienceHelper.LEVEL_1, ExperienceHelper.LEVEL_2, xp);}
        else if (xp < ExperienceHelper.LEVEL_3) {return new ExperienceLevelProgressionInfo(ExperienceHelper.LEVEL_2, ExperienceHelper.LEVEL_3, xp);}
        else if (xp < ExperienceHelper.LEVEL_4) {return new ExperienceLevelProgressionInfo(ExperienceHelper.LEVEL_3, ExperienceHelper.LEVEL_4, xp);}
        else if (xp < ExperienceHelper.LEVEL_5) {return new ExperienceLevelProgressionInfo(ExperienceHelper.LEVEL_4, ExperienceHelper.LEVEL_5, xp);}
        else if (xp < ExperienceHelper.LEVEL_6) {return new ExperienceLevelProgressionInfo(ExperienceHelper.LEVEL_5, ExperienceHelper.LEVEL_6, xp);}
        else if (xp < ExperienceHelper.LEVEL_7) {return new ExperienceLevelProgressionInfo(ExperienceHelper.LEVEL_6, ExperienceHelper.LEVEL_7, xp);}
        else if (xp < ExperienceHelper.LEVEL_8) {return new ExperienceLevelProgressionInfo(ExperienceHelper.LEVEL_7, ExperienceHelper.LEVEL_8, xp);}
        else if (xp < ExperienceHelper.LEVEL_9) {return new ExperienceLevelProgressionInfo(ExperienceHelper.LEVEL_8, ExperienceHelper.LEVEL_9, xp);}
        else {return new ExperienceLevelProgressionInfo(ExperienceHelper.LEVEL_9, ExperienceHelper.LEVEL_10, xp);}
    }

    public static boolean arePlayersInSameTeam(Player player1, Player player2) {return getPlayerTeam(player1) == getPlayerTeam(player2);}

    public static void deltaPlayerIncarnaExperience(Player player, ExperienceDeltaReason reason)
    {
        int previousExperience = getPlayerIncarnaExperience(player);
        float multiplier = reason.delta() > 0 ? ServerLoadedConfig.XP_GAIN_MULTIPLIER : ServerLoadedConfig.XP_LOSS_MULTIPLIER;
        player.getEntityData().set(IncarnaTrackedData.INCARNA_EXPERIENCE, Math.clamp((int) (previousExperience + multiplier * reason.delta()), ExperienceHelper.MIN_INCARNA_EXPERIENCE, ExperienceHelper.MAX_INCARNA_EXPERIENCE));
    }

    public static void updatePlayerChoice(Player player, EntityDataAccessor<String> choiceTrackedData, IncarnaChoice previousChoice, IncarnaChoice newChoice)
    {
        player.getEntityData().set(choiceTrackedData, newChoice.name());
        PlayerHelper.onPlayerChangeTeamOrSpecies(player, previousChoice, newChoice);
    }

    public static void onPlayerChangeTeamOrSpecies(Player player, IncarnaChoice previousChoice, IncarnaChoice newChoice)
    {
        String folder = newChoice instanceof IncarnaTeam ? "team/" : "species/";
        if (player instanceof ServerPlayer serverPlayer)
        {
            IncarnaHelper.runFunction(serverPlayer, folder + newChoice.name());
            removeModifiersOnPlayerChangeTeamOrSpecies(serverPlayer, previousChoice);
        }
        resetPlayerIncarnaExperience(player);
    }

    public static boolean canUseAbility(Player player) {return !player.hasEffect(IncarnaMobEffects.ABILITY_COOLDOWN) && getPlayerSpecies(player).hasAbility();}

    public static void onPlayerUseAbility(ServerPlayer player)
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
        player.addEffect(new MobEffectInstance(IncarnaMobEffects.ABILITY_COOLDOWN, 600));
    }

    public static List<IncarnaPower> getAllPowersFrom(Player player)
    {
        IncarnaTeam playerTeam = getPlayerTeam(player);
        IncarnaSpecie playerSpecies = getPlayerSpecies(player);

        List<IncarnaPower> powerList = new ArrayList<>();
        powerList.addAll(playerTeam.powers());
        powerList.addAll(playerSpecies.powers());
        return powerList;
    }

    public static void removeModifiersOnPlayerChangeTeamOrSpecies(ServerPlayer player, IncarnaChoice previousChoice)
    {
        for (IncarnaPower power : previousChoice.powers())
        {
            power.onRemovedFromPlayer(player);
        }
    }

    public static void resetPlayerIncarnaExperience(Player player)
    {
        player.getEntityData().set(IncarnaTrackedData.INCARNA_EXPERIENCE, 0);
    }

    public static void resetPlayerChoices(Player player)
    {
        resetPlayerTeam(player);
        resetPlayerSpecies(player);
    }

    public static void resetPlayerTeam(Player player)
    {
        IncarnaTeam previousTeam = IncarnaTeams.TEAM_NAMES.get(player.getEntityData().get(IncarnaTrackedData.TEAM));
        player.getEntityData().set(IncarnaTrackedData.TEAM, IncarnaTeams.DEFAULT.name());
        PlayerHelper.onPlayerChangeTeamOrSpecies(player, previousTeam, IncarnaTeams.DEFAULT);
    }

    public static void resetPlayerSpecies(Player player)
    {
        IncarnaSpecie previousSpecies = IncarnaSpecies.SPECIES_NAMES.get(player.getEntityData().get(IncarnaTrackedData.SPECIES));
        player.getEntityData().set(IncarnaTrackedData.SPECIES, IncarnaSpecies.DEFAULT.name());
        PlayerHelper.onPlayerChangeTeamOrSpecies(player, previousSpecies, IncarnaSpecies.DEFAULT);
    }
}
