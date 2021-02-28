package org.uninstal.levels.data.gui;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public interface Gui {

	public void open();
	public void send(InventoryClickEvent e);
	public void send(InventoryCloseEvent e);
}
