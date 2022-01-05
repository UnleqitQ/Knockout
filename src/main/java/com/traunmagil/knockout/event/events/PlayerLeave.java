package com.traunmagil.knockout.event.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.traunmagil.knockout.Main;

public class PlayerLeave implements Listener {

	private Main main = Main.getInstance();
	
	@EventHandler
	public void on(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		
		if(main.knManager.isKnockout(p)) {
			p.setHealth(0.0);
			main.knManager.removeKnockout(p);
		}
		
	}
	
}
