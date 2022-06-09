package dev.chicle.amongsteves.gamemanager;

import dev.chicle.amongsteves.config.Locations;
import dev.chicle.amongsteves.gamemanager.player.ASPlayer;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

public class GameStateItems {

    public static void giveItems(Player p, GameState state) {
        ASPlayer asPlayer = GameManager.getPlayer(p);
        p.getInventory().clear();

        switch (state) {
            case IN_LOBBY:
                ItemStack bookMenu = new ItemStack(Material.BOOK, 1);
                ItemMeta meta = bookMenu.getItemMeta();

                meta.setDisplayName(ChatColor.RESET + "" + ChatColor.GOLD + "Abrir menu");
                meta.setLore(Collections.singletonList(ChatColor.RESET + "" + ChatColor.GRAY + "Tambien puedes usar /menu"));

                bookMenu.setItemMeta(meta);

                p.getInventory().setItem(4, bookMenu);
                break;
            case IN_GAME:
                break;
        }
    }

}
