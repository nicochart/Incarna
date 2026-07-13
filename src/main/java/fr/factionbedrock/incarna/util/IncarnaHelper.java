package fr.factionbedrock.incarna.util;

import fr.factionbedrock.incarna.Incarna;
import fr.factionbedrock.incarna.choice.IncarnaChoice;
import fr.factionbedrock.incarna.power.AttributeModifierPower;
import fr.factionbedrock.incarna.power.IncarnaPower;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.functions.CommandFunction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import java.util.Optional;

public class IncarnaHelper
{
    public static void runFunction(ServerPlayer user, String functionName)
    {
        MinecraftServer server = user.server;
        ServerLevel world = user.serverLevel();
        ResourceLocation functionId = Incarna.id(functionName);

        Optional<CommandFunction<CommandSourceStack>> mcFunction = server.getFunctions().get(functionId);

        mcFunction.ifPresent(function -> {
            CommandSourceStack source = server.createCommandSourceStack().withEntity(user).withLevel(world).withPosition(user.position()).withSuppressedOutput();
            server.getFunctions().execute(function, source);
        });
    }
}
