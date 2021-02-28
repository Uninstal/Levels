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
		
		HOST = config.getString("settings.db.host");
		USER = config.getString("settings.db.user");
		PASS = config.getString("settings.db.pass");
		BASE = config.getString("settings.db.base");
		
		loadPerksNames();
		loadPerksValues();
	}
	
	public static String HOST;
	public static String USER;
	public static String PASS;
	public static String BASE;
	
	public static List<String> LOOTABLE_ENTITIES;
	public static Map<PerkType, Object> PERKS;
	public static Map<PerkType, String> NAMES;
	public static Map<String, Integer> MOBS;
	public static Map<Integer, Integer> LEVELS;
	
	private static void loadPerksNames() {
		String path = "settings.perks.";
		
		String dexterity = config.getString(path + "Dexterity.name");
		String survivabillity = config.getString(path + "Survivabillity.name");
		String metalLeather = config.getString(path + "MetalLeather.name");
		String walker = config.getString(path + "Walker.name");
		String hunter = config.getString(path + "Hunter.name");
		String cooking = config.getString(path + "Cooking.name");
		String craft = config.getString(path + "Craft.name");
		String eloquence = config.getString(path + "Eloquence.name");
		String medicine = config.getString(path + "Medicine.name");
		String saveEnergy = config.getString(path + "SaveEnergy.name");
		
		NAMES.put(PerkType.Dexterity, dexterity);
		NAMES.put(PerkType.Survivabillity, survivabillity);
		NAMES.put(PerkType.MetalLeather, metalLeather);
		NAMES.put(PerkType.Walker, walker);
		NAMES.put(PerkType.Hunter, hunter);
		NAMES.put(PerkType.Cooking, cooking);
		NAMES.put(PerkType.Craft, craft);
		NAMES.put(PerkType.Eloquence, eloquence);
		NAMES.put(PerkType.Medicine, medicine);
		NAMES.put(PerkType.SaveEnergy, saveEnergy);
		
		return;
	}
	
	private static void loadPerksValues() {
		String path = "settings.perks.";
		
		double dexterity = config.getDouble(path + "Dexterity.value");
		double survivabillity = config.getDouble(path + "Survivabillity.value");
		double metalLeather = config.getDouble(path + "MetalLeather.value");
		float walker = (float) config.getDouble(path + "Walker.value");
		int hunter = config.getInt(path + "Hunter.value");
		
		PERKS.put(PerkType.Dexterity, dexterity);
		PERKS.put(PerkType.Survivabillity, survivabillity);
		PERKS.put(PerkType.MetalLeather, metalLeather);
		PERKS.put(PerkType.Walker, walker);
		PERKS.put(PerkType.Hunter, hunter);
		
		return;
	}
}
