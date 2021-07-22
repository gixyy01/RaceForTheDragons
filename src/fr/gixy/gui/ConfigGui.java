package fr.gixy.gui;

import fr.gixy.Main;
import fr.gixy.task.Start;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ConfigGui extends GUI {

    private final Main main;

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
    }
}
