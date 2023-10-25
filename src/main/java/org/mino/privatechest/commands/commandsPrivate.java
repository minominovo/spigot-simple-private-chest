package org.mino.privatechest.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.mino.privatechest.storageSys.storageSys;

public class commandsPrivate implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (s.equalsIgnoreCase("private")) {
            if (commandSender instanceof Player) {
                Player sender = (Player) commandSender;

                Inventory playerInventory = storageSys.getInventory(sender);

                sender.openInventory(playerInventory);

                return true;
            }
        }
        return false;
    }
}