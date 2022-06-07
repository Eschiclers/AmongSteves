package dev.chicle.amongsteves.gamemanager.listener;

import dev.chicle.amongsteves.AmongSteves;
import dev.chicle.amongsteves.config.Locations;
import dev.chicle.amongsteves.gamemanager.GameManager;
import dev.chicle.amongsteves.gamestate.GameState;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent ev) {
        Player p = ev.getPlayer();

        if (GameManager.getState() == GameState.IN_LOBBY) {
            p.teleport(Locations.getLobby());
        }

        p.sendMessage(AmongSteves.chatPrefix +
                ChatColor.RESET + "Bienvenido! Actualmente la partida esta " +
                ChatColor.ITALIC + GameManager.getState().getLabel() + ChatColor.RESET + ".");
    }
}