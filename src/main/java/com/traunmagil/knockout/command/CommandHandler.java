package com.traunmagil.knockout.command;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;

import com.traunmagil.knockout.Main;
import com.traunmagil.knockout.command.commands.DeathCommand;

public class CommandHandler implements CommandExecutor {

	private List<KnockoutCommand> cmds = new ArrayList<KnockoutCommand>();
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		KnockoutCommand command = getCommand(cmd.getName());
		
		if(command == null) 
			return true;
		command.execute(sender, args);
		
		return true;
	}
	
	protected KnockoutCommand getCommand(String name) {
		for(KnockoutCommand cmd : cmds) {
			if(cmd.getName().equalsIgnoreCase(name))
				return cmd;
		}
		
		return null;
	}
	
	public void register() {
		//add commands here
		this.cmds.add(new DeathCommand());
		
		for(KnockoutCommand cmd : this.cmds) {
			PluginCommand pluginCmd = Main.getInstance().getCommand(cmd.getName());
			if(pluginCmd != null) {
				pluginCmd.setExecutor(this);
				pluginCmd.setUsage(cmd.getUsage());
			}
		}
	}
	
}

