package dev.chicle.amongsteves.command;

import dev.chicle.amongsteves.AmongSteves;
import dev.chicle.amongsteves.gamemanager.GameState;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class ChangeStateCommand implements CommandExecutor, TabCompleter {

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

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return null;

        if (command.getName().equals("change")) {
            if (args.length == 1) {
                // Return a list of states that contains the given string
                return Stream.of(GameState.values())
                        .filter(state -> state.name().toLowerCase().contains(args[0].toLowerCase()))
                        .map(GameState::name)
                        .collect(Collectors.toList());
            }
        }
        return null;
    }
}
