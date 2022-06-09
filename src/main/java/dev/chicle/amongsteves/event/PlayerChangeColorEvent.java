package dev.chicle.amongsteves.event;

import dev.chicle.amongsteves.gamemanager.GameState;
import dev.chicle.amongsteves.gamemanager.player.PlayerColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerChangeColorEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();

    private static PlayerColor oldColor;
    private static PlayerColor newColor;
    private static Player player;

    public static HandlerList getHandlerList(){
        return HANDLERS;
    }

    public PlayerChangeColorEvent(Player player, PlayerColor oldColor, PlayerColor newColor){
        this.oldColor = oldColor;
        this.newColor = newColor;
        this.player = player;
    }

    public PlayerColor getOldColor() {
        return oldColor;
    }

    public PlayerColor getNewColor() {
        return newColor;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public HandlerList getHandlers(){
        return HANDLERS;
    }
}
