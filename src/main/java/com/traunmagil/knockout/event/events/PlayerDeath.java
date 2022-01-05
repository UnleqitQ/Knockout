package com.traunmagil.knockout.event.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import com.traunmagil.knockout.Main;

public class PlayerDeath implements Listener {

	private Main main = Main.getInstance();
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e) {
		Player p = e.getEntity();
		
		if(main.knManager.isKnockout(p)) {
			main.knManager.removeKnockout(p);
			if(e.getDeathMessage().endsWith(("died"))) e.setDeathMessage("");
		}
		
	}
	
}
