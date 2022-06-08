package dev.chicle.amongsteves.inventorymenu;

import de.themoep.inventorygui.GuiStateElement;
import de.themoep.inventorygui.InventoryGui;
import de.themoep.inventorygui.StaticGuiElement;
import dev.chicle.amongsteves.AmongSteves;
import dev.chicle.amongsteves.gamemanager.GameManager;
import dev.chicle.amongsteves.gamemanager.player.ASPlayer;
import dev.chicle.amongsteves.gamemanager.player.PlayerColor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class SelectColorMenu {
    Player player;
    InventoryGui gui;
    String[] guiSetup = {
            "ABCDEFGHI",
            "JKL      ",
            "         "
    };


    public SelectColorMenu(Player player) {
        this.player = player;
        ASPlayer asPlayer = GameManager.getPlayer(player);

        gui = new InventoryGui(AmongSteves.getInstance(), null, "Selecciona un color", guiSetup);
        gui.setFiller(new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1));

        // Iterate PlayerColor enum
        for (PlayerColor color : PlayerColor.values()) {
            int index = color.ordinal() + 65;
            player.sendMessage((char)index + ": " + color.name());

            gui.addElement(new StaticGuiElement((char)index,
                    getChestPlateColor(color),
                    1, // Display a number as the item count
                    click -> {
                        asPlayer.setColor(color);
                        asPlayer.getPlayer().sendMessage(ChatColor.GREEN + "Has seleccionado el color " + color.toString());
                        gui.close();
                        return true;
                    },
                    ChatColor.RESET + "Elegir color: " + color.name()
            ));
        }
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
