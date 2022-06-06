package dev.chicle.amongsteves.command;

import dev.chicle.amongsteves.AmongSteves;

public class SetupCommand {
    public SetupCommand(AmongSteves plugin){
        plugin.getCommand("change").setExecutor(new ChangeStateCommand());
    }
}
