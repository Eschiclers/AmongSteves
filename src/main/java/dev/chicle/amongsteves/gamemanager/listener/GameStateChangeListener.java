package dev.chicle.amongsteves.gamemanager.listener;

import dev.chicle.amongsteves.AmongSteves;
import dev.chicle.amongsteves.config.Locations;
import dev.chicle.amongsteves.gamemanager.GameManager;
import dev.chicle.amongsteves.event.GameStateChangeEvent;
import dev.chicle.amongsteves.gamemanager.GameStateItems;
import dev.chicle.amongsteves.gamemanager.player.ASPlayer;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class GameStateChangeListener implements Listener {
    @EventHandler
    public void onGameStateChange(GameStateChangeEvent ev) {

        for (ASPlayer asPlayer : GameManager.getPlayers()) {
            Player player = asPlayer.getPlayer();
            asPlayer.reset();
            GameStateItems.giveItems(player, ev.getNewGameState());

            player.setGameMode(GameMode.ADVENTURE);
            switch (ev.getNewGameState()) {
                case IN_GAME:
                    player.teleport(Locations.getStart());
                    player.sendMessage(AmongSteves.chatPrefix + ChatColor.GREEN + "La partida ha empezado!");
                    break;
                case IN_LOBBY: {
                    player.teleport(Locations.getLobby());
                    player.sendMessage(AmongSteves.chatPrefix +
                            ChatColor.RESET + "La partida ha finalizado!");
                    asPlayer.stopScheduledTask();
                    break;
                }
                default: {
                    asPlayer.stopScheduledTask();
                    break;
                }
            }


        }


    }
}
