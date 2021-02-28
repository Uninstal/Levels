package org.uninstal.levels;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.uninstal.levels.db.Database;
import org.uninstal.levels.util.Values;

public class Main extends JavaPlugin {

	private Files files;

	@Override
	public void onEnable() {
		
		//Загрузка файлов
		this.files = new Files(this);
		YamlConfiguration config = files.registerNewFile("config");
		
		//Загрузка данных из конфига
		Values v = new Values();
		v.setUseConfig(config);
		v.read();
		
		Database db = new Database(Values.HOST, Values.BASE, Values.USER, Values.PASS);
		if(db.getConnection() == null) return;
		
		
	}
	
	@Override
	public void onDisable() {
		
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		// TODO Auto-generated method stub
		return super.onCommand(sender, command, label, args);
	}
}