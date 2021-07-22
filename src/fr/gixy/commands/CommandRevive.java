package fr.gixy.commands;

import fr.gixy.Main;
import fr.gixy.listeners.PlayerDeath;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.libs.org.ibex.nestedvm.util.Platform;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class CommandRevive implements CommandExecutor {

    private Main main;
    private PlayerDeath playerDeath;


    public CommandRevive(Main main) {

        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 1) {
                Player target = Bukkit.getPlayer((args[0]));
                if (args[0].equalsIgnoreCase(target.getName())) {
                    if(target.getName() == null){
                        player.sendMessage("§cCe joueur est déconnecté ou il n'existe pas !");
                        return true;
                    }
                    if (!main.getPlayers().contains(target.getUniqueId())) {
                        main.getPlayers().add(target.getUniqueId());
                        target.setGameMode(GameMode.SURVIVAL);
                        for(Player players : Bukkit.getOnlinePlayers()){
                            players.sendMessage(main.getPrefix()+ ChatColor.GREEN+target.getName()+" §ea été revive");
                        }
                        target.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20 * 15, 255, false, false));
                        Location location = new Location(Bukkit.getWorld("world"), 100, 100, 100);
                        target.teleport(location);

                    } else {
                        player.sendMessage("§cCe joueur n'est pas mort");
                    }


                } else {
                    player.sendMessage("§cCommande incorrect : §f /revive pseudo");
                    return true;


                }
            }


        }
        return false;
    }
}
