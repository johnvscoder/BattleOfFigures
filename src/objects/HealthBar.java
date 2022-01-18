package objects;

import java.awt.Color;
import java.awt.Graphics;

import math.Vector2D;
import states.TestState;

public class HealthBar extends GameMovingObject {
	private GameMovingObject object;
	private double health;
	private Color color;
	public HealthBar(double angle, Vector2D position, Vector2D velocity, Vector2D acceleration, boolean dir,
			TestState testState, double radio, GameMovingObject object, double health, Color color) {
		super(angle, position, velocity, acceleration, dir, testState, radio);
		this.object = object;
		this.health = health;
		this.color = color;
	}
	
	

	public double getHealth() {
		return health;
	}



	public void setHealth(double health) {
		this.health = health;
	}



	@Override
	public void update() {
		position = new Vector2D(object.getPosition().getX(), object.getPosition().getY());
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillRect((int) (position.getX() - (health / 2) / 2 - testState.getCamera().getPosition().getX()), (int) (position.getY() - object.getRadio() - 15  - testState.getCamera().getPosition().getY()), (int) health / 2, 5);
	}

}
