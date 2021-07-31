package fr.gixy.listeners;

import fr.gixy.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerTeleportEvent;


public class PortalListener implements Listener {

    private final Main main;

    public PortalListener(Main main) {

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
        if(event.getCause().equals(PlayerTeleportEvent.TeleportCause.END_PORTAL)){
            if(!main.isEnd()){
                event.setCancelled(true);
                player.sendMessage(main.getPrefix()+"§cL'end est désactivé pour le moment !");
            }
        }

    }
}