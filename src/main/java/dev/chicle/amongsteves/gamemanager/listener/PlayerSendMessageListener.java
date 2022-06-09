package dev.chicle.amongsteves.gamemanager.listener;

import dev.chicle.amongsteves.gamemanager.GameManager;
import dev.chicle.amongsteves.gamemanager.GameState;
import dev.chicle.amongsteves.gamemanager.player.ASPlayer;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerSendMessageListener implements Listener {
    @EventHandler
    public void onPlayerSendMessage(AsyncPlayerChatEvent ev) {
        ev.setCancelled(true);

        if(GameManager.getState() == GameState.IN_GAME) {
            return;
        }

        ASPlayer asPlayer = GameManager.getPlayer(ev.getPlayer());

        String message = "[" + asPlayer.getColor().getChatColor() + asPlayer.getColor().name() + ChatColor.RESET + "] " + ev.getPlayer().getDisplayName() + ": " + ev.getMessage();

        System.out.printf("El jugador %s ha intentado enviar el mensaje: %s%n",
                ev.getPlayer().getDisplayName(),
                ev.getMessage());


        Bukkit.broadcastMessage(message);
    }
}
