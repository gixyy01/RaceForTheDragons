package fr.gixy.listeners;

import fr.gixy.Main;
import fr.gixy.State;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerJoin implements Listener {

    private final Main main;

    public PlayerJoin(Main main) {

        this.main = main;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();
        event.setJoinMessage(main.getPrefix() + player.getName() + " §ea rejoint le serveur");
        main.getScoreboard().onJoin(player);
        player.setStatistic(Statistic.PLAYER_KILLS, 0);

        //Check if the game is already running

        if (!main.isState(State.WAITING)) {
            if (!main.getPlayers().contains(player.getUniqueId())) {
                player.setGameMode(GameMode.SPECTATOR);
                player.sendMessage("§aLa partie a déjà commencé !");
                main.getSpectators().add(player.getUniqueId());
            }
        }
        //Check if the player is already in the playerList

        if (!main.getPlayers().contains(player.getUniqueId()) && main.isState(State.WAITING)) {

            main.getPlayers().add(player.getUniqueId());
            player.setGameMode(GameMode.ADVENTURE);
            player.setHealth(20);
            player.setFoodLevel(20);
            main.spawn(player);
            player.getInventory().clear();
            main.setAlive(true, player);
            if (player.isOp()) {
                ItemStack config = new ItemStack(Material.REDSTONE_COMPARATOR);
                ItemMeta configM = config.getItemMeta();
                configM.setDisplayName("§6Configuration");
                config.setItemMeta(configM);
                player.getInventory().setItem(4, config);
            }
        }
    }
}