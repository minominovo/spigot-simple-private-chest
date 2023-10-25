package org.mino.privatechest.eventsHandler;


import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.Inventory;
import org.mino.privatechest.storageSys.storageSys;

public class eventsHandler implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        storageSys.playerJoinCallback(player);
    }

    @EventHandler
    public void onPlayerSwap(PlayerSwapHandItemsEvent event) {
        Player player = event.getPlayer();

        if (player.isSneaking()) {
            event.setCancelled(true);
            Inventory openingInventory = storageSys.getInventory(player);
            player.openInventory(openingInventory);
        }
    }
}
