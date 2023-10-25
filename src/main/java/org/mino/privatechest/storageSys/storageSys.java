package org.mino.privatechest.storageSys;

import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.mino.privatechest.PrivateChest;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static org.mino.privatechest.fileSystem.fileSystem.fileSave;

public class storageSys {
    private static Map<String , Inventory> storageMap = new HashMap<>();
    private static final int storageAmount = 36;

    public static YamlConfiguration storageConfig = new YamlConfiguration();
    public static boolean contains(String playerName) {
        return storageMap.containsKey(playerName);
    }
    public static Inventory getInventory(Player player) {
        if (contains(player.getName())) {
            return storageMap.get(player.getName());
        } else {
            Inventory playerInventory = Bukkit.createInventory(player, storageAmount, player.getName() + "的箱子");
            storageSys.addInventory(player, playerInventory);

            return playerInventory;
        }
    }

    public static void addInventory(Player player, Inventory inventory) {
        storageMap.put(player.getName(), inventory);
    }

    public static void onSetup() throws IOException, InvalidConfigurationException {
        storageConfig.load(new File(PrivateChest.DataFolder, "storage.yml"));
    }

    public static void playerJoinCallback(Player player) {
        if (storageConfig.get(player.getName()) != null) {
            ArrayList<Map<?, ?>> playerConfig = (ArrayList<Map<?, ?>>)storageConfig.getMapList(player.getName());
            if (!contains(player.getName())) {
                Inventory playerInventory = getInventory(player);
                for (int i = 0; i < playerInventory.getSize(); i++) {
                    if (playerConfig.get(i) != null) {
                        Map item = playerConfig.get(i);

                        if (!item.isEmpty())
                            playerInventory.setItem(i, ItemStack.deserialize(item));
                    }
                }
            }
        }
    }

    public static void onDestroy() throws IOException {
        YamlConfiguration storageYaml = new YamlConfiguration();

        for (Map.Entry<String, Inventory> entry : storageMap.entrySet()) {
            String key = entry.getKey();
            Inventory value = entry.getValue();

            Iterator<ItemStack> inventoryIterator = value.iterator();

            ArrayList<Map<?, ?>> playerYaml = new ArrayList<>();
            int count = 0;

            for (int i = 0; i < storageAmount; i++) {
                playerYaml.add(new HashMap<>());
            }

            while (inventoryIterator.hasNext()) {
                ItemStack inventoryEntry = inventoryIterator.next();
                if (inventoryEntry != null)
                    playerYaml.set(count, inventoryEntry.serialize());

                count++;
            }

            storageYaml.set(key, playerYaml);

        }

        fileSave(storageYaml, "storage.yml");
    }
}
