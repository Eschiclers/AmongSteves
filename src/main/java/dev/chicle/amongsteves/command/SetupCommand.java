package dev.chicle.amongsteves.command;

import dev.chicle.amongsteves.AmongSteves;

public class SetupCommand {
    public SetupCommand(AmongSteves plugin){
        plugin.getCommand("change").setExecutor(new ChangeStateCommand());
        plugin.getCommand("startgame").setExecutor(new StartGameCommand());
        plugin.getCommand("endgame").setExecutor(new EndGameCommand());
    }
}
