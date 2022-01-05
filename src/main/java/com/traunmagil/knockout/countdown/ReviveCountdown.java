package com.traunmagil.knockout.countdown;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.traunmagil.knockout.Main;


public class ReviveCountdown extends Countdown {

	private static Main main = Main.getInstance();
	private Player p;
	
	public ReviveCountdown(int start, Player p) {
		super(start);
		this.p = p;
	}

	@Override
	public void run() {
		setRunning(true);
		taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(main, ()-> {
			if(!main.rvManager.wereRevived(p)) cancel();
			p.sendTitle(main.langManager.getMessage("Revive-Title"), main.langManager.getMessage("Revive-Countdown").replace("%cd%", String.valueOf(getCurrent())), 0, 22, 0);
			setCurrent(getCurrent()-1);
			if(getCurrent() < 0) end();
		}, 0L, 20L);
	}

	@Override
	public void end() {
		main.rvManager.endRevivePlayer(p);
		setStart(getCurrent());
		cancel();
	}

}
