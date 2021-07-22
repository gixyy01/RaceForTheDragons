package fr.gixy.listeners;

import fr.gixy.Main;
import fr.gixy.State;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class CheckWin implements Listener {

    private final Main main;


    public CheckWin(Main main) {

        this.main = main;
    }

    @EventHandler
    public void onBlockPlacedEvent(BlockPlaceEvent event) {

        Player player = event.getPlayer();
        Block block = event.getBlock();

        if (main.isState(State.PLAYING)) {
            if (block.getType().equals(Material.DRAGON_EGG)) {
                if (block.getLocation().getBlockX() == main.getX() && block.getLocation().getBlockY() == main.getY() && block.getLocation().getBlockZ() == main.getZ()) {

                    main.setState(State.FINISH);
                            for (Player players : Bukkit.getOnlinePlayers()) {

                                players.sendMessage(main.getPrefix() + ChatColor.YELLOW + player.getName() + " §aremporte la partie !");
                                players.teleport(player.getLocation());
                                players.setAllowFlight(true);
                            }
                        }

                    }
                }
            }
        }