package dev.chicle.amongsteves.listener;

import dev.chicle.amongsteves.AmongSteves;
import dev.chicle.amongsteves.gamemanager.listener.GameStateChangeListener;
import dev.chicle.amongsteves.gamemanager.listener.PlayerJoinListener;

public class SetupListener {

    public SetupListener(AmongSteves plugin){
        // PlayerJoinEvent GameManager
        plugin.getServer().getPluginManager().registerEvents(new PlayerJoinListener(), plugin);
        // GameStateChangeEvent
        plugin.getServer().getPluginManager().registerEvents(new GameStateChangeListener(), plugin);
    }

}
