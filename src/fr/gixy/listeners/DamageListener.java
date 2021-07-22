package fr.gixy.listeners;

import fr.gixy.Damage;
import fr.gixy.Main;
import fr.gixy.State;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamageListener implements Listener {

    private Main main;

    public DamageListener(Main main) {

        this.main = main;
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event){

        Player player =(Player) event.getEntity();

        if(main.isState(State.WAITING) || main.isState(State.STARTING)) event.setCancelled(true);
        if(main.isDamage(Damage.FALSE)){
            event.setCancelled(true);
        }else{
            event.setCancelled(false);
        }

    }


}
