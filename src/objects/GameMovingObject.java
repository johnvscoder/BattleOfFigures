package objects;

import java.awt.image.BufferedImage;

import math.Vector2D;
import states.TestState;

public abstract class GameMovingObject extends GameObject {
	protected Vector2D velocity;
	protected Vector2D acceleration;
	protected boolean dir;
	protected TestState testState;
	protected boolean dead;
	protected double radio;
	
	public GameMovingObject(double angle, Vector2D position,
			Vector2D velocity, Vector2D acceleration, boolean dir, TestState testState,
			double radio) {
		super(angle, position);
		this.velocity = velocity;
		this.acceleration = acceleration;
		this.dir = dir;
		this.testState = testState;
		this.radio = radio;
		dead = false;
		
	}
	
	public boolean isDead() {
		return dead;
	}
	
	public double getRadio() {
		return radio;
	}
}
