package fr.factionbedrock.incarna.client.gui;

import fr.factionbedrock.incarna.Incarna;
import fr.factionbedrock.incarna.util.ExperienceLevelProgressionInfo;
import fr.factionbedrock.incarna.util.PlayerHelper;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class InfoScreen extends Screen
{
    private final PlayerEntity player;

    public InfoScreen(PlayerEntity player)
    {
        super(Text.translatable("gui."+Incarna.MOD_ID+".info_screen").formatted(Formatting.BOLD));
        this.player = player;
    }

    @Override public void render(DrawContext context, int mouseX, int mouseY, float delta)
    {
        this.renderBackground(context, mouseX, mouseY, delta);
        ExperienceLevelProgressionInfo progressInfo = PlayerHelper.getPlayerIncarnaLevelProgressionInfo(player);

        int text_height = 20;
        context.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 2, text_height, 0xFFFFFF);
        text_height+=20;
        context.drawCenteredTextWithShadow(this.textRenderer, Text.translatable("gui."+Incarna.MOD_ID+".info_screen.player_level"), this.width / 2, text_height, 0xFFFFFF);
        text_height+=10;
        context.drawCenteredTextWithShadow(this.textRenderer, Text.literal("" + progressInfo.currentLevel()), this.width / 2, text_height, 0xFF0000);

        text_height+=20;
        context.drawCenteredTextWithShadow(this.textRenderer, Text.translatable("gui."+Incarna.MOD_ID+".info_screen.player_next_level_progression"), this.width / 2, text_height, 0xFFFFFF);
        text_height+=10;
        context.drawCenteredTextWithShadow(this.textRenderer, Text.literal(progressInfo.progression() + " / "+progressInfo.stepXpDelta() + " ("+Math.round(progressInfo.progressionPercentage() * 10.0F) / 10.0F + "%)"), this.width / 2, text_height, 0xFF0000);

        text_height+=20;
        context.drawCenteredTextWithShadow(this.textRenderer, Text.translatable("gui."+Incarna.MOD_ID+".info_screen.player_experience"), this.width / 2, text_height, 0xFFFFFF);
        text_height+=10;
        context.drawCenteredTextWithShadow(this.textRenderer, Text.literal("" + progressInfo.currentXp()), this.width / 2, text_height, 0xFF0000);
    }

    @Override public boolean shouldPause() {return false;}
}
