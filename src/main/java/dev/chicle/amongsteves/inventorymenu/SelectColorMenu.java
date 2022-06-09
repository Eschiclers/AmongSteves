package dev.chicle.amongsteves.inventorymenu;

import de.themoep.inventorygui.GuiStateElement;
import de.themoep.inventorygui.InventoryGui;
import de.themoep.inventorygui.StaticGuiElement;
import dev.chicle.amongsteves.AmongSteves;
import dev.chicle.amongsteves.event.GameStateChangeEvent;
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

public class SelectColorMenu {
    Player player;
    InventoryGui gui;
    String[] guiSetup = {
            "ABCDEFGHI",
            "JKL      ",
            "        Z"
    };


    public SelectColorMenu(Player player) {
        this.player = player;
        ASPlayer asPlayer = GameManager.getPlayer(player);

        gui = new InventoryGui(AmongSteves.getInstance(), null, "Selecciona un color", guiSetup);
        gui.setFiller(new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1));

        // Iterate PlayerColor enum
        for (PlayerColor color : PlayerColor.values()) {
            int index = color.ordinal() + 65;

            if(!GameManager.isColorAvailable(color)) continue;

            gui.addElement(new StaticGuiElement((char)index,
                    getChestPlateColor(color),
                    1,
                    click -> {
                        GameManager.changePlayerColor(asPlayer, asPlayer.getColor(), color);
                        gui.close();
                        return true;
                    },
                    ChatColor.RESET + "Elegir color: " + color.name()
            ));
        }

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
