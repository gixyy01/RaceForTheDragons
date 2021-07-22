package fr.gixy.gui;

import fr.gixy.gui.GUI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;


public class GUIManager implements Listener{
	
	public static Plugin plugin;
	
	private static Map<Inventory, GUI> inventorys;
	
	public GUIManager(Plugin pl) {
		this.plugin = pl;
		pl.getServer().getPluginManager().registerEvents(this, pl);
		this.inventorys = new HashMap<>();
	}
	
	public static void registerGUI(GUI gui, Inventory inv) {
		inventorys.put(inv, gui);
	}
	
	public static void unregisterGUI(Inventory inv) {
		inventorys.remove(inv);
	}
	

	@EventHandler
	public void onPlayerClickIntoInventory(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		Inventory inv = event.getClickedInventory();
		ItemStack item = event.getCurrentItem();
		if(item == null) return;
		if(inv == null) return;
		if(inventorys.containsKey(inv)) {
			GUI gui = inventorys.get(inv);  
			if(gui.getItem(item).isPresent()) {
				GUI.ItemData data = gui.getItem(item).get().getValue();
				event.setCancelled(data.isCanceled());
				if(data.getOnClick() != null) data.getOnClick().accept(new GUI.ClickEvent(player, item, event));
			}
		}
	}
	
	@EventHandler
	public void onInventoryClose(InventoryCloseEvent event) {
		Player player = (Player) event.getPlayer();
		if(inventorys.containsKey(event.getInventory())) {
			GUI gui = inventorys.get(event.getInventory());
			if(!gui.isClosable()) {
				new BukkitRunnable() {
					
					@Override
					public void run() {
						player.openInventory(event.getInventory());
					}
				}.runTaskLater(getPlugin(), 2);
			}else { gui.onInventoryClose(event);}
		}
	}
	
	public static Plugin getPlugin() {
		return plugin;
	}
	
}