package fr.gixy.listeners;

import fr.gixy.Main;
import fr.gixy.gui.ConfigGui;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ConfigInteractGUI implements Listener {

    private final Main main;

    public ConfigInteractGUI(Main main) {

        this.main = main;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {

        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        if (item == null) {
            return;
        }
        if (item.getType() == Material.REDSTONE_COMPARATOR) {
            if (item.getItemMeta().getDisplayName().equals("§6Configuration")) {
                new ConfigGui(main).open(player);
            }
        }
    }
}