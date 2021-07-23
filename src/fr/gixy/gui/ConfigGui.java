package fr.gixy.gui;

import fr.gixy.Main;
import fr.gixy.task.Start;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ConfigGui extends GUI {

    private final Main main;
    private String netherState;

    public ConfigGui(Main main) {
        super("Configuration", 6);
        this.main = main;
    }

    @Override
    public void drawItems() {

        ItemStack start = new ItemStack(Material.SLIME_BALL);
        ItemMeta startM = start.getItemMeta();
        startM.setDisplayName("§6Start");
        start.setItemMeta(startM);
        super.setItem(49, start, onClick -> {

            Start gameStart = new Start(main);

            gameStart.start();

            super.close(onClick.getPlayer());

        });

        ItemStack nether = new ItemStack(Material.NETHERRACK);
        ItemMeta netherM = nether.getItemMeta();
        netherM.setDisplayName("§6Nether activé");
        nether.setItemMeta(netherM);
        super.setItem(13, nether, onClick -> {

            if (main.isNether() == true) {
                boolean allowNether = Bukkit.getAllowNether();
                allowNether = false;
                main.setNether(false);
                nether.getItemMeta().setDisplayName("§6Nether désactivé");
                onClick.getPlayer().sendMessage("§cNether désactivé !");
                netherState = "§6désactivé";

            }else{
                boolean allowNether = Bukkit.getAllowNether();
                main.setNether(true);
                allowNether= true;
                nether.getItemMeta().setDisplayName("§6Nether activé");

                onClick.getPlayer().sendMessage("§cNether activé !");
                netherState = "§6activé";

            }


        });
    }


}
