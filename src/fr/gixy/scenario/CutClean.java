package fr.gixy.scenario;

import fr.gixy.Main;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.inventory.ItemStack;

public class CutClean implements Listener {

    private Main main;

    public CutClean(Main main) {

        this.main = main;
    }

    @EventHandler
    public void onspawn(ItemSpawnEvent event) {
        ItemStack item = event.getEntity().getItemStack();

        if (main.isCutClean() == false) {
            return;
        } else {
            switch (item.getType()) {
                case GOLD_ORE:
                    item.setType(Material.GOLD_INGOT);
                    break;
                case IRON_ORE:
                    item.setType(Material.IRON_INGOT);
                    break;
                case RAW_BEEF:
                    item.setType(Material.COOKED_BEEF);
                    break;
                case PORK:
                    item.setType(Material.GRILLED_PORK);
                    break;
                case RAW_CHICKEN:
                    item.setType(Material.COOKED_CHICKEN);
                    break;
                case MUTTON:
                    item.setType(Material.COOKED_MUTTON);
                    break;
                case RABBIT:
                    item.setType(Material.COOKED_RABBIT);
                    break;
                case POTATO:
                    item.setType(Material.BAKED_POTATO);
                    break;
                case RAW_FISH:
                    item.setType(Material.COOKED_FISH);
                    break;
                default:
                    break;
            }
        }

    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        if (main.isCutClean() == false) {
            return;

        } else {

            if (block.getType() == Material.GOLD_ORE) {
                event.setExpToDrop(1);
            } else if (block.getType() == Material.IRON_ORE) {
                event.setExpToDrop(2);
            }
        }
    }
}