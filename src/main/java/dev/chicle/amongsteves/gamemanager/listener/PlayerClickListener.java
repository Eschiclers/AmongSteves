package dev.chicle.amongsteves.gamemanager.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerClickListener implements Listener {
    @EventHandler
    public void onPlayerClick(PlayerInteractEvent ev) {
        //ev.getPlayer().sendMessage("Player clicked!");
        ev.setCancelled(true);
    }
}
