package org.uninstal.levels;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.uninstal.levels.data.Manager;
import org.uninstal.levels.data.PerkType;
import org.uninstal.levels.data.User;
import org.uninstal.levels.data.events.LevelUpgradeEvent;
import org.uninstal.levels.data.events.PerkUpgradeEvent;
import org.uninstal.levels.util.Utils;
import org.uninstal.levels.util.Values;

public class Handler implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		
		Player player = e.getPlayer();
		UUID uuid = player.getUniqueId();
		
		if(!Manager.contains(uuid))
			Manager.add(new User(uuid));
		
		else Manager.get(uuid).updatePerks();
	}
	
	@EventHandler
	public void onMobKill(EntityDeathEvent e) {
		
		LivingEntity entity = e.getEntity();
		Player killer = entity.getKiller();
		
		if(killer != null) {
			
			UUID uuid = killer.getUniqueId();
			User user = Manager.get(uuid);
			
			String type = type(entity);
			if(!Values.MOBS.containsKey(type)) return;
			
			int xp = Values.MOBS.get(type);
			user.setXp(xp);
		}
	}
	
	@EventHandler
	public void onEntityDeath(EntityDeathEvent e) {
		
		LivingEntity entity = e.getEntity();
		Player killer = entity.getKiller();
		
		if(Values.LOOTABLE_ENTITIES
				.stream()
				.anyMatch(t -> type(entity)
				.equalsIgnoreCase(t))) {
			
			if(killer != null) {
				
				UUID uuid = killer.getUniqueId();
				User user = Manager.get(uuid);
				int hunter = user.getPerkLevel(PerkType.Hunter);
				
				List<ItemStack> itemStacks = new ArrayList<>(e.getDrops());
				e.getDrops().clear();
				
				for(ItemStack itemStack : itemStacks) {
					
					if(Utils.random(1, 100) <= hunter * 5)
						itemStack.setAmount(itemStack.getAmount() + 1);
					
					e.getDrops().add(itemStack);
					continue;
				}
			}
		}
	}
	
	@EventHandler
	public void onEntityDamage(EntityDamageByEntityEvent e) {
		if(e.getEntityType() != EntityType.PLAYER) return;
		
		Player player = (Player) e.getEntity();
		User user = Manager.get(player.getUniqueId());
		
		int dexterity = user.getPerkLevel(PerkType.Dexterity);
		
		if(Utils.random(1, 100) <= dexterity * 0.5d) {
			player.sendMessage("Вы увернулись.");
			e.setCancelled(true);
		}
		else metalLeather(e);
	}
	
	private void metalLeather(EntityDamageByEntityEvent e) {
		if(e.getEntityType() != EntityType.PLAYER) return;
		
		Player player = (Player) e.getEntity();
		User user = Manager.get(player.getUniqueId());
		
		int metalLeather = user.getPerkLevel(PerkType.MetalLeather);
		e.setDamage(Math.max(0d, e.getDamage() - metalLeather * 0.5d));
	}
	
	@EventHandler
	public void onRespawn(PlayerRespawnEvent e) {
		
		Player player = e.getPlayer();
		User user = Manager.get(player.getUniqueId());
		user.updatePerks();
	}
	
	@EventHandler
	public void onLevelUpgrade(LevelUpgradeEvent e) {
		
		User user = e.getUser();
		user.updatePerks();
	}
	
	@EventHandler
	public void onPerkUpgrade(PerkUpgradeEvent e) {
		
		User user = e.getUser();
		user.updatePerks();
	}
	
	private String type(Entity entity) {
		return entity.getType().name().toLowerCase();
	}
}
