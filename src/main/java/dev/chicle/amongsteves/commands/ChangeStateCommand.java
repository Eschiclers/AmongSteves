package dev.chicle.amongsteves.commands;

import dev.chicle.amongsteves.gamestate.GameState;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class ChangeStateCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) return false;

        Player p = (Player) sender;
        GameState.setState(GameState.IN_GAME);

        return true;
    }
}
