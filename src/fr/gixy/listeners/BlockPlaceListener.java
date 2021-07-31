package fr.gixy.listeners;

import fr.gixy.Main;
import fr.gixy.State;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlaceListener implements Listener {

    private final Main main;

    public BlockPlaceListener(Main main) {

        this.main = main;
    }

    @EventHandler
    public void onBlockPlaceEvent(BlockPlaceEvent event) {

        if (main.isState(State.WAITING) || main.isState(State.FINISH)) {
            event.setCancelled(true);
        }
    }
}