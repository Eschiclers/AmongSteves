package dev.chicle.amongsteves.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

public class SetupConfiguration {
    Plugin plugin;
    FileConfiguration config;
    Locations locations;

    public SetupConfiguration(Plugin p){
        plugin = p;
        config = p.getConfig();
        loadDefault();

        locations = new Locations(p);
    }

    private void loadDefault() {
        config.addDefault("language", "es_es");
        // Habitación del lobby
        config.addDefault("locations.lobby.coords.x", 118.5);
        config.addDefault("locations.lobby.coords.y", 39);
        config.addDefault("locations.lobby.coords.z", 388.5);
        config.addDefault("locations.lobby.coords.yaw", -180.0f);
        config.addDefault("locations.lobby.coords.pitch", 0.0f);

        // Habitación del botón
        config.addDefault("locations.start.coords.x", 118.5);
        config.addDefault("locations.start.coords.y", 34.5);
        config.addDefault("locations.start.coords.z", 385.85);
        config.addDefault("locations.start.coords.yaw", 0.0f);
        config.addDefault("locations.start.coords.pitch", 0.0f);

        config.options().copyDefaults(true);
        plugin.saveConfig();
    }
}
