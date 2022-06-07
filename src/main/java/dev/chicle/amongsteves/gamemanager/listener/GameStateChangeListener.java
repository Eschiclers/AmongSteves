package dev.chicle.amongsteves.gamemanager.listener;

import dev.chicle.amongsteves.AmongSteves;
import dev.chicle.amongsteves.config.Locations;
import dev.chicle.amongsteves.gamemanager.GameManager;
import dev.chicle.amongsteves.event.GameStateChangeEvent;
import dev.chicle.amongsteves.gamemanager.player.ASPlayer;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class GameStateChangeListener implements Listener {
    @EventHandler
    public void onGameStateChange(GameStateChangeEvent ev) {

        switch (ev.getNewGameState()) {
            case IN_GAME:
                for (ASPlayer p : GameManager.getPlayers()) {
                    p.getPlayer().teleport(Locations.getStart());
                    p.getPlayer().sendMessage(AmongSteves.chatPrefix + ChatColor.GREEN + "La partida ha empezado!");
                }
                break;
            case IN_LOBBY: {
                for (ASPlayer p : GameManager.getPlayers()) {
                    p.getPlayer().teleport(Locations.getLobby());
                    p.getPlayer().sendMessage(AmongSteves.chatPrefix +
                            ChatColor.RESET + "La partida ha finalizado!");
                }
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown game state: " + ev.getNewGameState());
        }
    }
}
