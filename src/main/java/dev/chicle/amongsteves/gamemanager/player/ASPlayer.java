package dev.chicle.amongsteves.gamemanager.player;

import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;

public class ASPlayer {
    private final Player player;
    private PlayerRole role;
    private PlayerColor color;
    private boolean actionCooldown;
    private ASProgressBar actionBar;
    private boolean isDead;

    public ASPlayer(Player player, boolean isDead) {
        this.player = player;
        this.isDead = isDead;
        this.role = PlayerRole.NONE;
        // Color aleatorio sacado de los enums
        this.color = PlayerColor.values()[(int) (Math.random() * PlayerColor.values().length)];
        this.actionCooldown = false;
        this.actionBar = new ASProgressBar(100.0f);
    }

    public Player getPlayer() {
        return player;
    }

    public PlayerRole getRole() {
        return role;
    }

    public PlayerColor getColor() {
        return color;
    }

    public void setColor(PlayerColor color) {
        this.color = color;
    }

    public void setRole(PlayerRole role) {
        this.role = role;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean isDead) {
        this.isDead = isDead;
    }

    public void setScoreboard(Scoreboard scoreboard) {
        player.setScoreboard(scoreboard);
    }

    public boolean isActionCooldown() {
        return actionCooldown;
    }

    public void setActionCooldown(boolean actionCooldown) {
        this.actionCooldown = actionCooldown;
    }

    public ASProgressBar getActionBar() {
        return actionBar;
    }

    public void setActionBar(ASProgressBar actionBar) {
        this.actionBar = actionBar;
    }
}
