package fr.gixy.listeners;

import fr.gixy.Main;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.yaml.snakeyaml.Yaml;

import java.util.Optional;
import java.util.Timer;
import java.util.UUID;
import java.util.zip.ZipEntry;

public class ChatListener implements Listener {

    private Main main;

    public ChatListener(Main main) {

        this.main = main;
    }

    @EventHandler
    public void onChatListerner(AsyncPlayerChatEvent event) {


        Player player = event.getPlayer();


        if (!main.isAlive(player)) {
            event.setCancelled(true);
            for (UUID playerId : main.getSpectators()) {

                Player players = Bukkit.getPlayer(playerId);
                if (players != null) {
                    players.sendMessage("§7[SPEC]" + ChatColor.GRAY + player.getName() + "§7: " + event.getMessage());
                }
            }

        }else{
            event.setFormat(ChatColor.GRAY+player.getName()+"» "+event.getMessage());
        }
    }
}