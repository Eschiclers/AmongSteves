package dev.chicle.amongsteves.gamemanager.listener;

import dev.chicle.amongsteves.gamemanager.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerDisconnectListener implements Listener {
    @EventHandler
    public void onPlayerDisconnect(PlayerQuitEvent ev) {
        ev.setQuitMessage(null);

        GameManager.removePlayer(GameManager.getPlayer(ev.getPlayer()));
        Bukkit.getScoreboardManager().getMainScoreboard().getTeam("players").removeEntry(ev.getPlayer().getName());

        GameManager.checkWinner();
    }
}
