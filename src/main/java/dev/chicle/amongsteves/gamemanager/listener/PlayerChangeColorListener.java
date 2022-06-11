package dev.chicle.amongsteves.gamemanager.listener;

import dev.chicle.amongsteves.event.PlayerChangeColorEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerChangeColorListener implements Listener {
    @EventHandler
    public void onPlayerChangeColor(PlayerChangeColorEvent ev) {
        System.out.printf("El jugador %s ha intentado cambiar de color %s a color %s%n",
                ev.getPlayer().getName(),
                ev.getOldColor().name(),
                ev.getNewColor().name());
    }

}
