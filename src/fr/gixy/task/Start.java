package fr.gixy.task;

import fr.gixy.Damage;
import fr.gixy.Main;
import fr.gixy.State;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class Start {


    private final Main main;

    public Start(Main main) {
        this.main = main;
    }

    public void start() {


        if (!main.isCanStart()) {

            Bukkit.broadcastMessage("§cStart annulé, vous devez choisir un emplacement pour le temple avec la commande /temple");
            return;

        }

        main.setState(State.STARTING);

        for (Player players : Bukkit.getOnlinePlayers()) {
            new BukkitRunnable() {

                int timer = 11;
                int max = 50;
                int x = new Random().nextInt(max);
                int z = new Random().nextInt(max);
                Location loc = new Location(players.getWorld(), x, 130, z);
                int y = loc.getWorld().getHighestBlockYAt(loc) + 3;

                @Override
                public void run() {

                    timer--;
                    if (timer == 10 || timer == 5 || timer == 4 || timer == 3 || timer == 2 || timer == 1) {
                        players.sendMessage(main.getPrefix() + "§aDébut de la partie dans " + ChatColor.YELLOW + timer);
                        players.playSound(players.getLocation(), Sound.NOTE_PLING, 1f, 3f);
                    }

                    players.setLevel(timer);
                    if (timer == 0) {

                        main.setState(State.PLAYING);
                        players.teleport(loc);
                        players.sendTitle("§aBonne chance !", "");
                        main.setDamage(Damage.FALSE);
                        players.getInventory().clear();
                        players.setHealth(20);
                        players.setFoodLevel(20);
                        players.sendMessage(main.getPrefix() + "§aL'invulnérabilité prendra fin dans 30 secondes !");
                        main.setState(State.PLAYING);
                        Bukkit.broadcastMessage(main.getPrefix()+"§7Jour 1");
                        for(Player players : Bukkit.getOnlinePlayers()){
                            players.playSound(players.getLocation(), Sound.NOTE_PLING, 1f,1f);
                        }

                        GameLoop loop = new GameLoop(main);
                        loop.runTaskTimer(main, 0, 20);

                        cancel();
                    }
                }
            }.runTaskTimer(main, 0, 20);


        }
    }
}

