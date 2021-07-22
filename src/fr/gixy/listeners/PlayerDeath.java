package fr.gixy.listeners;

import fr.gixy.Main;
import fr.gixy.State;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeath implements Listener {

    private Main main;

    public PlayerDeath(Main main) {

        this.main = main;


    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {

        Player player = event.getEntity();


        player.getLocation();
        main.eliminate(player);
        main.checkNoWin();
        main.setAlive(false, player);
        main.getSpectators().add(player.getUniqueId());



    }


}