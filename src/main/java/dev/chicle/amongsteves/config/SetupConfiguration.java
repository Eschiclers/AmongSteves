package dev.chicle.amongsteves.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

public class SetupConfiguration {
    Plugin plugin;
    FileConfiguration config;

    public SetupConfiguration(Plugin p){
        plugin = p;
        config = p.getConfig();

        loadDefault();
    }

    private void loadDefault() {
        config.addDefault("language", "es_es");
        config.addDefault("lobby.coords.x", 118.5);
        config.addDefault("lobby.coords.y", 39);
        config.addDefault("lobby.coords.z", 388.5);
        config.addDefault("lobby.coords.yaw", -180.0f);
        config.addDefault("lobby.coords.pitch", 0.0f);
        config.options().copyDefaults(true);
        plugin.saveConfig();
    }
}
