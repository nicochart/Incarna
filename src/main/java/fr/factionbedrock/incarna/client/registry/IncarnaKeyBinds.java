package fr.factionbedrock.incarna.client.registry;

import fr.factionbedrock.incarna.Incarna;
import fr.factionbedrock.incarna.client.gui.InfoScreen;
import fr.factionbedrock.incarna.packet.IncarnaData;
import fr.factionbedrock.incarna.util.PlayerHelper;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class IncarnaKeyBinds
{
    public static final KeyBinding ABILITY_KEY = new KeyBinding(
            "key."+ Incarna.MOD_ID+".ability",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_G,
            "category."+ Incarna.MOD_ID
    );

    public static final KeyBinding MENU_KEY = new KeyBinding(
            "key."+ Incarna.MOD_ID+".ability",
            InputUtil.Type.KEYSYM,
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
            while (ABILITY_KEY.wasPressed())
            {
                if (client.player != null && PlayerHelper.canUseAbility(client.player))
                {
                    ClientPlayNetworking.send(new IncarnaData("ability_use"));
                }
            }

            while (MENU_KEY.wasPressed())
            {
                if (client.player != null)
                {
                    client.setScreen(new InfoScreen(client.player));
                }
            }
        });
    }
}
