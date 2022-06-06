package dev.chicle.amongsteves.listener;

import dev.chicle.amongsteves.AmongSteves;
import dev.chicle.amongsteves.gamestate.listener.GameStateChangeListener;

public class SetupListener {

    public SetupListener(AmongSteves plugin){
        // PlayerJoinEvent
        plugin.getServer().getPluginManager().registerEvents(plugin, plugin);
        // GameStateChangeEvent
        plugin.getServer().getPluginManager().registerEvents(new GameStateChangeListener(), plugin);
    }

}
