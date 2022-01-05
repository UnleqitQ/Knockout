package com.traunmagil.knockout.command.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.traunmagil.knockout.Main;
import com.traunmagil.knockout.command.KnockoutCommand;

public class DeathCommand extends KnockoutCommand {

	public DeathCommand() {
		super("deathnow", "/deathnow");
	}

	private Main main = Main.getInstance();
	
	@Override
	public boolean execute(CommandSender sender, String[] args) {
		
		if(!(sender instanceof Player)) {
			main.knManager.killAllInKnockout();
			Main.consoleLog(main.langManager.getMessage("Deathnow-Message"));
			return true;
		}
		
		Player p = (Player) sender;
		
		if(p.hasPermission("knockout.deathnow")) {
			if(!main.knManager.isKnockout(p)) {
				Main.sendMsg(p, main.langManager.getMessage("Deathnow-NotKnockout-Message"));
				return true;
			}
			p.setHealth(0.0D);
			Main.sendMsg(p, main.langManager.getMessage("Deathnow-Message"));
		} else {
			Main.sendMsg(p, main.langManager.getMessage("Permission"));
		}
		
		
		return true;
	}

}
