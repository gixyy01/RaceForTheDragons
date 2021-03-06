package fr.gixy;

import fr.gixy.commands.CommandHeal;
import fr.gixy.commands.CommandRevive;
import fr.gixy.commands.CommandSetTemple;
import fr.gixy.gui.GUIManager;
import fr.gixy.listeners.*;
import fr.gixy.scenario.CutClean;
import fr.gixy.scoreboard.RFTDScoreboard;
import fr.gixy.scoreboard.ScoreboardAPI;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Main extends JavaPlugin {

    List<UUID> players = new ArrayList<>();
    List<UUID> spectators = new ArrayList<>();
    List<UUID> winner = new ArrayList<>();
    private boolean canStart;
    private boolean nether;
    private boolean end;
    private boolean cutClean;
    private boolean giveStuff;
    private State state;
    private Damage damage;
    private final String prefix = "§f[§6RACE FOR THE DRAGON§f] ";
    private int x;
    private int y;
    private int z;
    private int endTimer;
    private int finalHeal;
    private int worldBorderSize;
    private ScoreboardAPI scoreboard;

    @Override
    public void onEnable() {

        setState(State.WAITING);

        canStart = false;

        nether = true;

        new GUIManager(this);

        setDamage(Damage.FALSE);

        this.scoreboard = new RFTDScoreboard(this);
        for (World worlds : Bukkit.getWorlds()) {

            worlds.setDifficulty(Difficulty.HARD);
            worlds.setGameRuleValue("naturalRegeneration", "false");
            worlds.getWorldBorder().reset();

        }

        endTimer = 20;
        finalHeal = 20;
        worldBorderSize = 2000;
        PluginManager pm = getServer().getPluginManager();

        pm.registerEvents(new PlayerJoin(this), this);
        pm.registerEvents(new PlayerQuit(this), this);
        pm.registerEvents(new DamageListener(this), this);
        pm.registerEvents(new WorldEvent(this), this);
        pm.registerEvents(new CheckWin(this), this);
        pm.registerEvents(new ConfigInteractGUI(this), this);
        pm.registerEvents(new PlayerDeath(this), this);
        pm.registerEvents(new ChatListener(this), this);
        pm.registerEvents(new PortalListener(this), this);
        pm.registerEvents(new FoodLevelListener(this), this);
        pm.registerEvents(new CutClean(this), this);

        getCommand("setTemple").setExecutor(new CommandSetTemple(this));
        getCommand("revive").setExecutor(new CommandRevive(this));
        getCommand("heal").setExecutor(new CommandHeal(this));

    }


    @Override
    public void onDisable() {
        this.getScoreboard().onDisable();
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public boolean isState(State state) {
        return this.state == state;
    }

    public List<UUID> getPlayers() {
        return players;
    }

    public String getPrefix() {
        return prefix;
    }

    public void spawn(Player player) {

        Location spawn = new Location(Bukkit.getWorld("world"), 0, 100, 0);
        player.teleport(spawn);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public boolean isDamage(Damage damage) {
        return this.damage == damage;
    }

    public void setDamage(Damage damage) {
        this.damage = damage;
    }

    public List<UUID> getSpectators() {
        return spectators;
    }

    public boolean isAlive(Player player) {
        return !this.spectators.contains(player.getUniqueId());
    }

    public void setAlive(boolean alive, Player player) {
    }

    public boolean isCanStart() {
        return canStart;
    }

    public void setCanStart(boolean canStart) {
        this.canStart = canStart;
    }

    public boolean isNether() {
        return nether;
    }

    public void setNether(boolean nether) {
        this.nether = nether;
    }

    public boolean isEnd() {
        return end;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }

    public ScoreboardAPI getScoreboard() {
        return scoreboard;
    }

    public void eliminate(Player player) {

        if (players.contains(player.getUniqueId())) players.remove(player.getUniqueId());
        player.setGameMode(GameMode.SPECTATOR);
    }

    public void checkNoWin() {
        if (players.size() == 0) {
            for (Player players : Bukkit.getOnlinePlayers()) {
                players.sendMessage(this.getPrefix() + "§aPersonne a gagné !");
                setState(State.FINISH);

            }
        }
    }

    public List<UUID> getWinner() {
        return winner;
    }

    public boolean isCutClean() {
        return cutClean;
    }

    public void setCutClean(boolean cutClean) {
        this.cutClean = cutClean;
    }

    public int getEndTimer() {
        return endTimer;
    }

    public void setEndTimer(int endTimer) {
        this.endTimer = endTimer;
    }

    public int getFinalHeal() {
        return finalHeal;
    }

    public void setFinalHeal(int finalHeal) {
        this.finalHeal = finalHeal;
    }

    public boolean isGiveStuff() {
        return giveStuff;
    }

    public void setGiveStuff(boolean giveStuff) {
        this.giveStuff = giveStuff;
    }

    public void stuffGive(Player player) {

        ItemStack book = new ItemStack(Material.BOOK, 3);
        ItemStack axe = new ItemStack(Material.IRON_AXE);
        ItemStack pickaxe = new ItemStack(Material.IRON_PICKAXE);
        ItemStack beef = new ItemStack(Material.COOKED_BEEF, 64);
        ItemStack water = new ItemStack(Material.WATER_BUCKET);
        player.getInventory().addItem(book, axe, pickaxe, beef, water);
    }

    public int getWorldBorderSize() {
        return worldBorderSize;
    }

    public void setWorldBorderSize(int worldBorderSize) {
        this.worldBorderSize = worldBorderSize;
    }
}