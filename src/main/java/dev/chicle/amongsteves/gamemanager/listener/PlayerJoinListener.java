package dev.chicle.amongsteves.gamemanager.listener;

import dev.chicle.amongsteves.AmongSteves;
import dev.chicle.amongsteves.gamemanager.GameManager;
import dev.chicle.amongsteves.gamemanager.GameStateItems;
import dev.chicle.amongsteves.gamemanager.player.ASPlayer;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent ev) {
        ev.setJoinMessage(null);

        Player p = ev.getPlayer();
        ASPlayer asPlayer = new ASPlayer(p, false);
        GameManager.addPlayer(asPlayer);

        p.sendMessage(AmongSteves.chatPrefix +
                ChatColor.RESET + "Bienvenido! Actualmente la partida se encuentra " +
                ChatColor.ITALIC + GameManager.getState().getLabel() + ChatColor.RESET + ".");

        GameStateItems.giveItems(p, GameManager.getState());
        GameManager.createAndEquipColoredArmor(p, asPlayer.getColor());
    }
}
