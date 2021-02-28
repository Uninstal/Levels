package org.uninstal.levels.data.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.uninstal.levels.data.PerkType;
import org.uninstal.levels.data.User;

public class PerkUpgradeEvent extends Event {

	private static HandlerList handlerList = new HandlerList();
	
	@Override
	public HandlerList getHandlers() {
		return handlerList;
	}

	public static HandlerList getHandlerList() {
		return handlerList;
	}
	
	private User user;
	private PerkType type;
	private int newLevel;

	public PerkUpgradeEvent(User user, PerkType type, int newLevel) {
		
		this.user = user;
		this.type = type;
		this.newLevel = newLevel;
	}
	
	public int getNewLevel() {
		return newLevel;
	}
	
	public PerkType getType() {
		return type;
	}
	
	public User getUser() {
		return user;
	}
}
