package dev.chicle.amongsteves;

import dev.chicle.amongsteves.commands.ChangeStateCommand;
import dev.chicle.amongsteves.gamestate.GameState;
import dev.chicle.amongsteves.gamestate.listener.GameStateChangeListener;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public final class AmongSteves extends JavaPlugin implements Listener {
    FileConfiguration config = getConfig();

    @Override
    public void onEnable() {
        // Plugin startup logic
        // Configuración por defecto y esas cosas
        setUpConfig();

        getServer().getPluginManager().registerEvents(this, this);

        getServer().getPluginManager().registerEvents(new GameStateChangeListener(), this);
        getCommand("change").setExecutor(new ChangeStateCommand());
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
                (float)config.getDouble("lobby.coords.yaw"),
                (float)config.getDouble("lobby.coords.pitch"));

        player.teleport(lobby);

        player.sendMessage(
                ChatColor.AQUA + "[AmongSteves] " +
                ChatColor.RESET + "Bienvenido! Actualmente la partida esta " +
                ChatColor.ITALIC + GameState.getState());
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
