package fr.factionbedrock.incarna.util;

import fr.factionbedrock.incarna.choice.IncarnaSpecie;
import fr.factionbedrock.incarna.choice.IncarnaTeam;
import fr.factionbedrock.incarna.power.IncarnaPower;
import fr.factionbedrock.incarna.registry.IncarnaSpecies;
import fr.factionbedrock.incarna.registry.IncarnaTeams;
import fr.factionbedrock.incarna.registry.IncarnaTrackedData;
import net.minecraft.entity.player.PlayerEntity;

import java.util.ArrayList;
import java.util.List;

public class PlayerHelper
{
    public static List<IncarnaPower> getAllPowersFrom(PlayerEntity player)
    {
        IncarnaTeam playerTeam = IncarnaTeams.TEAM_NAMES.get(player.getDataTracker().get(IncarnaTrackedData.TEAM));
        IncarnaSpecie playerSpecies = IncarnaSpecies.SPECIES_NAMES.get(player.getDataTracker().get(IncarnaTrackedData.SPECIES));

        List<IncarnaPower> powerList = new ArrayList<>();
        powerList.addAll(playerTeam.powers());
        powerList.addAll(playerSpecies.powers());
        return powerList;
    }

    public static void resetPlayerChoices(PlayerEntity player)
    {
        resetPlayerTeam(player);
        resetPlayerSpecies(player);
    }

    public static void resetPlayerTeam(PlayerEntity player) {player.getDataTracker().set(IncarnaTrackedData.TEAM, IncarnaTeams.DEFAULT.name());}
    public static void resetPlayerSpecies(PlayerEntity player) {player.getDataTracker().set(IncarnaTrackedData.SPECIES, IncarnaSpecies.DEFAULT.name());}
}
