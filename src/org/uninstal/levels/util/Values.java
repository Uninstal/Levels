package org.uninstal.levels.util;

import java.util.List;
import java.util.Map;

import org.bukkit.configuration.file.YamlConfiguration;
import org.uninstal.levels.data.PerkType;

public class Values {

	private static YamlConfiguration config;

	public void setUseConfig(YamlConfiguration file) {
		config = file;
	}
	
	public void read() {
		loadPerksValues();
	}
	
	public static List<String> LOOTABLE_ENTITIES;
	public static Map<PerkType, Object> PERKS;
	public static Map<String, String> NAMES;
	public static Map<String, Integer> MOBS;
	public static Map<Integer, Integer> LEVELS;
	
	private static void loadPerksValues() {
		String path = "settings.perks.";
		
		double dexterity = config.getDouble(path + "Dexterity");
		double survivabillity = config.getDouble(path + "Survivabillity");
		double metalLeather = config.getDouble(path + "MetalLeather");
		float walker = (float) config.getDouble(path + "Walker");
		int hunter = config.getInt(path + "Hunter");
		
		PERKS.put(PerkType.Dexterity, dexterity);
		PERKS.put(PerkType.Survivability, survivabillity);
		PERKS.put(PerkType.MetalLeather, metalLeather);
		PERKS.put(PerkType.Walker, walker);
		PERKS.put(PerkType.Hunter, hunter);
		
		return;
	}
}
