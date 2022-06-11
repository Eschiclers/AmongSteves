package dev.chicle.amongsteves.inventorymenu;

import de.themoep.inventorygui.InventoryGui;
import de.themoep.inventorygui.StaticGuiElement;
import dev.chicle.amongsteves.AmongSteves;
import dev.chicle.amongsteves.event.PlayerChangeColorEvent;
import dev.chicle.amongsteves.gamemanager.GameManager;
import dev.chicle.amongsteves.gamemanager.player.ASPlayer;
import dev.chicle.amongsteves.gamemanager.player.PlayerColor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class MainMenu {
    Player player;
    InventoryGui gui;
    String[] guiSetup = {
            "C       Z"
    };


    public MainMenu(Player player) {
        this.player = player;
        ASPlayer asPlayer = GameManager.getPlayer(player);

        gui = new InventoryGui(AmongSteves.getInstance(), null, "Menu de partida", guiSetup);
        gui.setFiller(new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1));

        gui.addElement(new StaticGuiElement('C',
                getChestPlateColor(asPlayer.getColor()),
                1,
                click -> {
                    gui.close();
                    new SelectColorMenu(player);
                    return true;
                },
                ChatColor.RESET + "Cambiar color"
        ));

        gui.addElement(new StaticGuiElement('Z',
                new ItemStack(Material.BARRIER),
                1,
                click -> {
                    gui.close();
                    return true;
                },
                ChatColor.RESET + "Cerrar menu"
        ));

        gui.show(player);
    }

    private ItemStack getChestPlateColor(PlayerColor color) {
        ItemStack chestPlate = new ItemStack(Material.LEATHER_CHESTPLATE);
        LeatherArmorMeta meta = (LeatherArmorMeta) chestPlate.getItemMeta();
        meta.setColor(color.getColor());
        meta.setDisplayName(color.name());

        chestPlate.setItemMeta(meta);
        return chestPlate;
    }

    public InventoryGui getGui() {
        return gui;
    }
}
