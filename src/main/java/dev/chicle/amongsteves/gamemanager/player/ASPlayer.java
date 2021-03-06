package dev.chicle.amongsteves.gamemanager.player;

import dev.chicle.amongsteves.AmongSteves;
import dev.chicle.amongsteves.config.Locations;
import dev.chicle.amongsteves.gamemanager.GameManager;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Scoreboard;

public class ASPlayer {
    private final Player player;
    private PlayerRole role;
    private PlayerColor color;
    private boolean actionCooldown;
    private ASProgressBar actionBar;

    private Runnable actionThread = () -> handleActionThread();

    private int scheduledTask;

    private int cooldownTask;
    private float cooldownTime;

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
        this.actionCooldown = true;
        this.actionBar = new ASProgressBar(0.0f, 20);
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

    private void handleActionThread() {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                actionCooldown ?
                        TextComponent.fromLegacyText("Ataque disponible en (" + (int)cooldownTime + ChatColor.GRAY + "s" + ChatColor.WHITE + ") " + actionBar.getProgressBar() + " " + (int)actionBar.getProgressPercentage() + "%") :
                        TextComponent.fromLegacyText(ChatColor.GREEN + "Ataque disponible"));
    }

    public void resetActionCooldown() {
        this.setActionCooldown(true);
        float cooldownMax = GameManager.getActionCooldownTime();
        this.cooldownTime = GameManager.getActionCooldownTime();

        cooldownTask = Bukkit.getScheduler().scheduleSyncRepeatingTask(AmongSteves.getInstance(), () -> {
            if (cooldownTime > 0) {
                cooldownTime--;
                float progress = (cooldownMax - cooldownTime) * 100 / cooldownMax;
                actionBar.setProgress(progress);
            } else {
                Bukkit.getScheduler().cancelTask(cooldownTask);
                this.setActionCooldown(false);
            }
        }, 0L, 20L);
    }

    private void updatePlayerDisplayName() {
        String newDisplayName = ChatColor.RESET + "[" + this.color.getChatColor() + this.color.name() + ChatColor.RESET + "] " + this.player.getName();
        this.player.setDisplayName(newDisplayName);
        this.player.setPlayerListName(newDisplayName);
    }

    public void reset(){
        reset(true);
    }

    public void reset(boolean equip) {
        player.setHealth(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
        player.setFoodLevel(20);
        player.setSaturation(20);
        player.getInventory().clear();
        player.setFireTicks(0);
        player.setExp(0);
        player.setLevel(0);
        player.setTotalExperience(0);
        player.setGameMode(GameMode.ADVENTURE);
        player.setFlying(false);
        player.setAllowFlight(false);
        player.getActivePotionEffects().forEach(effect -> player.removePotionEffect(effect.getType()));

        if(equip)
            GameManager.createAndEquipColoredArmor(player, getColor());
    }

    public void stopScheduledTask() {
        Bukkit.getScheduler().cancelTask(scheduledTask);
    }
}
