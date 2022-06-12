package dev.chicle.amongsteves.config;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;

public class Locations {
    static Plugin plugin;
    static World world = Bukkit.getServer().getWorlds().get(0);

    public Locations(Plugin p) {
        plugin = p;
    }

    public static Location getBlack() {
        return new Location(world,
                plugin.getConfig().getDouble("locations.black.coords.x"),
                plugin.getConfig().getDouble("locations.black.coords.y"),
                plugin.getConfig().getDouble("locations.black.coords.z"),
                (float) plugin.getConfig().getDouble("locations.black.coords.yaw"),
                (float) plugin.getConfig().getDouble("locations.black.coords.pitch"));
    }

    public static Location getLobby() {
        return new Location(world,
                plugin.getConfig().getDouble("locations.lobby.coords.x"),
                plugin.getConfig().getDouble("locations.lobby.coords.y"),
                plugin.getConfig().getDouble("locations.lobby.coords.z"),
                (float) plugin.getConfig().getDouble("locations.lobby.coords.yaw"),
                (float) plugin.getConfig().getDouble("locations.lobby.coords.pitch"));
    }

    public static Location getStart() {
        return new Location(world,
                plugin.getConfig().getDouble("locations.start.coords.x"),
                plugin.getConfig().getDouble("locations.start.coords.y"),
                plugin.getConfig().getDouble("locations.start.coords.z"),
                (float) plugin.getConfig().getDouble("locations.start.coords.yaw"),
                (float) plugin.getConfig().getDouble("locations.start.coords.pitch"));
    }

}
