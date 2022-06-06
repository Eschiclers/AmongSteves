package dev.chicle.amongsteves.gamemanager.listener;

import dev.chicle.amongsteves.AmongSteves;
import dev.chicle.amongsteves.config.Locations;
import dev.chicle.amongsteves.gamestate.GameState;
import dev.chicle.amongsteves.gamestate.event.GameStateChangeEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class GameStateChangeListener implements Listener {
    @EventHandler
    public void onGameStateChange(GameStateChangeEvent ev){

        if(ev.getNewGameState() == GameState.IN_GAME){
            Bukkit.getOnlinePlayers().forEach(p -> {
                p.teleport(Locations.getStart());
                p.sendMessage(AmongSteves.chatPrefix +
                        ChatColor.RESET + "¡La partida ha comenzado!");
            });
        } else if(ev.getNewGameState() == GameState.IN_LOBBY){
            Bukkit.getOnlinePlayers().forEach(p -> {
                p.teleport(Locations.getLobby());
                p.sendMessage(AmongSteves.chatPrefix +
                        ChatColor.RESET + "La partida ha terminado. Esperando a que todos se unan.");
            });
        }
    }
}
