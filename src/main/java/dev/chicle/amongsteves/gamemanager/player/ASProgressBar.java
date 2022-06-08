package dev.chicle.amongsteves.gamemanager.player;

import org.bukkit.ChatColor;

public class ASProgressBar {
    private final int progressBarLength = 40;
    private final float maxProgress = 100.0f;
    private float progress;

    public ASProgressBar() {
        this(0.0f);
    }
    public ASProgressBar(float progress) {
        this.progress = progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
    }

    public void addProgress(float progress) {
        this.progress = Math.min(this.progress + progress, maxProgress);
    }

    public float getProgress() {
        return progress;
    }

    public float getProgressPercentage() {
        return (progress / maxProgress) * 100.0f;
    }

    public String getProgressBar() {
        StringBuilder sb = new StringBuilder();
        sb.append(ChatColor.WHITE + "[" + ChatColor.RESET);
        for (int i = 0; i < progressBarLength; i++) {
            if(i < progressBarLength*(progress/maxProgress)) {
                sb.append(ChatColor.GREEN + "|");
            } else {
                sb.append(ChatColor.GRAY + "|");
            }
        }
        sb.append(ChatColor.WHITE + "]");
        return sb.toString();
    }
}
