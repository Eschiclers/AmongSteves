package dev.chicle.amongsteves.gamemanager;

import dev.chicle.amongsteves.AmongSteves;
import dev.chicle.amongsteves.config.Locations;
import dev.chicle.amongsteves.event.PlayerChangeColorEvent;
import dev.chicle.amongsteves.gamemanager.player.ASPlayer;
import dev.chicle.amongsteves.event.GameStateChangeEvent;
import dev.chicle.amongsteves.gamemanager.player.PlayerColor;
import dev.chicle.amongsteves.gamemanager.player.PlayerRole;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import static org.bukkit.Bukkit.getLogger;

public class GameManager {
    private static Plugin plugin;
    static World world = Bukkit.getServer().getWorlds().get(0);

    @Getter
    private static GameState state;
    private static List<ASPlayer> players = new ArrayList<>();

    // Ajustes del juego
    @Getter
    private static int minPlayers;
    @Getter
    private static int maxImpostors;
    @Getter
    @Setter
    private static float actionCooldownTime = 45.0f;

    public GameManager(Plugin plugin) {
        this.plugin = plugin;
        state = GameState.IN_LOBBY;
        minPlayers = plugin.getConfig().getInt("minPlayers", 4);
        maxImpostors = plugin.getConfig().getInt("maxImpostors", 1);
    }

    /**
     * @param state El nuevo estado del juego
     */
    public static void setState(GameState state) {
        Bukkit.getPluginManager().callEvent(new GameStateChangeEvent(getState(), state));
        getLogger().info("GameStateChangeEvent: " + getState() + " -> " + state);
        GameManager.state = state;
    }

    public static void addPlayer(ASPlayer player) {
        players.add(player);
    }

    public static void removePlayer(ASPlayer player) {
        players.remove(player);
    }

    public static List<ASPlayer> getPlayers() {
        return players;
    }

    public static ASPlayer getPlayer(Player player) {
        for (ASPlayer p : players) {
            if (p.getPlayer().equals(player)) {
                return p;
            }
        }
        ASPlayer newPlayer = new ASPlayer(player, false);
        addPlayer(newPlayer);
        return newPlayer;
    }

    public static void startGame() {
        Collection<? extends Player> players = Bukkit.getOnlinePlayers();
        List<ASPlayer> impostors = new ArrayList<>();

        Random random = new Random();

        world.setDifficulty(Difficulty.PEACEFUL);

        while (impostors.size() < GameManager.getMaxImpostors()) {
            ASPlayer impostor = GameManager.getPlayers().get(random.nextInt(GameManager.getPlayers().size()));
            if (impostors.contains(impostor)) continue;
            impostors.add(impostor);
            impostor.setRole(PlayerRole.IMPOSTOR);
        }

        String impostorTitle = ChatColor.RED + PlayerRole.IMPOSTOR.name();
        String impostorSubtitle = "Acaba con todos los tripulantes";

        String crewmateTitle = ChatColor.AQUA + PlayerRole.CREWMATE.name();
        String crewmateSubtitle = "Hay " + ChatColor.RED + impostors.size() + (impostors.size() > 1 ? " impostores " : " impostor ") + ChatColor.GOLD + "entre nosotros";

        for (ASPlayer asPlayer : GameManager.getPlayers()) {
            Player player = asPlayer.getPlayer();

            player.setGameMode(GameMode.ADVENTURE);

            // Ocultar a los jugadores
            for (Player p2 : players) {
                player.hidePlayer(AmongSteves.getInstance(), p2);
            }

            // Si el jugador no está en la lista de impostores, asignarle el rol CREWMATE
            if (!impostors.contains(asPlayer)) asPlayer.setRole(PlayerRole.CREWMATE);

            // Llevar a la zona oscura y cegar
            player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 1, false, false));
            player.teleport(Locations.getBlack());

            // Vaciar el inventario
            player.getInventory().clear();

            // Sonido al empezar el juego
            //player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);

