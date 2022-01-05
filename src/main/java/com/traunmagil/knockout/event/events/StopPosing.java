package com.traunmagil.knockout.event.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.traunmagil.knockout.Main;

import ru.armagidon.poseplugin.api.events.StopPosingEvent;

public class StopPosing implements Listener {

	private Main main = Main.getInstance();
	
	@EventHandler
	public void onStopPosing(StopPosingEvent e) {
		if(main.knManager.isKnockout(e.getPlayer().getHandle())) e.setCancelled(true);
	}
	
}
