package com.traunmagil.knockout.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import com.traunmagil.knockout.Main;

public class FileManager {

	Main main = Main.getInstance();
	List<File> files = new ArrayList<File>();
	
	public FileManager() {
		createDefaults();
	}
	
	private void createDefaults() {
		FileConfiguration cfg = get("de");
		cfg.options().copyDefaults(true);
		
		cfg.addDefault("Knockout-Title", "&4Knockout");
		cfg.addDefault("Knockout-Countdown", "&4%cd%");
		cfg.addDefault("Knockout-Message", "&4Knockout! &cIn %time% Sekunden stirbst du.");
		cfg.addDefault("Deathnow-Message", "&aErfolgreich im Knockout getötet.");
		cfg.addDefault("Permission", "&4Du hast dazu keine Berechtigung!");
		cfg.addDefault("Knockout-Life-Message", "&cDein Knockout Leben: &4%life% Herzen");
		cfg.addDefault("Revive-Title", "&4Wiederbelebung");
		cfg.addDefault("Revive-Countdown", "&4%cd%");
		cfg.addDefault("Deathnow-NotKnockout-Message", "&4Du bist nicht im Knockout");
		
		save(cfg, "de");
		
		cfg = get("countdowns");
		cfg.options().copyDefaults(true);
		
		for(DamageCause cause : DamageCause.values()) {
			cfg.addDefault(cause.name(), 120);
		}
		
		save(cfg, "countdowns");
	}
	
	public FileConfiguration get(String name) {
		File file = getFile(name);
		
		return YamlConfiguration.loadConfiguration(file);
	}
	
	public File createFile(String name) {
		File file = new File(main.getDataFolder(), name + ".yml");
		if(!file.exists())
			try {
				if(!file.createNewFile()) {
					Main.consoleErr("File " + name + " konnte nicht erstellt werden!");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		return file;
	}
	
	public void save(FileConfiguration cfg, String name) {
		File file = getFile(name);
		try {
			cfg.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public File getFile(String name) {
		File file = null;
		for(File f : files) {
			if(f.getName().equalsIgnoreCase(name + ".yml"))
				file = f;
		}
		
		if(file == null) {
			file = createFile(name);
			files.add(file);
		}
		
		return file;
	}
	
}
