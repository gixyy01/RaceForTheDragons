package fr.gixy.listeners;

import fr.gixy.Main;
import fr.gixy.State;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener {

    private Main main;

    public PlayerQuit(Main main) {

        this.main = main;
    }


    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){

        Player player = event.getPlayer();
        event.setQuitMessage(main.getPrefix()+player.getName()+" §ea quitté le serveur");
        main.getScoreboard().onQuit(player);

        if(main.isState(State.WAITING)){
            main.getPlayers().remove(player.getUniqueId());
        }
    }
}