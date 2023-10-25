package org.mino.privatechest;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.mino.privatechest.commands.commandsPrivate;
import org.mino.privatechest.eventsHandler.eventsHandler;
import org.mino.privatechest.storageSys.storageSys;

import java.io.File;
import java.io.IOException;

public final class PrivateChest extends JavaPlugin {
    public static FileConfiguration configS;
    public static File DataFolder;

    @Override
    public void onEnable() {
        DataFolder = getDataFolder();
        configS = getConfig();

        Bukkit.getPluginManager().registerEvents(new eventsHandler(), this);

        this.getCommand("private").setExecutor(new commandsPrivate());

        try {
            storageSys.onSetup();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InvalidConfigurationException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void onDisable() {
        try {
            storageSys.onDestroy();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
