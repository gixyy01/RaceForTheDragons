package fr.gixy.commands;

import fr.gixy.Main;
import fr.gixy.State;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandTemple implements CommandExecutor {

    private final Main main;

    public CommandTemple(Main main) {

        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (main.isState(State.WAITING)) {
                if (player.isOp()) {

                    Block block = player.getLocation().getBlock();
                    int x = block.getLocation().getBlockX();
                    int y = block.getLocation().getBlockY();
                    int z = block.getLocation().getBlockZ();

                    main.setX(x);
                    main.setY(y);
                    main.setZ(z);

                    player.sendMessage(main.getPrefix()+"§eLes coordonnées du temple sont " + x + " " + y + " " + z);
                    main.setCanStart(true);
                }else{
                    player.sendMessage("§cVous n'avez pas les permissions de faire cette commande");
                }
            }else{
                player.sendMessage("§cCommande impossible en jeu");
            }
        }
        return false;
    }

}
