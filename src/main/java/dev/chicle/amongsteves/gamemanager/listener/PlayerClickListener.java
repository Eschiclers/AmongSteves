package dev.chicle.amongsteves.gamemanager.listener;

import dev.chicle.amongsteves.gamemanager.GameManager;
import dev.chicle.amongsteves.gamemanager.GameState;
import dev.chicle.amongsteves.inventorymenu.MainMenu;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerClickListener implements Listener {
    @EventHandler
    public void onPlayerClick(PlayerInteractEvent ev) {
        if (GameManager.getState() == GameState.IN_LOBBY) {
            if (ev.getPlayer().getInventory().getHeldItemSlot() == 4) {
                new MainMenu(ev.getPlayer());
            }
        }
        ev.setCancelled(true);
    }
}
