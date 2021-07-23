package fr.gixy.scoreboard;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PersonalScoreboard {
	
	private final ScoreboardAPI sb;
    private final UUID uuid;
    private final ObjectiveSign objectiveSign;
 
    PersonalScoreboard(ScoreboardAPI sb, Player player){
    	this.sb = sb;
        uuid = player.getUniqueId();
        objectiveSign = new ObjectiveSign("sidebar", "GLOBAL");

        reloadData();
        objectiveSign.addReceiver(player);
    }
 
    public void reloadData(){}
 
	public void setLines() {
        objectiveSign.setDisplayName(sb.getDisplayName());
        
        sb.drawLines(uuid, objectiveSign);


 
        objectiveSign.updateLines();
    }
 
    public void onLogout(){
        objectiveSign.removeReceiver(Bukkit.getServer().getOfflinePlayer(uuid));
    }
}
