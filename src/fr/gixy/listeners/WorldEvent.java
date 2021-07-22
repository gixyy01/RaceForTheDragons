package fr.gixy.listeners;

import fr.gixy.Main;
import fr.gixy.State;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

import javax.swing.text.html.parser.Entity;

public class WorldEvent implements Listener {

    private Main main;


    public WorldEvent(Main main) {

        this.main = main;
    }


    @EventHandler
    public void onWeatherState(WeatherChangeEvent event) {

        event.setCancelled(true);
    }

    @EventHandler
    public void onMobSpawning(EntitySpawnEvent event) {


        if (main.isState(State.WAITING) || main.isState(State.FINISH)) {
            event.setCancelled(true);
        }

    }

}
