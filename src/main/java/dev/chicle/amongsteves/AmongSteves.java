package dev.chicle.amongsteves;

import dev.chicle.amongsteves.command.SetupCommand;
import dev.chicle.amongsteves.config.SetupConfiguration;
import dev.chicle.amongsteves.gamemanager.GameManager;
import dev.chicle.amongsteves.gamemanager.Teams;
import dev.chicle.amongsteves.gamemanager.player.PlayerColor;
import dev.chicle.amongsteves.listener.SetupListener;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public final class AmongSteves extends JavaPlugin {
    private static AmongSteves instance;
    @Getter
    private static Log log;
    public static final String prefix = "[AmongSteves] ";
    FileConfiguration config = getConfig();

    public static final String chatPrefix = ChatColor.AQUA + prefix + ChatColor.RESET;

    public static GameManager GameManager;

    @Override
    public void onEnable() {

        if(Bukkit.getMaxPlayers() > PlayerColor.values().length){
            Bukkit.getPluginManager().disablePlugin(this);
            throw new RuntimeException("La cantidad de jugadores no puede ser mayor a la cantidad de colores disponibles.");
        }

        instance = this;
        log = new Log(this);
        // Plugin startup logic
        new SetupConfiguration(this);
        new SetupListener(this);
        new SetupCommand(this);

        GameManager = new GameManager(this);
        Teams.registerTeams();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static boolean checkPermissions(Player player, String permission){
        if(!player.hasPermission(permission)){
            player.sendMessage(chatPrefix + ChatColor.RED + "No tienes permisos para hacer eso.");
            return false;
        }
        return true;
    }

    public static AmongSteves getInstance(){
        return instance;
    }
}
