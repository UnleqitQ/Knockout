package com.traunmagil.knockout.event.events;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import com.traunmagil.knockout.Main;


public class EntityDamage implements Listener {

	private static Main main = Main.getInstance();
	
	@EventHandler
	public void onEntityDamage(EntityDamageEvent e) {
		
		if(e.getEntityType() != EntityType.PLAYER) return;
		
		Player p = (Player) e.getEntity();
		
		
		if(p.getHealth() <= e.getDamage()) {
			if(!main.knManager.isKnockout(p)) {
				e.setCancelled(true);
				if(e.getCause() == DamageCause.FALL) p.teleport(p.getLocation().add(0, 0.17, 0));
				p.setHealth(20.0);
				main.knManager.setKnockout(p, e.getCause());
				return;
			}
		} 
		
		if(main.knManager.isKnockout(p)) {
			if(main.rvManager.wereRevived(p)) {
				e.setCancelled(true);
				return;
			}
			double life = Math.round((p.getHealth()-e.getDamage())/ 2 * 10) / 10.0;
			Main.sendMsg(p, main.langManager.getMessage("Knockout-Life-Message").replace("%life%", String.valueOf(life)));
		}
		
	}
	
}
