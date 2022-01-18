package states;

import java.awt.Graphics;

import graphics.Sound;

public abstract class State {
	private static State currentState;
	private static Sound backgroundMusic;
	
	public abstract void update();
	public abstract void draw(Graphics g);
	
	public static State getCurrentState() {
		return currentState;
	}
	
	public static void setCurrentState(State currentState) {
		State.currentState = currentState;
	}
	

	
	public static Sound getBackgroundMusic() {
		return backgroundMusic;
	}
	public static void setBackgroundMusic(Sound backgroundMusic) {
		State.backgroundMusic = backgroundMusic;
	}
	

}
