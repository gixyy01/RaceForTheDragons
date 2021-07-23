package fr.gixy.scoreboard;

import fr.gixy.Main;
import fr.gixy.task.GameLoop;
import fr.gixy.utils.FormatTime;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;

import java.util.UUID;

public class RFTDScoreboard extends ScoreboardAPI {

    private final Main main;

    public RFTDScoreboard(Main main) {
        super("§aRaceForTheDragon");
        this.main = main;
    }



    @Override
    public void drawLines(UUID id, ObjectiveSign sign) {

        Player player = Bukkit.getPlayer(id);
        sign.clearScores();

        switch (main.getState()) {
            case WAITING: {
                sign.setLine(0, "§8§m-------------------------");
                sign.setLine(1, "§0");
                sign.setLine(2, ChatColor.YELLOW + "Joueurs : " + ChatColor.WHITE + main.getPlayers().size());
                sign.setLine(3, ChatColor.YELLOW + "Statut : " + ChatColor.WHITE + "En attente de joueurs");
                sign.setLine(4, "§2");
                sign.setLine(5, "§8§m-------------------------§c");
                sign.setLine(6, "§bPlugin by Gixy");

                break;
            }
            case STARTING: {
                sign.setLine(0, "§8§m-------------------------");
                sign.setLine(1, "§0");
                sign.setLine(2, ChatColor.YELLOW + "Joueurs : " + ChatColor.WHITE + main.getPlayers().size());
                sign.setLine(3, ChatColor.YELLOW + "Statut : " + ChatColor.WHITE + "Démarrage en cours");
                sign.setLine(4, "§2");
                sign.setLine(5, "§8§m-------------------------§c");
                sign.setLine(6, "§bPlugin by Gixy");
                break;
            }

            case PLAYING: {
                sign.setLine(0, "§8§m-------------------------");
                sign.setLine(1, "§0");
                sign.setLine(2,"§eTemps : "+ChatColor.WHITE+ new FormatTime(GameLoop.getTimer()));
                sign.setLine(3, ChatColor.YELLOW + "Joueurs : " + ChatColor.WHITE + main.getPlayers().size());
                sign.setLine(4, ChatColor.YELLOW + "Kills : " + ChatColor.WHITE + player.getStatistic(Statistic.PLAYER_KILLS));
                sign.setLine(5, ChatColor.YELLOW + "Statut : " + ChatColor.WHITE + "En jeu");
                sign.setLine(6, "§2");
                sign.setLine(7, "§8§m-------------------------§c");
                sign.setLine(8, "§bPlugin by Gixy");

                break;
            }

            case FINISH: {
                sign.setLine(0, "§8§m-------------------------");
                sign.setLine(1, "§0");
                for (UUID playerId : main.getWinner()) {
                    Player players = Bukkit.getPlayer(playerId);
                    sign.setLine(2, ChatColor.YELLOW + "Gagnant : " + ChatColor.WHITE + players.getName());
                }
                sign.setLine(3, ChatColor.YELLOW + "Kills : " + ChatColor.WHITE + player.getStatistic(Statistic.PLAYER_KILLS));
                sign.setLine(4, ChatColor.YELLOW + "Statut : " + ChatColor.WHITE + "Terminé");
                sign.setLine(5, "§2");
                sign.setLine(6, "§8§m-------------------------§c");
                sign.setLine(7, "§bPlugin by Gixy");

                break;
            }
            default:
        }
    }
}