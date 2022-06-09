package dev.chicle.amongsteves.gamemanager.player;

import dev.chicle.amongsteves.AmongSteves;
import dev.chicle.amongsteves.config.Locations;
import dev.chicle.amongsteves.gamemanager.GameManager;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;

public class ASPlayer {
    private final Player player;
    private PlayerRole role;
    private PlayerColor color;
    private boolean actionCooldown;
    private ASProgressBar actionBar;

    private Runnable actionThread = new Runnable() {
        @Override
        public void run() {
            handleActionThread();
        }
    };

    private int scheduledTask;

    private boolean isDead;
    private boolean showActionBar;

    public ASPlayer(Player player, boolean isDead) {
        this.player = player;
        this.isDead = isDead;
        this.role = PlayerRole.NONE;
        while (true) {
            this.color = PlayerColor.values()[(int) (Math.random() * PlayerColor.values().length)];
            if (GameManager.isColorAvailable(this.color)) {
                break;
            }
        }
        this.actionCooldown = false;
        this.actionBar = new ASProgressBar(100.0f);
        this.showActionBar = false;
        updatePlayerDisplayName();
        switch (GameManager.getState()) {
            case IN_LOBBY -> player.teleport(Locations.getLobby());
        }
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
        updatePlayerDisplayName();
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

    public boolean getShowActionBar() {
        return this.showActionBar;
    }

    public void setShowActionBar(boolean show) {
        this.showActionBar = show;
        if (this.showActionBar) {
            scheduledTask = Bukkit.getScheduler().scheduleSyncRepeatingTask(AmongSteves.getInstance(), actionThread, 0L, 10L);
        } else {
            Bukkit.getScheduler().cancelTask(scheduledTask);
        }
    }

    private void handleActionThread(){
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                getActionBar().getProgress() == 100.0f ?
                        TextComponent.fromLegacyText(ChatColor.GREEN + "Ya puedes asesinar a un jugador") :
                        TextComponent.fromLegacyText("Ataque en cooldown " + actionBar.getProgressBar() + " " + actionBar.getProgressPercentage() + "%"));
    }

    public void resetActionCooldown(){
        this.setActionCooldown(true);
        this.getActionBar().setProgress(0.0f);
    }

    private void updatePlayerDisplayName() {
        String newDisplayName = ChatColor.RESET + "[" + this.color.getChatColor() + this.color.name() + ChatColor.RESET + "] " + this.player.getName();
        this.player.setDisplayName(newDisplayName);
        this.player.setPlayerListName(newDisplayName);
    }
}
