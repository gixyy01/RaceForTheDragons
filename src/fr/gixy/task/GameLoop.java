package fr.gixy.task;

import fr.gixy.Damage;
import fr.gixy.Main;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class GameLoop extends BukkitRunnable {

    private final Main main;
    private static int timer = 0;

    public GameLoop(Main main) {
        this.main = main;
    }


    @Override
    public void run() {

        timer++;

        if (timer == 30) {
            for (Player players : Bukkit.getOnlinePlayers()) {

                players.sendMessage(main.getPrefix() + "§aVous pouvez désormais prendre des dégâts" + "§c(sauf PVP)");
            }
            main.setDamage(Damage.TRUE);

        }
        if (timer == 20*60) {
            Bukkit.broadcastMessage(main.getPrefix() + "§7Jour 2");
            for(Player players : Bukkit.getOnlinePlayers()){
                players.playSound(players.getLocation(), Sound.NOTE_PLING, 1f,1f);
            }

        } else if (timer == 40*60) {
            Bukkit.broadcastMessage(main.getPrefix() + "§7Jour 3");
            for(Player players : Bukkit.getOnlinePlayers()){
                players.playSound(players.getLocation(), Sound.NOTE_PLING, 1f,1f);
            }


        }else if(timer == 60*60){
            Bukkit.broadcastMessage(main.getPrefix()+"§7Jour 4");
            for(Player players : Bukkit.getOnlinePlayers()){
                players.playSound(players.getLocation(), Sound.NOTE_PLING, 1f,1f);
            }
        }else if(timer == 80*60){
            Bukkit.broadcastMessage(main.getPrefix()+"§7Jour 5");
            for(Player players : Bukkit.getOnlinePlayers()){
                players.playSound(players.getLocation(), Sound.NOTE_PLING, 1f,1f);
            }
        }else if(timer == 100*60){
            Bukkit.broadcastMessage(main.getPrefix()+"§7Jour 6");
            for(Player players : Bukkit.getOnlinePlayers()){
                players.playSound(players.getLocation(), Sound.NOTE_PLING, 1f,1f);
            }
        }else if(timer == 120*60){
            Bukkit.broadcastMessage(main.getPrefix()+"§7Jour 7");
            for(Player players : Bukkit.getOnlinePlayers()){
                players.playSound(players.getLocation(), Sound.NOTE_PLING, 1f,1f);
            }
        }else if(timer == 140*60){
            Bukkit.broadcastMessage(main.getPrefix()+"§7Jour 8");
            for(Player players : Bukkit.getOnlinePlayers()){
                players.playSound(players.getLocation(), Sound.NOTE_PLING, 1f,1f);
            }
        }else if(timer == 160*60){
            Bukkit.broadcastMessage(main.getPrefix()+"§7Jour 9");
            for(Player players : Bukkit.getOnlinePlayers()){
                players.playSound(players.getLocation(), Sound.NOTE_PLING, 1f,1f);
            }
        }else if(timer == 180*60){
            Bukkit.broadcastMessage(main.getPrefix()+"§7Jour 10");
            for(Player players : Bukkit.getOnlinePlayers()){
                players.playSound(players.getLocation(), Sound.NOTE_PLING, 1f,1f);
            }
        }else if(timer == 200*60){
            Bukkit.broadcastMessage(main.getPrefix()+"§7Jour 11");
            for(Player players : Bukkit.getOnlinePlayers()){
                players.playSound(players.getLocation(), Sound.NOTE_PLING, 1f,1f);
            }
        }
        if (timer == 60 * 20) {
            for (Player players : Bukkit.getOnlinePlayers()) {
                players.sendMessage(main.getPrefix() + "§c PVP ACTIF !");
            }

            for (World world : Bukkit.getWorlds()) {
                world.setPVP(true);
            }
        }
    }
    public static int getTimer() {

        return timer;
    }
}