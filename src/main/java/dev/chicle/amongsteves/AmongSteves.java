package dev.chicle.amongsteves;

import dev.chicle.amongsteves.command.SetupCommand;
import dev.chicle.amongsteves.gamemanager.GameManager;
import dev.chicle.amongsteves.listener.SetupListener;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public final class AmongSteves extends JavaPlugin implements Listener {
    FileConfiguration config = getConfig();
    public static final String chatPrefix = ChatColor.AQUA + "[AmongSteves] " + ChatColor.RESET;

    public static GameManager GameManager = new GameManager();

    @Override
    public void onEnable() {
        // Plugin startup logic
        setUpConfig();
        new SetupListener(this);
        new SetupCommand(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent ev) {
        Player player = ev.getPlayer();
        Location lobby = new Location(
                player.getWorld(),
                config.getDouble("lobby.coords.x"),
                config.getDouble("lobby.coords.y"),
                config.getDouble("lobby.coords.z"),
                (float) config.getDouble("lobby.coords.yaw"),
                (float) config.getDouble("lobby.coords.pitch"));

        player.teleport(lobby);

        player.sendMessage(
                ChatColor.AQUA + "[AmongSteves] " +
                        ChatColor.RESET + "Bienvenido! Actualmente la partida esta " +
                        ChatColor.ITALIC + GameManager.getState().getLabel() + ChatColor.RESET + ".");
    }

    private void setUpConfig() {
        config.addDefault("language", "es_es");
        config.addDefault("lobby.coords.x", 85.5);
        config.addDefault("lobby.coords.y", 34.0);
        config.addDefault("lobby.coords.z", 377.5);
        config.addDefault("lobby.coords.yaw", -90.0f);
        config.addDefault("lobby.coords.pitch", 0.0f);
        config.options().copyDefaults(true);
        saveConfig();
    }
}
