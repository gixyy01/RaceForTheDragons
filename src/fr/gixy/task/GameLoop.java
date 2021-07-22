package fr.gixy.task;

import fr.gixy.Damage;
import fr.gixy.Main;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class GameLoop extends BukkitRunnable {

    private Main main;
    private static int timer = 0;

    public GameLoop(Main main) {
        this.main = main;
    }


    @Override
    public void run() {

        // if(timer == 4){

        //   main.checkwin();
        // cancel();
        //}


        if (timer == 30) {
            for (Player players : Bukkit.getOnlinePlayers()) {

                players.sendMessage(main.getPrefix() + "§aVous pouvez désormais prendre des dégâts" + "§c(sauf PVP)");
            }
            main.setDamage(Damage.TRUE);

        }

        if (timer == 1200) {
            for (Player players : Bukkit.getOnlinePlayers()) {

                players.sendMessage(main.getPrefix() + "§c PVP ACTIF !");
            }

            for (World world : Bukkit.getWorlds()) {

                world.setPVP(true);


            }
        }








        timer++;
    }

    public static int getTimer() {

        return timer;
    }
}