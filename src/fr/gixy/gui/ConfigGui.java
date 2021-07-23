package fr.gixy.gui;

import fr.gixy.Main;
import fr.gixy.scenario.ScenarioGUI;
import fr.gixy.task.Start;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class ConfigGui extends GUI {

    private final Main main;

    public ConfigGui(Main main) {
        super("Configuration", 6);
        super.setUpdate(1);
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
        netherM.setDisplayName("§6Nether");
        netherM.setLore(Arrays.asList("Statut : " + (main.isNether() ? "§aactivé" : "§cdésactivé")));
        nether.setItemMeta(netherM);
        super.setItem(13, nether, onClick -> {

            if (main.isNether()) {
                main.setNether(false);
                onClick.getPlayer().sendMessage("§cNether désactivé !");


            } else {
                main.setNether(true);
                onClick.getPlayer().sendMessage("§aNether activé !");
            }
            super.updateItems();


        });

        ItemStack scenario = new ItemStack(Material.ENCHANTED_BOOK);
        ItemMeta scenarioM = scenario.getItemMeta();
        scenarioM.setDisplayName("§6 Scénario");
        scenario.setItemMeta(scenarioM);
        super.setItem(10, scenario, onClick ->{

            new ScenarioGUI(main).open(onClick.getPlayer());


        });

    }


}
