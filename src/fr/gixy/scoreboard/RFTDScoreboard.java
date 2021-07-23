package fr.gixy.scoreboard;

import fr.gixy.Main;
import fr.gixy.State;
import org.bukkit.Bukkit;

import java.util.UUID;

public class RFTDScoreboard extends ScoreboardAPI {

    private Main main;
    public RFTDScoreboard(){
        super("RaceForTheDragon");
    }


    public RFTDScoreboard(String displayName, Main main) {
        super(displayName);
        this.main = main;
    }


    @Override
    public void drawLines(UUID id, ObjectiveSign sign) {

        switch (main.getState()) {
            case WAITING: {
                sign.setLine(0, "§eJoueurs : §f" + Bukkit.getOnlinePlayers().size());
                sign.setLine(1, "t");
                break;
        }
            case STARTING:{
                sign.setLine(0, "§eJoueurs : §f" + Bukkit.getOnlinePlayers().size());
                sign.setLine(1, "t");
                break;

            }
            default:
                sign.setLine(0, "Plugin By Gixy");



        }
    }
}