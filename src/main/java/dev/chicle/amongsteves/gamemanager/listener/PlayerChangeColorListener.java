package dev.chicle.amongsteves.gamemanager.listener;

import dev.chicle.amongsteves.AmongSteves;
import dev.chicle.amongsteves.event.PlayerChangeColorEvent;
import dev.chicle.amongsteves.gamemanager.GameManager;
import dev.chicle.amongsteves.gamemanager.GameState;
import dev.chicle.amongsteves.gamemanager.player.ASPlayer;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerChangeColorListener implements Listener {
    @EventHandler
    public void onPlayerChangeColor(PlayerChangeColorEvent ev) {
        System.out.printf("El jugador %s ha intentado cambiar de color %s a color %s%n",
                ev.getPlayer().getDisplayName(),
                ev.getOldColor().name(),
                ev.getNewColor().name());
    }

}
