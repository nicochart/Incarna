package fr.factionbedrock.incarna.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import fr.factionbedrock.incarna.Incarna;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;

public class IncarnaConfigLoader
{
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path CONFIG_FOLDER = FabricLoader.getInstance().getConfigDir().resolve(Incarna.MOD_ID);
    private static final Path CONFIG_PATH = CONFIG_FOLDER.resolve("config.json");

    public static IncarnaConfig loadConfig()
    {
        if (!Files.exists(CONFIG_PATH))
        {
            IncarnaConfig defaultConfig = new IncarnaConfig();
            saveConfig(defaultConfig);
            return defaultConfig;
        }

        try (Reader reader = Files.newBufferedReader(CONFIG_PATH)) {return GSON.fromJson(reader, IncarnaConfig.class);}
        catch (IOException | JsonParseException e)
        {
            e.printStackTrace();
            Incarna.LOGGER.error("Failed to load Not So Hardcore config file, using default configuration instead");
            return new IncarnaConfig();
        }
    }

    public static void saveConfig(IncarnaConfig config)
    {
        try
        {
            Files.createDirectories(CONFIG_FOLDER);
            try (Writer writer = Files.newBufferedWriter(CONFIG_PATH)) {GSON.toJson(config, writer);}
        }
        catch (IOException e) {e.printStackTrace();}
    }
}
