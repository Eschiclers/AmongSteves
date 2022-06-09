package dev.chicle.amongsteves.gamemanager.listener;

import dev.chicle.amongsteves.AmongSteves;
import dev.chicle.amongsteves.config.Locations;
import dev.chicle.amongsteves.gamemanager.GameManager;
import dev.chicle.amongsteves.event.GameStateChangeEvent;
import dev.chicle.amongsteves.gamemanager.GameState;
import dev.chicle.amongsteves.gamemanager.GameStateItems;
import dev.chicle.amongsteves.gamemanager.player.ASPlayer;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class GameStateChangeListener implements Listener {
    @EventHandler
    public void onGameStateChange(GameStateChangeEvent ev) {

        for (ASPlayer asPlayer : GameManager.getPlayers()) {
            asPlayer.getPlayer().getInventory().clear();
            GameManager.createAndEquipColoredArmor(asPlayer.getPlayer(), asPlayer.getColor());
            GameStateItems.giveItems(asPlayer.getPlayer(), ev.getNewGameState());

            switch (ev.getNewGameState()) {
                case IN_GAME:
                    asPlayer.getPlayer().teleport(Locations.getStart());
                    asPlayer.getPlayer().sendMessage(AmongSteves.chatPrefix + ChatColor.GREEN + "La partida ha empezado!");
                    break;
                case IN_LOBBY: {
                    asPlayer.getPlayer().teleport(Locations.getLobby());
                    asPlayer.getPlayer().sendMessage(AmongSteves.chatPrefix +
                            ChatColor.RESET + "La partida ha finalizado!");
                    break;
                }
                default:
                    throw new UnsupportedOperationException("Unknown game state: " + ev.getNewGameState());
            }


        }


    }
}
