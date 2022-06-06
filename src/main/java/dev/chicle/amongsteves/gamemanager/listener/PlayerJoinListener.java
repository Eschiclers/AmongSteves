package dev.chicle.amongsteves.gamemanager.listener;

import dev.chicle.amongsteves.AmongSteves;
import dev.chicle.amongsteves.gamemanager.GameManager;
import dev.chicle.amongsteves.gamestate.GameState;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
    private final AmongSteves plugin;

    public PlayerJoinListener(AmongSteves plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent ev) {
        FileConfiguration config = plugin.getConfig();
        Player player = ev.getPlayer();

        switch (GameManager.getState()){
        }

        Location lobby = new Location(
                player.getWorld(),
                config.getDouble("lobby.coords.x"),
                config.getDouble("lobby.coords.y"),
                config.getDouble("lobby.coords.z"),
                (float) config.getDouble("lobby.coords.yaw"),
                (float) config.getDouble("lobby.coords.pitch"));

        player.teleport(lobby);

        player.sendMessage(
                ChatColor.AQUA + "[AmongSteves] " +
                        ChatColor.RESET + "Bienvenido! Actualmente la partida esta " +
                        ChatColor.ITALIC + GameManager.getState().getLabel() + ChatColor.RESET + ".");
    }
}
