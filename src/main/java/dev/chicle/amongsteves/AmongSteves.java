package dev.chicle.amongsteves;

import dev.chicle.amongsteves.command.SetupCommand;
import dev.chicle.amongsteves.config.SetupConfiguration;
import dev.chicle.amongsteves.gamemanager.GameManager;
import dev.chicle.amongsteves.gamemanager.player.PlayerColor;
import dev.chicle.amongsteves.listener.SetupListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public final class AmongSteves extends JavaPlugin {
    private static AmongSteves instance;
    FileConfiguration config = getConfig();

    public static final String chatPrefix = ChatColor.AQUA + "[AmongSteves] " + ChatColor.RESET;

    public static GameManager GameManager;

    @Override
    public void onEnable() {

        if(Bukkit.getMaxPlayers() > PlayerColor.values().length){
            Bukkit.getPluginManager().disablePlugin(this);
            throw new RuntimeException("La cantidad de jugadores no puede ser mayor a la cantidad de colores disponibles.");
        }

        instance = this;
        // Plugin startup logic
        new SetupConfiguration(this);
        new SetupListener(this);
        new SetupCommand(this);

        GameManager = new GameManager(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static AmongSteves getInstance(){
        return instance;
    }
}
