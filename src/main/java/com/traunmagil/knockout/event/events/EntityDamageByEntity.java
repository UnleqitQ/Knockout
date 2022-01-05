package com.traunmagil.knockout.event.events;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.traunmagil.knockout.Main;

public class EntityDamageByEntity implements Listener {

	private Main main = Main.getInstance();
	
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
		
		if(e.getDamager().getType() != EntityType.PLAYER) return;
		Player d = (Player)e.getDamager();
		
		if(main.knManager.isKnockout(d)) e.setCancelled(true);
	}
	
}
