package dev.chicle.amongsteves.command;

import dev.chicle.amongsteves.AmongSteves;
import dev.chicle.amongsteves.gamemanager.GameManager;
import dev.chicle.amongsteves.gamemanager.GameState;
import dev.chicle.amongsteves.inventorymenu.SelectColorMenu;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChangeColorCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return true;
        Player player = (Player) sender;

        if (GameManager.getState() != GameState.IN_LOBBY) {
            player.sendMessage(AmongSteves.chatPrefix + ChatColor.RED + "No puedes cambiar de color mientras estas en una partida.");
            return true;
        }

        new SelectColorMenu(player);

        return true;
    }
}