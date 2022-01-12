package com.traunmagil.knockout;

import com.traunmagil.knockout.command.CommandHandler;
import com.traunmagil.knockout.event.EventHandler;
import com.traunmagil.knockout.utils.FileManager;
import com.traunmagil.knockout.utils.KnockoutManager;
import com.traunmagil.knockout.utils.LanguageManager;
import com.traunmagil.knockout.utils.ReviveManager;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	public static String PREFIX;
	public static String DEFAULT_CLR = "§7";
	public static String ERR_CLR = "§c";
	public static ConsoleCommandSender console;
	
	private static Main instance;
	
	private CommandHandler cmdHandler;
	private EventHandler evHandler;
	
	public FileManager fileManager;
	public LanguageManager langManager;
	public KnockoutManager knManager;
	public ReviveManager rvManager;
	
	@Override
	public void onEnable() {
		instance = this;
		console = getServer().getConsoleSender();
		
		loadConfig();
		
		PREFIX = ChatColor.translateAlternateColorCodes('&', getConfig().getString("prefix")) + " ";
		
		this.fileManager = new FileManager();
		this.langManager = new LanguageManager();
		this.rvManager = new ReviveManager();
		this.knManager = new KnockoutManager();
		this.cmdHandler = new CommandHandler();
		this.evHandler = new EventHandler();
		
		cmdHandler.register();
		evHandler.register();
	}
	
	
	@Override
	public void onDisable() {
		knManager.killAllInKnockout();
	}
	
	private void loadConfig() {
		this.getConfig().options().copyDefaults(true);
		this.getConfig().addDefault("language", "de");
		this.getConfig().addDefault("prefix", "&8[§bKnockout&8]");
		this.getConfig().addDefault("revive-time", 20);
		
		this.saveConfig();
	}
	
	public static Main getInstance() {
		return instance;
	}
	
	public static void consoleLog(String msg) {
		console.sendMessage(PREFIX + DEFAULT_CLR + msg);
	}
	
	public static void consoleErr(String err) {
		console.sendMessage(PREFIX + ERR_CLR + err);
	}
	
	public static void sendMsg(Player p, String msg) {
		p.sendMessage(PREFIX + DEFAULT_CLR + msg);
	}
	
}
