package dev.chicle.amongsteves;

import dev.chicle.amongsteves.commands.ChangeStateCommand;
import dev.chicle.amongsteves.gamestate.listener.GameStateChangeListener;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public final class AmongSteves extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new GameStateChangeListener(), this);
        getCommand("change").setExecutor(new ChangeStateCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
