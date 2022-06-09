package dev.chicle.amongsteves.gamemanager.listener;

import dev.chicle.amongsteves.AmongSteves;
import dev.chicle.amongsteves.config.Locations;
import dev.chicle.amongsteves.gamemanager.GameManager;
import dev.chicle.amongsteves.gamemanager.GameState;
import dev.chicle.amongsteves.gamemanager.player.ASPlayer;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

public class PlayerJoinListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent ev) {
        Player p = ev.getPlayer();
        GameManager.addPlayer(new ASPlayer(p, false));

        p.sendMessage(AmongSteves.chatPrefix +
                ChatColor.RESET + "Bienvenido! Actualmente la partida se encuentra " +
                ChatColor.ITALIC + GameManager.getState().getLabel() + ChatColor.RESET + ".");

        if (GameManager.getState() == GameState.IN_LOBBY) {
            p.getInventory().clear();

            ItemStack bookMenu = new ItemStack(Material.BOOK, 1);
            ItemMeta meta = bookMenu.getItemMeta();

            meta.setDisplayName(ChatColor.RESET + "" + ChatColor.GOLD + "Abrir menu");
            meta.setLore(Collections.singletonList(ChatColor.RESET + "" + ChatColor.GRAY + "Tambien puedes usar /menu"));

            bookMenu.setItemMeta(meta);

            p.getInventory().setItem(4, bookMenu);

            p.teleport(Locations.getLobby());
        }
    }
}
