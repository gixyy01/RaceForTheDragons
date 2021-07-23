package fr.gixy.commands;

import fr.gixy.Main;
import fr.gixy.State;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandHeal implements CommandExecutor {

    private Main main;


    public CommandHeal(Main main) {

        this.main = main;

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (main.isState(State.PLAYING)) {
                if (player.isOp()) {

                    for (Player players : Bukkit.getOnlinePlayers()) {

                        players.setHealth(20);
                        players.sendMessage("§aTous les joueurs ont été §dsoignés !");
                    }
                }else{
                    player.sendMessage("§cVous n'avez pas les permissions requises..");
                    player.playSound(player.getLocation(), Sound.VILLAGER_HAGGLE, 1f, 1f);

                }
                }else {
                player.sendMessage("§cCommande impossible à ce moment de la partie !");
                player.playSound(player.getLocation(), Sound.VILLAGER_HAGGLE, 1f, 1f);
            }
        }

        return false;
    }
}
