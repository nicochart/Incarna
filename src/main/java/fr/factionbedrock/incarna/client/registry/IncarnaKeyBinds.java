package fr.factionbedrock.incarna.client.registry;

import com.mojang.blaze3d.platform.InputConstants;
import fr.factionbedrock.incarna.Incarna;
import fr.factionbedrock.incarna.client.gui.InfoScreen;
import fr.factionbedrock.incarna.packet.IncarnaData;
import fr.factionbedrock.incarna.util.PlayerHelper;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;

public class IncarnaKeyBinds
{
    public static final KeyMapping ABILITY_KEY = new KeyMapping(
            "key."+ Incarna.MOD_ID+".ability",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_G,
            "category."+ Incarna.MOD_ID
    );

    public static final KeyMapping MENU_KEY = new KeyMapping(
            "key."+ Incarna.MOD_ID+".ability",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_I,
            "category."+ Incarna.MOD_ID
    );

    public static void registerKeybindsAndPressedInteractions()
    {
        KeyBindingHelper.registerKeyBinding(ABILITY_KEY);
        registerPressedInteractions();
    }

    public static void registerPressedInteractions()
    {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (ABILITY_KEY.consumeClick())
            {
                if (client.player != null && PlayerHelper.canUseAbility(client.player))
                {
                    ClientPlayNetworking.send(new IncarnaData("ability_use"));
                }
            }

            while (MENU_KEY.consumeClick())
            {
                if (client.player != null)
                {
                    client.setScreen(new InfoScreen(client.player));
                }
            }
        });
    }
}
