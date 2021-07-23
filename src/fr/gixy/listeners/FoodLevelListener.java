package fr.gixy.listeners;

import fr.gixy.Main;
import fr.gixy.State;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class FoodLevelListener implements Listener {

    private final Main main;

    public FoodLevelListener(Main main) {

        this.main = main;

    }

    @EventHandler
    public void onFoodLevel(FoodLevelChangeEvent event) {

        if (event instanceof Player) {
            if (main.isState(State.WAITING) || main.isState(State.FINISH)) {
                event.setCancelled(true);
            }
        }
    }
}
