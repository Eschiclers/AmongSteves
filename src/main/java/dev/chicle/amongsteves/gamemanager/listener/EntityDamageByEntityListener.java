package dev.chicle.amongsteves.gamemanager.listener;

import dev.chicle.amongsteves.gamemanager.GameManager;
import dev.chicle.amongsteves.gamemanager.GameState;
import dev.chicle.amongsteves.gamemanager.player.ASPlayer;
import dev.chicle.amongsteves.gamemanager.player.PlayerRole;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamageByEntityListener implements Listener {
    @EventHandler
    public void onPlayerClickEntity(EntityDamageByEntityEvent ev) {
        ev.setCancelled(true);

        if (!(ev.getDamager() instanceof Player player) || !(ev.getEntity() instanceof Player target)) return;
        if(GameManager.getState() != GameState.IN_GAME) return;

        ASPlayer asPlayer = GameManager.getPlayer(player);
        ASPlayer asTarget = GameManager.getPlayer(target);

        if((asPlayer.getRole() == PlayerRole.IMPOSTOR && asTarget.getRole() != PlayerRole.IMPOSTOR) && !asPlayer.isActionCooldown()){
            player.sendMessage("matado");
            target.sendMessage("muerto");
        }

    }

}
