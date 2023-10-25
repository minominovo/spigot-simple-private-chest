package org.mino.privatechest.fileSystem;

import org.bukkit.configuration.file.YamlConfiguration;
import org.mino.privatechest.PrivateChest;

import java.io.File;
import java.io.IOException;

public class fileSystem {
    public static YamlConfiguration fileSetValue(String name, int value) {
        YamlConfiguration newConfig = new YamlConfiguration();
        newConfig.set(name, value);

        return newConfig;
    }

    public static YamlConfiguration fileSetValue(String name, String value) {
        YamlConfiguration newConfig = new YamlConfiguration();
        newConfig.set(name, value);

        return newConfig;
    }

    public static YamlConfiguration fileSetValue(String name, boolean value) {
        YamlConfiguration newConfig = new YamlConfiguration();
        newConfig.set(name, value);

        return newConfig;
    }

    public static void fileSave(YamlConfiguration config, String name) throws IOException {
        config.save(new File(PrivateChest.DataFolder, name));
    }
}