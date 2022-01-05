package com.traunmagil.knockout.countdown;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.traunmagil.knockout.Main;


public class KnockoutCountdown extends Countdown {

	private static Main main = Main.getInstance();
	private Player p;
	
	public KnockoutCountdown(int start, Player p) {
		super(start);
		this.p = p;
	}
	
	public Player getPlayer() {
		return p;
	}

	@Override
	public void run() {
		setRunning(true);
		taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(main, ()-> {
			p.sendTitle(main.langManager.getMessage("Knockout-Title"), main.langManager.getMessage("Knockout-Countdown").replace("%cd%", String.valueOf(getCurrent())), 0, 22, 0);
			setCurrent(getCurrent()-1);
			if(getCurrent() < 0) end();
		}, 0L, 20L);
	}

	@Override
	public void end() {
		p.setHealth(0.0);
		setStart(getCurrent());
		cancel();
	}

}
