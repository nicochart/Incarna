package fr.factionbedrock.incarna.client.gui;

import fr.factionbedrock.incarna.Incarna;
import fr.factionbedrock.incarna.config.ServerLoadedConfig;
import fr.factionbedrock.incarna.util.ExperienceLevelProgressionInfo;
import fr.factionbedrock.incarna.util.PlayerHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class InfoScreen extends Screen
{
    private final Player player;

    public InfoScreen(Player player)
    {
        super(Component.translatable("gui."+Incarna.MOD_ID+".info_screen").withStyle(ChatFormatting.BOLD));
        this.player = player;
    }

    @Override public void render(GuiGraphics context, int mouseX, int mouseY, float delta)
    {
        this.renderBackground(context, mouseX, mouseY, delta);
        ExperienceLevelProgressionInfo progressInfo = PlayerHelper.getPlayerIncarnaLevelProgressionInfo(player);

        int text_height = 20;
        context.drawCenteredString(this.font, this.title, this.width / 2, text_height, 0xFFFFFF);
        text_height+=20;
        context.drawCenteredString(this.font, Component.translatable("gui."+Incarna.MOD_ID+".info_screen.player_level"), this.width / 2, text_height, 0xFFFFFF);
        text_height+=10;
        context.drawCenteredString(this.font, Component.literal("" + progressInfo.currentLevel()), this.width / 2, text_height, 0xFF0000);

        text_height+=20;
        context.drawCenteredString(this.font, Component.translatable("gui."+Incarna.MOD_ID+".info_screen.player_next_level_progression"), this.width / 2, text_height, 0xFFFFFF);
        text_height+=10;
        context.drawCenteredString(this.font, Component.literal(progressInfo.progression() + " / "+progressInfo.stepXpDelta() + " ("+Math.round(progressInfo.progressionPercentage() * 10.0F) / 10.0F + "%)"), this.width / 2, text_height, 0xFF0000);

        text_height+=20;
        context.drawCenteredString(this.font, Component.translatable("gui."+Incarna.MOD_ID+".info_screen.player_experience"), this.width / 2, text_height, 0xFFFFFF);
        text_height+=10;
        context.drawCenteredString(this.font, Component.literal("" + progressInfo.currentXp()), this.width / 2, text_height, 0xFF0000);

        if (ServerLoadedConfig.DISPLAY_DEBUG_INFO_IN_INFO_SCREEN)
        {
            text_height+=20;
            context.drawCenteredString(this.font, Component.translatable("gui."+Incarna.MOD_ID+".info_screen.xp_gain_multiplier"), this.width / 2, text_height, 0xFFFFFF);
            text_height+=10;
            context.drawCenteredString(this.font, Component.literal("" + ServerLoadedConfig.XP_GAIN_MULTIPLIER), this.width / 2, text_height, 0xFF0000);

            text_height+=20;
            context.drawCenteredString(this.font, Component.translatable("gui."+Incarna.MOD_ID+".info_screen.xp_loss_multiplier"), this.width / 2, text_height, 0xFFFFFF);
            text_height+=10;
            context.drawCenteredString(this.font, Component.literal("" + ServerLoadedConfig.XP_LOSS_MULTIPLIER), this.width / 2, text_height, 0xFF0000);
        }
    }

    @Override public boolean isPauseScreen() {return false;}
}
