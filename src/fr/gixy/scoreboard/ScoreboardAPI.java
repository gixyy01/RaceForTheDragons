package fr.gixy.scoreboard;

import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public abstract class ScoreboardAPI {
	
	public String displayName;
	
	/* SCOREBOARD MANAGEMENT */
	private ScoreboardManager scoreboardManager;
    private ScheduledExecutorService scheduledExecutorService;
	private ScheduledExecutorService executorMonoThread;
	
	public ScoreboardAPI(String displayName) {
		this.displayName = displayName;
		this.scheduledExecutorService = Executors.newScheduledThreadPool(16);
		this.executorMonoThread = Executors.newScheduledThreadPool(1);
		this.scoreboardManager = new ScoreboardManager(this);
	}
	
	public ScoreboardManager getScoreboardManager() {
		return this.scoreboardManager;
	}

	public ScheduledExecutorService getScheduledExecutorService() {
		return this.scheduledExecutorService;
	}
	
	public ScheduledExecutorService getExecutorMonoThread() {
		return this.executorMonoThread;
	}

	public String getDisplayName() {
		return this.displayName;
	}
	
	public void onJoin(Player player) {
		this.scoreboardManager.onLogin(player);
	}
	
	public void onQuit(Player player) {
		this.scoreboardManager.onLogout(player);
	}

	public void onDisable() {
		this.scoreboardManager.onDisable();
	}

	public String getIp() {
		return ChatColor.AQUA+"mc.piggy.eu";
	}

	public abstract void drawLines(UUID id, ObjectiveSign sign);
	
}
