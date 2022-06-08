package dev.chicle.amongsteves.listener;

import dev.chicle.amongsteves.AmongSteves;
import dev.chicle.amongsteves.gamemanager.listener.GameStateChangeListener;
import dev.chicle.amongsteves.gamemanager.listener.PlayerClickListener;
import dev.chicle.amongsteves.gamemanager.listener.PlayerDisconnectListener;
import dev.chicle.amongsteves.gamemanager.listener.PlayerJoinListener;

public class SetupListener {

    public SetupListener(AmongSteves plugin){
        // PlayerJoinEvent GameManager
        plugin.getServer().getPluginManager().registerEvents(new PlayerJoinListener(), plugin);
        // PlayerDisconnectEvent GameManager
        plugin.getServer().getPluginManager().registerEvents(new PlayerDisconnectListener(), plugin);
        // PlayerClickEvent Gamemanager
        plugin.getServer().getPluginManager().registerEvents(new PlayerClickListener(), plugin);
        // GameStateChangeEvent
        plugin.getServer().getPluginManager().registerEvents(new GameStateChangeListener(), plugin);
    }

}
