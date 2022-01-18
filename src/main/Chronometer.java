package main;

public class Chronometer {
	private int time_fps;
	private int frames;
	private boolean running;
	
	public Chronometer(int time_fps, boolean running) {
		this.time_fps = time_fps;
		this.running = running;
		frames = 0;
	}
	
	public void run() {
		running = true;
		frames = time_fps;
	}
	
	public void update()  {
		if(running) {
			frames--;
		}
		
		if(frames <= 1) {
			running = false;
		}
	}
	
	public boolean isRunning() {
		return running;
	}

}
