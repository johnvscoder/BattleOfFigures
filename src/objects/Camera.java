package objects;

import java.awt.Graphics;

import main.Constants;
import math.Vector2D;
import states.TestState;

public class Camera extends GameMovingObject {
	public Camera(double angle, Vector2D position,
		Vector2D velocity, Vector2D acceleration, boolean dir, TestState testState,
		double radio) {
		super(angle, position, velocity, acceleration, dir, testState, radio);
		
		
	}

	@Override
	public void update() {
		position = testState.getPlayer().getPosition().add(new Vector2D(-Constants.WIDTH / 2, -Constants.HEIGHT / 2));
		
		if(position.getX() < 0)
			position.setX(0);
		if(position.getX() + Constants.WIDTH > Constants.ROOM_WIDTH + 16)
			position.setX(Constants.ROOM_WIDTH + 16 - Constants.WIDTH);
		if(position.getY() < 0)
			position.setY(0);
		if(position.getY() + Constants.HEIGHT > Constants.ROOM_HEIGHT + 39)
			position.setY(Constants.ROOM_HEIGHT + 39 - Constants.HEIGHT);
	}

	@Override
	public void draw(Graphics g) {
		
	}
}
