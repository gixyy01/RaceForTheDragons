package fr.gixy.gui;

import fr.gixy.Main;
import fr.gixy.scenario.ScenarioGUI;
import fr.gixy.task.Start;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

import java.util.Arrays;
import java.util.Collections;

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
        super.setItem(10, scenario, onClick -> {

            new ScenarioGUI(main).open(onClick.getPlayer());


        });

        ItemStack end = new ItemStack(Material.ENDER_PORTAL_FRAME);
        ItemMeta endM = end.getItemMeta();
        endM.setDisplayName("§6Activation de l'end ");

        endM.setLore(Collections.singletonList(("§a" + main.getEndTimer() + " §aminutes ")));
        end.setItemMeta(endM);
        super.setItem(16, end, onClick -> {


            if (onClick.getEvent().getClick().isLeftClick()) {
                main.setEndTimer(main.getEndTimer() + 5);
            }

            if (onClick.getEvent().getClick().isRightClick()) {
                if (main.getEndTimer() != 5) {

                    main.setEndTimer(main.getEndTimer() - 5);
                }
            }
            super.updateItems();

        });

        ItemStack finalHeal = new ItemStack(Material.POTION, 1);
        Potion pot = new Potion(1);
        pot.setType(PotionType.REGEN);
        pot.setSplash(true);
        pot.apply(finalHeal);
        ItemMeta finalHealM = finalHeal.getItemMeta();
        finalHealM.setDisplayName("§dFinal Heal");
        finalHealM.setLore(Collections.singletonList("§a" + main.getFinalHeal() + " §aminutes "));
        finalHealM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        finalHeal.setItemMeta(finalHealM);
        super.setItem(31, finalHeal, onClick ->{

            if (onClick.getEvent().getClick().isLeftClick()) {
                main.setFinalHeal(main.getFinalHeal() + 5);
            }

            if (onClick.getEvent().getClick().isRightClick()) {
                if (main.getFinalHeal() != 5) {

                    main.setFinalHeal(main.getFinalHeal() - 5);
                }
            }
            super.updateItems();

        });

    }


}
