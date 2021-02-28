package org.uninstal.levels.data;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.uninstal.levels.util.Values;

public class User {

	private Map<PerkType, Integer> perks = new HashMap<>();
	
	private UUID uuid;
	private int xp;
	private int level;
	private int points;
	
	public User(UUID uuid) {
		
		this.uuid = uuid;
		this.xp = 0;
		this.level = 0;
		this.points = 0;
		
		setDefaults();
	}
	
	public User(UUID uuid, int xp, int level, int points, Map<PerkType, Integer> perks) {
		
		this.uuid = uuid;
		this.xp = xp;
		this.level = level;
		this.points = points;
		this.perks = perks;
	}
	
	public void setDefaults() {
		
		//Перки для работы других плагинов
		this.perks.put(PerkType.Cooking, 0);
		this.perks.put(PerkType.Craft, 0);
		this.perks.put(PerkType.Eloquence, 0);
		this.perks.put(PerkType.Medicine, 0);
		
		//Ловкость, шанс уворота от урона
		this.perks.put(PerkType.Dexterity, 0);
		
		//Охотник, шанс увеличения лута
		this.perks.put(PerkType.Hunter, 0);
		
		//Живучесть, увеличение максимального здоровья
		this.perks.put(PerkType.Survivabillity, 0);
		
		//Скороход, увеличение скорости ходьбы
		this.perks.put(PerkType.Walker, 0);
		
		//Стальная кожа, уменьшение урона
		this.perks.put(PerkType.MetalLeather, 0);
		
		//Экономия энергии, уменьшение скорости понижения голода
		this.perks.put(PerkType.SaveEnergy, 0);
		
		return;
	}
	
	public UUID uuid() {
		return uuid;
	}
	
	public void setLevel(int level) {
		this.level = level;
		this.updateStats();
	}
	
	public void setPoints(int points) {
		this.points = points;
	}
	
	public void addXp(int xp) {
		this.xp += xp;
		this.updateStats();
	}
	
	public void setXp(int xp) {
		this.xp = xp;
		this.updateStats();
	}
	
	public int getLevel() {
		return level;
	}
	
	public int getPoints() {
		return points;
	}
	
	public int getXp() {
		return xp;
	}
	
	public void updateStats() {
		int needXp = getNeedXp();
		
		if(needXp != -1)
			while(needXp <= xp) {
				xp -= needXp;
				++level;
				continue;
			}
	}
	
	public int getNeedXp() {
		return Values.LEVELS.getOrDefault(level + 1, -1);
	}
	
	public int getPerkLevel(PerkType type) {
		return perks.get(type);
	}
	
	//Обновление пассивных перков
	@SuppressWarnings("deprecation")
	public void updatePerks() {
		
		int survivabillity = perks.get(PerkType.Survivabillity);
		int walker = perks.get(PerkType.Walker);
		
		double health = survivabillity * 2d;
		float speed = walker * 0.05f;
		
		Player player = Bukkit.getPlayer(uuid);
		if(player != null) {
			
			player.setMaxHealth(health);
			player.setWalkSpeed(0.2f + speed);
		}
		
		return;
	}
}
