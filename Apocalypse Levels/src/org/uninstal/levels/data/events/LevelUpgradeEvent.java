package org.uninstal.levels.data.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.uninstal.levels.data.User;

public class LevelUpgradeEvent extends Event {

	private static HandlerList handlerList = new HandlerList();
	
	@Override
	public HandlerList getHandlers() {
		return handlerList;
	}

	public static HandlerList getHandlerList() {
		return handlerList;
	}
	
	private User user;
	private int newLevel;

	public LevelUpgradeEvent(User user, int newLevel) {
		
		this.user = user;
		this.newLevel = newLevel;
	}
	
	public int getNewLevel() {
		return newLevel;
	}
	
	public User getUser() {
		return user;
	}
}
