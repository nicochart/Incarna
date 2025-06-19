package fr.factionbedrock.incarna.util;

public record ExperienceLevelProgressionInfo(int previousXpStep, int nextXpStep, int currentXp)
{
    public int currentLevel() {return PlayerHelper.getPlayerIncarnaLevelFromExperience(currentXp);}

    public int stepXpDelta() {return nextXpStep - previousXpStep;}

    public int progression() {return currentXp - previousXpStep;}

    public float progressionPercentage() {return 100.0F * progression() / stepXpDelta();}
}
