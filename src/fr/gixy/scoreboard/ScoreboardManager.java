package fr.gixy.scoreboard;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.bukkit.entity.Player;

public class ScoreboardManager {

	private final ScoreboardAPI sb;
	private final Map<UUID, PersonalScoreboard> scoreboards;
	private final ScheduledFuture task;

	public ScoreboardManager(ScoreboardAPI scoreboardAPI) {
		this.sb = scoreboardAPI;
		this.scoreboards = new HashMap<>();

		this.task = scoreboardAPI.getScheduledExecutorService().scheduleAtFixedRate(() -> {
			for (PersonalScoreboard scoreboard : scoreboards.values())
				scoreboardAPI.getExecutorMonoThread().execute(() -> scoreboard.setLines());
		}, 1, 1, TimeUnit.SECONDS);
	}

	public void onDisable() {
		scoreboards.values().forEach(PersonalScoreboard::onLogout);

		this.task.cancel(true);
	}

	public void onLogin(Player player) {
		if (scoreboards.containsKey(player.getUniqueId())) {
			return;
		}
		scoreboards.put(player.getUniqueId(), new PersonalScoreboard(sb, player));
	}

	public void onLogout(Player player) {
		if (scoreboards.containsKey(player.getUniqueId())) {
			scoreboards.get(player.getUniqueId()).onLogout();
			scoreboards.remove(player.getUniqueId());
		}
	}

	public void update(Player player) {
		if (scoreboards.containsKey(player.getUniqueId())) {
			scoreboards.get(player.getUniqueId()).reloadData();
		}
	}

}
