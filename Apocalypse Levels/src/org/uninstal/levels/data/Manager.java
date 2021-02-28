package org.uninstal.levels.data;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Manager {

	private static Map<UUID, User> users = new HashMap<>();
	
	public static void add(User user) {
		users.put(user.uuid(), user);
	}
	
	public static void remove(UUID uuid) {
		users.remove(uuid);
	}
	
	public static User get(UUID uuid) {
		return users.get(uuid);
	}
	
	public static boolean contains(UUID uuid) {
		return users.containsKey(uuid);
	}
	
	public static Collection<User> users() {
		return users.values();
	}
}
