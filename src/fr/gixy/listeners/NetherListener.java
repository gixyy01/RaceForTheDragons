package fr.gixy.listeners;

import fr.gixy.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerTeleportEvent;


public class NetherListener implements Listener {

    private final Main main;

    public NetherListener(Main main) {

        this.main = main;
    }

    @EventHandler
    public void onPlayerPortal(PlayerPortalEvent event) {

        Player player = event.getPlayer();
        if (event.getCause().equals(PlayerTeleportEvent.TeleportCause.NETHER_PORTAL)) {
            if (!main.isNether()) {
                event.setCancelled(true);
                player.sendMessage(main.getPrefix() + "§cLe nether est désactivé !");
            }
        }
    }
}