            switch (asPlayer.getRole()) {
                case CREWMATE:
                    player.sendTitle(crewmateTitle, crewmateSubtitle, 20, 70, 10);
                    player.sendMessage(AmongSteves.chatPrefix + "Eres " + ChatColor.AQUA + "tripulante" + ChatColor.WHITE + ".");
                    player.sendMessage(AmongSteves.chatPrefix + ChatColor.GOLD + crewmateSubtitle);
                    break;
                case IMPOSTOR:
                    player.sendTitle(impostorTitle, impostorSubtitle, 20, 70, 10);
                    player.sendMessage(AmongSteves.chatPrefix + "Eres " + ChatColor.RED + "impostor" + ChatColor.WHITE + ".");
                    player.sendMessage(AmongSteves.chatPrefix + ChatColor.GOLD + impostorSubtitle);
                    break;
            }
        }

        Bukkit.getScheduler().runTaskLater(plugin, () -> {

            for(Player p : players){
                ASPlayer asP = getPlayer(p);

                players.forEach(p2 -> {
                    p.showPlayer(AmongSteves.getInstance(), p2);
                });

                p.teleport(Locations.getStart());

                asP.resetActionCooldown();
                asP.setShowActionBar(asP.getRole() == PlayerRole.IMPOSTOR);

                asP.reset(true);
            }

            GameManager.setState(GameState.IN_GAME);
        }, 70L);
    }

    public static void checkWinner() {
        int crewmates = 0;
        int impostors = 0;

        for (ASPlayer asPlayer : GameManager.getPlayers()) {
            if (asPlayer.getRole() == PlayerRole.IMPOSTOR && !asPlayer.isDead()) impostors++;
            else if (asPlayer.getRole() != PlayerRole.IMPOSTOR && !asPlayer.isDead()) crewmates++;
        }

        if (crewmates == 0) {
            GameManager.setState(GameState.IN_LOBBY);
            for (ASPlayer asPlayer : GameManager.getPlayers()) {
                asPlayer.getPlayer().sendMessage(AmongSteves.chatPrefix + ChatColor.RED + "No hay mas tripulantes!");
                asPlayer.getPlayer().sendMessage(AmongSteves.chatPrefix + ChatColor.RED + "Los impostores ganan!");
                asPlayer.getPlayer().sendTitle(ChatColor.RED + "Los impostores ganan!", null, 20, 70, 10);
            }
        } else if (impostors == 0) {
            GameManager.setState(GameState.IN_LOBBY);
            for (ASPlayer asPlayer : GameManager.getPlayers()) {
                asPlayer.getPlayer().sendMessage(AmongSteves.chatPrefix + ChatColor.GREEN + "Todos los impostores han sido eliminados!");
                asPlayer.getPlayer().sendMessage(AmongSteves.chatPrefix + ChatColor.GREEN + "Los tripulantes ganan!");
                asPlayer.getPlayer().sendTitle(ChatColor.GREEN + "Los tripulantes ganan!", null, 20, 70, 10);
            }
        }

    }

    public static void changePlayerColor(ASPlayer asPlayer, PlayerColor oldColor, PlayerColor newColor) {
        Player player = asPlayer.getPlayer();

        if (getState() != GameState.IN_LOBBY) {
            player.sendMessage(AmongSteves.chatPrefix + ChatColor.RED + "No puedes cambiar de color mientras estas en una partida.");
            return;
        }

        if (oldColor == newColor) {
            player.sendMessage(AmongSteves.chatPrefix + ChatColor.RED + "Ya eres ese color.");
            return;
        }

        // Check if any player has the same color
        if (!isColorAvailable(newColor)) {
            player.sendMessage(AmongSteves.chatPrefix + ChatColor.RED + "Ya hay un jugador con ese color.");
            return;
        }

        if (createAndEquipColoredArmor(player, newColor)) {
            player.sendMessage(AmongSteves.chatPrefix + ChatColor.GREEN + "Color cambiado correctamente.");
            asPlayer.setColor(newColor);
            Bukkit.getPluginManager().callEvent(new PlayerChangeColorEvent(player, oldColor, newColor));
        } else {
            player.sendMessage(AmongSteves.chatPrefix + ChatColor.RED + "No se ha podido cambiar el color.");
        }
    }

    public static boolean isColorAvailable(PlayerColor color) {
        for (ASPlayer p : getPlayers()) {
            if (p.getColor() == color) {
                return false;
            }
        }
        return true;
    }

    public static boolean createAndEquipColoredArmor(Player player, PlayerColor color) {

        try {
            ItemStack[] armor = new ItemStack[4];

            armor[3] = new ItemStack(Material.LEATHER_HELMET);
            armor[2] = new ItemStack(Material.LEATHER_CHESTPLATE);
            armor[1] = new ItemStack(Material.LEATHER_LEGGINGS);
            armor[0] = new ItemStack(Material.LEATHER_BOOTS);

            LeatherArmorMeta meta = (LeatherArmorMeta) armor[0].getItemMeta();
            meta.setColor(color.getColor());
            meta.setDisplayName(color.name());
            armor[3].setItemMeta(meta);

            meta = (LeatherArmorMeta) armor[1].getItemMeta();
            meta.setColor(color.getColor());
            meta.setDisplayName(color.name());
            armor[2].setItemMeta(meta);

            meta = (LeatherArmorMeta) armor[2].getItemMeta();
            meta.setColor(color.getColor());
            meta.setDisplayName(color.name());
            armor[1].setItemMeta(meta);

            meta = (LeatherArmorMeta) armor[3].getItemMeta();
            meta.setColor(color.getColor());
            meta.setDisplayName(color.name());
            armor[0].setItemMeta(meta);

            // Equipo armor
            player.getInventory().setArmorContents(armor);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}