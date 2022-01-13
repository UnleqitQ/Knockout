package com.traunmagil.knockout.event;

import com.traunmagil.knockout.Main;
import com.traunmagil.knockout.event.events.*;
import org.bukkit.event.Listener;

import java.util.ArrayList;

public class EventHandler {
	
	private ArrayList<Listener> listener;
	private Main main;
	
	public EventHandler() {
		
		listener = new ArrayList<Listener>();
		main = Main.getInstance();
		
		
		listener.add(new EntityDamage());
		listener.add(new PlayerDeath());
		listener.add(new StopPosing());
		listener.add(new EntityDamageByEntity());
		listener.add(new PlayerLeave());
		listener.add(new BlockPlace());
		listener.add(new BlockBreak());
		listener.add(new PlayerInteract());
		listener.add(new PickupItem());
		listener.add(new PlayerInteractAtEntity());
	}
	
	public void register() {
		for (Listener l : listener) {
			main.getServer().getPluginManager().registerEvents(l, main);
		}
	}
	
}
