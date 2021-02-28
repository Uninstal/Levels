package org.uninstal.levels.db;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.uninstal.levels.data.Manager;
import org.uninstal.levels.data.PerkType;
import org.uninstal.levels.data.User;

public class Operator {

	private Database database;
	
	private static String table = "levels";
	private static String values = "(uuid, level, xp, points, dexterity, "
			+ "survivabillity, metalleather, walker, hunter, cooking, craft, "
			+ "eloquence, medicine, saveenergy)";
	
	public Operator(Database db) {
		this.database = db;
	}
	
	public void load() {
		ResultSet resultSet = database.getFull(table);
		
		try {
			
			while(resultSet.next()) {
				
				UUID uuid = UUID.fromString(resultSet.getString(1));
				int level = resultSet.getInt(2);
				int xp = resultSet.getInt(3);
				int points = resultSet.getInt(4);
				
				int dexterity = resultSet.getInt(5);
				int survivabillity = resultSet.getInt(6);
				int metalLeather = resultSet.getInt(7);
				int walker = resultSet.getInt(8);
				int hunter = resultSet.getInt(9);
				int cooking = resultSet.getInt(10);
				int craft = resultSet.getInt(11);
				int eloquence = resultSet.getInt(12);
				int medicine = resultSet.getInt(13);
				int saveEnergy = resultSet.getInt(14);
				
				Map<PerkType, Integer> perks = new HashMap<>();
				perks.put(PerkType.Dexterity, dexterity);
				perks.put(PerkType.Survivabillity, survivabillity);
				perks.put(PerkType.MetalLeather, metalLeather);
				perks.put(PerkType.Walker, walker);
				perks.put(PerkType.Hunter, hunter);
				perks.put(PerkType.Cooking, cooking);
				perks.put(PerkType.Craft, craft);
				perks.put(PerkType.Eloquence, eloquence);
				perks.put(PerkType.Medicine, medicine);
				perks.put(PerkType.SaveEnergy, saveEnergy);
				
				User user = new User(uuid, xp, level, points, perks);
				Manager.add(user);
				
				continue;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void save() {
		database.deleteFull(table);
		
		for(User user : Manager.users()) {
			
			UUID uuid = user.uuid();
			int level = user.getLevel();
			int xp = user.getXp();
			int points = user.getPoints();
			int dexterity = user.getPerkLevel(PerkType.Dexterity);
			int survivabillity = user.getPerkLevel(PerkType.Survivabillity);
			int metalLeather = user.getPerkLevel(PerkType.MetalLeather);
			int walker = user.getPerkLevel(PerkType.Walker);
			int hunter = user.getPerkLevel(PerkType.Hunter);
			int cooking = user.getPerkLevel(PerkType.Cooking);
			int craft = user.getPerkLevel(PerkType.Craft);
			int eloquence = user.getPerkLevel(PerkType.Eloquence);
			int medicine = user.getPerkLevel(PerkType.Medicine);
			int saveEnergy = user.getPerkLevel(PerkType.SaveEnergy);
			
			String command = "INSERT INTO " + table + " " + values + " ('" + uuid.toString() + "', "
					+ level + ", " + xp + ", " + points + ", " + dexterity + ", " + survivabillity
					+ ", " + metalLeather + ", " + walker + ", " + hunter + ", " + cooking + ", "
					+ craft + ", " + eloquence + ", " + medicine + ", " + saveEnergy;
			
			database.send(command);
			continue;
		}
	}
}
