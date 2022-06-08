package dev.chicle.amongsteves.config;
import org.bukkit.plugin.Plugin;

public class SetupConfiguration {
    Plugin plugin;
    Locations locations;

    public SetupConfiguration(Plugin p){
        plugin = p;
        loadDefault();

        locations = new Locations(p);
    }

    private void loadDefault() {
        plugin.saveDefaultConfig();
    }
}
