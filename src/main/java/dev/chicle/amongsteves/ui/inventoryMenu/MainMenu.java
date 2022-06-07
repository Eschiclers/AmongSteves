package dev.chicle.amongsteves.ui.inventoryMenu;

import net.md_5.bungee.api.chat.ClickEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

public class MainMenu implements Listener, CommandExecutor {
    private Inventory inv;
    private int rows = 3;

    public MainMenu() {
    }

    public void initializeItems() {
        /*
            * Initialize the inventory with the items
            * [ 0,  1,  2,  3,  4,  5,  6,  7,  8,
            *   9, 10, 11, 12, 13, 14, 15, 16, 17,
            *  18, 19, 20, 21, 22, 23, 24, 25, 26]
         */

        inv.setItem(10, createGuiItem(Material.STICK, "Palo maestro", ChatColor.GREEN + "Con lore y todo", ChatColor.BLUE + "Que ademas se expande"));
        inv.setItem(11, createGuiItem(Material.STICK, "Palo normal", "un palo"));
        inv.setItem(16, createGuiItem(Material.BARRIER, "Salir"));

        fillInventory(inv, Material.BLUE_STAINED_GLASS_PANE);
    }

    protected ItemStack createGuiItem(final Material material, final String name, final String... lore) {
        final ItemStack item = new ItemStack(material, 1);
        final ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(ChatColor.RESET + name);
        meta.setLore(Arrays.stream(lore).map(s -> ChatColor.RESET + s).collect(Collectors.toList()));


        item.setItemMeta(meta);

        return item;
    }

    public void fillInventory(Inventory inv, Material material) {
        ItemStack item = new ItemStack(material, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(" ");
        item.setItemMeta(meta);

        for(int i = 0; i < inv.getSize(); i++) {
            if(inv.getItem(i) == null) {
                inv.setItem(i, item);
            }
        }
    }

    public void openInventory(final Player p) {
        inv = Bukkit.createInventory(null, 3*9, p.getLocale().equals("es_es") ? "Menu principal" : "Main menu");
        initializeItems();
        p.openInventory(inv);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (!e.getView().getTopInventory().equals(inv)) return;

        e.setCancelled(true);

        final ItemStack clickedItem = e.getCurrentItem();

        if (clickedItem == null || clickedItem.getType().isAir() || clickedItem.getType().equals(Material.GLASS_PANE)) return;

        final Player p = (Player) e.getWhoClicked();

        switch (e.getRawSlot()) {
            case 10:
                p.sendMessage(ChatColor.GREEN + "Palo maestro");
                break;
            case 11:
                p.sendMessage(ChatColor.GREEN + "Palo normal");
                break;
            case 16:
                // Si cierro el inventario mientras hace shift click, le dará el item
                if (e.getClick().isShiftClick()) break;
                p.closeInventory();
                break;

        }
    }

    // Cancel dragging in our inventory
    @EventHandler
    public void onInventoryClick(final InventoryDragEvent e) {
        if (!e.getInventory().equals(inv)) return;
        e.setCancelled(true);

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return false;
        Player p = (Player) sender;
        openInventory(p);
        //p.sendMessage("Idioma: " + p.getLocale());
        return true;
    }
}
