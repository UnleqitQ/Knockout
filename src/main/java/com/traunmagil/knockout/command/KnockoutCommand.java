package com.traunmagil.knockout.command;

import org.bukkit.command.CommandSender;

import com.traunmagil.knockout.Main;


public abstract class KnockoutCommand {

	public Main main = Main.getInstance();
	private String name;
	private String usage;
	
	public KnockoutCommand(String name, String usage) {
		this.name = name;
		this.usage = usage;
	}

	public String getName() {
		return name;
	}
	
	public String getUsage() {
		return usage;
	}
	
	public abstract boolean execute(CommandSender sender, String[] args);
	
	
}

