package org.uninstal.levels.util;

import java.util.UUID;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.uninstal.levels.data.Manager;
import org.uninstal.levels.data.PerkType;
import org.uninstal.levels.data.User;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;

public class Expansion extends PlaceholderExpansion {

	@Override
	public String getAuthor() {
		return "Uninstal";
	}

	@Override
	public String getIdentifier() {
		return "levels";
	}

	@Override
	public String getVersion() {
		return "1.0";
	}
	
	@Override
	public String onRequest(OfflinePlayer p, String params) {
		
		if(p != null && p.isOnline())
			return onPlaceholderRequest((Player) p, params);
		
		return null;
	}
	
	@Override
	public String onPlaceholderRequest(Player p, String params) {
		
		if(params.startsWith("level")) {
			
			UUID uuid = p.getUniqueId();
			User user = Manager.get(uuid);
			
			if(params.equalsIgnoreCase("level")) 
				return String.valueOf(user.getLevel());
			
			String perkTypeName = params.substring(6);
			PerkType perkType = PerkType.valueOf(perkTypeName);
			
			int perkLevel = user.getPerkLevel(perkType);
			return String.valueOf(perkLevel);
		}
		
		if(params.equalsIgnoreCase("xp")) {
			
			UUID uuid = p.getUniqueId();
			User user = Manager.get(uuid);
			
			return String.valueOf(user.getXp());
		}

		if(params.equalsIgnoreCase("needxp")) {
			
			UUID uuid = p.getUniqueId();
			User user = Manager.get(uuid);
			
			return String.valueOf(user.getNeedXp());
		}
		
		return new String();
	}
}
