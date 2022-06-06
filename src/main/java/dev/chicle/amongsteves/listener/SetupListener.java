package dev.chicle.amongsteves.listener;

import dev.chicle.amongsteves.AmongSteves;
import dev.chicle.amongsteves.gamemanager.listener.PlayerJoinListener;
import dev.chicle.amongsteves.gamestate.listener.GameStateChangeListener;

public class SetupListener {

    public SetupListener(AmongSteves plugin){
        // PlayerJoinEvent GameManager
        plugin.getServer().getPluginManager().registerEvents(new PlayerJoinListener(plugin), plugin);
        // GameStateChangeEvent
        plugin.getServer().getPluginManager().registerEvents(new GameStateChangeListener(), plugin);
    }

}
