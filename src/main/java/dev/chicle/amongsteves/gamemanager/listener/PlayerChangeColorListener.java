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

        ASPlayer asPlayer = GameManager.getPlayer(ev.getPlayer());

        if(GameManager.getState() != GameState.IN_LOBBY) {
            ev.getPlayer().sendMessage(AmongSteves.chatPrefix + ChatColor.RED + "No puedes cambiar de color mientras estas en una partida.");
            return;
        }

        if(ev.getOldColor() == ev.getNewColor()) {
            ev.getPlayer().sendMessage(AmongSteves.chatPrefix + ChatColor.RED + "Ya eres ese color.");
            return;
        }

        // Check if any player has the same color
        for(ASPlayer p : GameManager.getPlayers()) {
            if(p.getColor() == ev.getNewColor()) {
                ev.getPlayer().sendMessage(AmongSteves.chatPrefix + ChatColor.RED + "Ya hay un jugador con ese color.");
                return;
            }
        }

        // Ha superado todos los checks

        asPlayer.setColor(ev.getNewColor());
        asPlayer.getPlayer().sendMessage(ChatColor.GREEN + "Has seleccionado el color " + ev.getNewColor().name());
    }

}
