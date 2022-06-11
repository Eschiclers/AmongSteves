package dev.chicle.amongsteves.gamemanager.listener;

import dev.chicle.amongsteves.AmongSteves;
import dev.chicle.amongsteves.event.PlayerChangeColorEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerChangeColorListener implements Listener {
    @EventHandler
    public void onPlayerChangeColor(PlayerChangeColorEvent ev) {
        AmongSteves.getLog().info(String.format("El jugador %s ha intentado cambiar de color %s a color %s",
                ev.getPlayer().getName(),
                ev.getOldColor().name(),
                ev.getNewColor().name()));
    }

}
