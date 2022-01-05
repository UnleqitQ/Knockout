package com.traunmagil.knockout.countdown;

import org.bukkit.Bukkit;

public abstract class Countdown {

	int start;
	int current;
	boolean running;
	int taskId;
	
	public Countdown(int start) {
		this.start = start;
		this.current = start;
		this.running = false;
	}
	
	public abstract void run();
	public abstract void end();

	public void cancel() {
		Bukkit.getScheduler().cancelTask(taskId);
		setRunning(false);
		setCurrent(getStart());
	}
	
	public void forceStart(int time) {
		if(!isRunning()) {
			run();
		}
		if(getCurrent() > time) {
			setCurrent(time);
		}
	}
	
	public void pause() {
		Bukkit.getScheduler().cancelTask(taskId);
		setRunning(false);
	}
	
	public int getStart() {
		return start;
	}
	
	public void setStart(int start) {
		this.start = start;
	}
	
	public int getCurrent() {
		return current;
	}
	
	public void setCurrent(int current) {
		this.current = current;
	}
	
	public boolean isRunning() {
		return running;
	}
	
	public void setRunning(boolean running) {
		this.running = running;
	}
	
	public int getTaskId() {
		return taskId;
	}
	
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	
}
