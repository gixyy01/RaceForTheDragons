package fr.gixy.scenario;

import fr.gixy.Main;
import fr.gixy.gui.GUI;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class ScenarioGUI extends GUI {

    private Main main;

    public ScenarioGUI(Main main) {
        super("§6Scénario", 6);
        this.main = main;
    }

    @Override
    public void drawItems() {

        ItemStack cutClean = new ItemStack(Material.IRON_INGOT);
        ItemMeta cutCleanM = cutClean.getItemMeta();
        cutCleanM.setDisplayName("§6CutClean");
        cutCleanM.setLore(Arrays.asList("Statut : " + (main.isCutClean() ? "§aactivé" : "§cdésactivé")));
        cutClean.setItemMeta(cutCleanM);
        super.setItem(0, cutClean, onClick -> {

            if (main.isCutClean() == true) {
                main.setCutClean(false);
                onClick.getPlayer().sendMessage("§cCut clean désactivé !");
            } else {
                main.setCutClean(true);
                onClick.getPlayer().sendMessage("§aCut clean activé !");


            }
            super.updateItems();


        });


    }
}
