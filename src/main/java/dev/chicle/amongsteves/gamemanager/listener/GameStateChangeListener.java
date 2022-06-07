package dev.chicle.amongsteves.gamemanager.listener;

import dev.chicle.amongsteves.AmongSteves;
import dev.chicle.amongsteves.config.Locations;
import dev.chicle.amongsteves.gamemanager.GameManager;
import dev.chicle.amongsteves.event.GameStateChangeEvent;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.List;

public class GameStateChangeListener implements Listener {
    @EventHandler
    public void onGameStateChange(GameStateChangeEvent ev){

        switch (ev.getNewGameState()) {
            case IN_GAME -> List.of(GameManager.getPlayers()).forEach(p -> {
                p.getPlayer().teleport(Locations.getStart());
                p.getPlayer().sendMessage(AmongSteves.chatPrefix +
                        ChatColor.RESET + "La partida ha comenzado!");
            });
            case IN_LOBBY -> List.of(GameManager.getPlayers()).forEach(p -> {
                p.getPlayer().teleport(Locations.getLobby());
                p.getPlayer().sendMessage(AmongSteves.chatPrefix +
                        ChatColor.RESET + "La partida ha terminado. Esperando a que comience una nueva partida.");
            });
        }
    }
}
