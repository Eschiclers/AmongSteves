package dev.chicle.amongsteves.command;

import dev.chicle.amongsteves.gamemanager.GameManager;
import dev.chicle.amongsteves.gamemanager.GameState;
import dev.chicle.amongsteves.gamemanager.player.ASPlayer;
import dev.chicle.amongsteves.gamemanager.player.PlayerRole;
import net.md_5.bungee.api.ChatMessageType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StartGameCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return true;
        Player p = (Player) sender;

        GameManager.setState(GameState.IN_GAME);

        List<ASPlayer> impostors = new ArrayList<>();

        Random random = new Random();

        while (impostors.size() < GameManager.getMaxImpostors()) {
            ASPlayer impostor = GameManager.getPlayers().get(random.nextInt(GameManager.getPlayers().size()));
            if (impostors.contains(impostor)) continue;
            impostors.add(impostor);
            impostor.setRole(PlayerRole.IMPOSTOR);
        }

        sender.sendMessage("Eres " + GameManager.getPlayer((Player) sender).getRole().toString() + ". " + impostors.size() + "/" + GameManager.getMaxImpostors() + ".");


        return true;
    }
}
