package dev.chicle.amongsteves.listener;

import dev.chicle.amongsteves.AmongSteves;
import dev.chicle.amongsteves.gamemanager.listener.*;

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
        // PlayerChangeColorEvent
        plugin.getServer().getPluginManager().registerEvents(new PlayerChangeColorListener(), plugin);
        // PlayerDropItemEvent
        plugin.getServer().getPluginManager().registerEvents(new PlayerDropItemListener(), plugin);
    }

}
