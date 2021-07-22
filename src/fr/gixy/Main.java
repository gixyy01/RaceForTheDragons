package fr.gixy;

import fr.gixy.commands.CommandRevive;
import fr.gixy.commands.CommandTemple;
import fr.gixy.gui.GUIManager;
import fr.gixy.listeners.*;
import net.minecraft.server.v1_8_R3.GameRules;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Main extends JavaPlugin {

    List<UUID> players = new ArrayList<>();
    List<UUID> spectators = new ArrayList<>();
    private boolean canStart;
    private State state;
    private Damage damage;
    private String prefix = "§f[§6RACE FOR THE DRAGON§f] ";
    private int x;
    private int y;
    private int z;

    @Override
    public void onEnable() {

        setState(State.WAITING);
        canStart = false;
        new GUIManager(this);
        setDamage(Damage.FALSE);

        for (World worlds : Bukkit.getWorlds()) {

            worlds.setDifficulty(Difficulty.HARD);
            worlds.setGameRuleValue("naturalRegeneration", "false");

        }


        PluginManager pm = getServer().getPluginManager();

        pm.registerEvents(new PlayerJoin(this), this);
        pm.registerEvents(new PlayerQuit(this), this);
        pm.registerEvents(new DamageListener(this), this);
        pm.registerEvents(new WorldEvent(this), this);
        pm.registerEvents(new CheckWin(this),this);
        pm.registerEvents(new ConfigInteractGUI(this),this);
        pm.registerEvents(new PlayerDeath(this),this);
        pm.registerEvents(new ChatListener(this),this);

        getCommand("temple").setExecutor(new CommandTemple(this));
        getCommand("revive").setExecutor(new CommandRevive(this));

    }


    @Override
    public void onDisable() {
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

    public void eliminate(Player player) {

        if (players.contains(player.getUniqueId())) players.remove(player.getUniqueId());
        player.setGameMode(GameMode.SPECTATOR);
    }

    public void checkNoWin(){
        if(players.size() == 0){
            for(Player players : Bukkit.getOnlinePlayers()){
                players.sendMessage(getPrefix()+"§aPersonne a gagné !");
                setState(State.FINISH);
            }
        }
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

    public boolean isDamage(Damage damage){
        return this.damage == damage;
    }

    public void setDamage(Damage damage) {
        this.damage = damage;
    }

    public List<UUID> getSpectators() {
        return spectators;
    }

    public boolean isAlive(Player player, boolean isAlive) {
        return isAlive;
    }

    public void setAlive(boolean alive, Player player) {
    }

    public boolean isCanStart() {
        return canStart;
    }

    public void setCanStart(boolean canStart) {
        this.canStart = canStart;
    }
}


