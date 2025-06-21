package fr.factionbedrock.incarna.config;

//accessible client side too. The parameters stored here are the one from the server side (even if player is connected to a dedicated server)
public class ServerLoadedConfig
{
    public static float XP_GAIN_MULTIPLIER;
    public static float XP_LOSS_MULTIPLIER;

    public static void storeServerParams(float xpGainMultiplier, float xpLossMultiplier)
    {
        XP_GAIN_MULTIPLIER = xpGainMultiplier;
        XP_LOSS_MULTIPLIER = xpLossMultiplier;
    }
}
