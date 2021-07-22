package fr.gixy.gui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.Consumer;


/**
 * 
 * Easily create guis, without messing your hands. <i>Note that if you do
 * use this in one of your projects, leave this notice.</i> <i>Please do credit
 * me if you do use this in one of your projects.</i>
 * 
 * @author Maygo
 */
public abstract class GUI {

	private final String title;
	private final int lines;
	private boolean closable = true;

	private final Inventory inventory;
	private Player currentViewer;

	private final Map<ItemStack, ItemData> items;
	private BukkitTask updateTask;

	/**
	 * 
	 * Create new GUI with title & lines
	 * 
	 * @param title The title of the GUI
	 * @param lines The number of lines in the GUI
	 */
	public GUI(String title, int lines) {
		this.title = title;
		this.lines = lines;
		this.items = new HashMap<>();
		this.inventory = Bukkit.createInventory(null, lines * 9, title);
		GUIManager.registerGUI(this, this.inventory);
	}

	public abstract void drawItems();

	public void onInventoryClose(InventoryCloseEvent event) {
		if(this.updateTask != null) this.updateTask.cancel();
		this.items.clear();
		GUIManager.unregisterGUI(this.getInventory());
	}
	
	public void setUpdate(int ticks) {
		this.updateTask = new BukkitRunnable() {
			
			@Override
			public void run() {
				updateItems();
			}
		}.runTaskTimer(GUIManager.getPlugin(), 0, ticks);
	}
	
	public void updateItems() {
		this.inventory.clear();
		this.drawItems();
	}
	
	public void open(Player player) {
		updateItems();
		player.openInventory(getInventory());
		this.currentViewer = player;
	}
	
	public void close(Player player) {
		this.setClosable(true);
		if(this.currentViewer == player) this.currentViewer = null;
		player.closeInventory();
	}

	public String getTitle() {
		return title;
	}

	public int getSize() {
		return lines * 9;
	}

	public boolean isClosable() {
		return closable;
	}

	public void setClosable(boolean closable) {
		this.closable = closable;
	}

	public void addItem(ItemStack item) {
		this.addItem(item, true);
	}

	public void addItem(ItemStack item, boolean canceled) {
		this.items.put(item, new ItemData(canceled, null));
		this.inventory.addItem(item);
	}

	public void addItem(ItemStack item, Consumer<ClickEvent> onClick) {
		this.items.put(item, new ItemData(true, onClick));
		this.inventory.addItem(item);
	}
	
	public void setItem(int slot, ItemStack item) {
		this.setItem(slot, item, true);
	}

	public void setItem(int slot, ItemStack item, boolean canceled) {
		this.items.put(item, new ItemData(canceled, null));
		this.inventory.setItem(slot, item);
	}

	public void setItem(int slot, ItemStack item, Consumer<ClickEvent> onClick) {
		this.items.put(item, new ItemData(true, onClick));
		this.inventory.setItem(slot, item);
	}
	
	public Inventory getInventory() {
		return inventory;
	}

	public Player getCurrentViewer() {
		return currentViewer;
	}

	public Map<ItemStack, ItemData> getItems() {
		return items;
	}
	
	public Optional<Entry<ItemStack, ItemData>> getItem(ItemStack item) {
		return items.entrySet().stream().filter(e -> e.getKey().isSimilar(item)).findFirst();
	}

	public static class ItemData {

		private final boolean canceled;
		private final Consumer<ClickEvent> onClick;

		public ItemData(boolean canceled, Consumer<ClickEvent> onClick) {
			this.canceled = canceled;
			this.onClick = onClick;
		}

		public boolean isCanceled() {
			return canceled;
		}

		public Consumer<ClickEvent> getOnClick() {
			return onClick;
		}

	}

	public static class ClickEvent {

		private final Player player;
		private final ItemStack item;
		private final InventoryClickEvent event;

		public ClickEvent(Player player, ItemStack item, InventoryClickEvent event) {
			this.player = player;
			this.item = item;
			this.event = event;
		}

		public Player getPlayer() {
			return player;
		}

		public ItemStack getItem() {
			return item;
		}

		public InventoryClickEvent getEvent() {
			return event;
		}

	}

}