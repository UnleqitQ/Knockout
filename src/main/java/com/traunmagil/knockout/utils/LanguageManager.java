package com.traunmagil.knockout.utils;

import java.util.HashMap;

import org.bukkit.configuration.file.FileConfiguration;

import com.traunmagil.knockout.Main;

import net.md_5.bungee.api.ChatColor;

public class LanguageManager {

	private HashMap<String, String> msg;
	private Main main;
	
	public LanguageManager() {
		this.msg = new HashMap<String, String>();
		this.main = Main.getInstance();
		
		loadMessages();
	}
	
	private void loadMessages() {
		String lang = main.getConfig().getString("language");
		FileConfiguration cfg = main.fileManager.get(lang);
		
		for(String key : cfg.getKeys(false)) {
			msg.put(key, cfg.getString(key));
		}
	}
	
	public String getMessage(String key) {
		if(!msg.containsKey(key)) {
			return "§4No message defined for key §6" + key;
		}
		
		
		return ChatColor.translateAlternateColorCodes('&',msg.get(key));
	}
	
	
}
