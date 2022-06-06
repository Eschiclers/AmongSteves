package dev.chicle.amongsteves.command;

import dev.chicle.amongsteves.AmongSteves;
import dev.chicle.amongsteves.gamestate.GameState;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class ChangeStateCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) return false;
        Player p = (Player) sender;

        if(args.length < 1) return false;

        GameState state = GameState.byName(args[0].toLowerCase());

        if(state == null) {
            p.sendMessage(AmongSteves.chatPrefix + ChatColor.RED + "El estado " + args[0] + " no existe.");
            return false;
        }

        AmongSteves.GameManager.setState(state);

        p.sendMessage(AmongSteves.chatPrefix + ChatColor.GREEN + "Estado cambiado a " + state);
        return true;
    }
}
