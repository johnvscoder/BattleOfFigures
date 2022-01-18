package objects;

import java.awt.Color;
import java.awt.Graphics;

import main.Constants;
import math.Vector2D;
import states.TestState;

public class Wall extends GameObject {
	private TestState testState;
	
	public Wall(double angle, Vector2D position, TestState testState) {
		super(angle, position);
		this.testState = testState;
	}

	@Override
	public void update() {
		
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect((int) (position.getX() - testState.getCamera().getPosition().getX()), (int) (position.getY() - testState.getCamera().getPosition().getY()),(int) Constants.WALL_L, (int) Constants.WALL_L);
	}

}
