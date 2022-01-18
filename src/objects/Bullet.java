package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import main.Constants;
import math.Vector2D;
import states.TestState;

public class Bullet extends GameMovingObject {
	private GameMovingObject source;
	private double damage;
	private double width;
	private Color color;
	
	public Bullet(double angle, Vector2D position, Vector2D velocity, Vector2D acceleration,
			boolean dir, TestState testState, GameMovingObject source, 
			double radio, double damage, double width, Color color) {
		super(angle, position, velocity, acceleration, dir, testState, radio);
		this.source = source;
		this.damage = damage;
		this.width = width;
		this.color = color;
	}
	
	public double getWidth() {
		return width;
	}
	
	public double getDamage() {
		return damage;
	}
	
	public void destroy() {
		
		dead = true;
	}
	
	public GameMovingObject getSource() {
		return source;
	}
	
	@Override
	public void update() {
		position = position.add(velocity);
		
		collision();
	}
	
	public void collision() {
		if(position.getX() < 0 || position.getX() > Constants.ROOM_WIDTH ||
				position.getY() < 0 || position.getY() > Constants.ROOM_HEIGHT) {
			destroy();
			return;
		}
		
		
		ArrayList<GameMovingObject> objects = testState.getGameObjects();
		for(int i = 0; i < objects.size(); i++) {
			GameMovingObject e = objects.get(i);
			if(e instanceof Bullet)
				continue;
			if(source instanceof Player) {
				if(e instanceof Player) {
					continue;
				}
			}
			if(source instanceof Enemy) {
				if(e instanceof Enemy) {
					continue;
				}
			}
			Vector2D distance = position.add(
					new Vector2D(
							Math.cos(angle) * width,
							Math.sin(angle) * width));
			distance = e.getPosition().subtract(distance);
			
			if(distance.getMagnitude() < e.getRadio()) {
				destroy();
				return;
			}
		}
		ArrayList<Wall> walls = testState.getWalls();
		for(int i = 0; i < walls.size(); i++) {
			Wall e = walls.get(i);
			//+ Math.cos(angle) * Constants.BULLET_WIDTH
			//+ Math.sin(angle) * Constants.BULLET_WIDTH
			if((position.getX()+ Math.cos(angle) * width  > e.getPosition().getX()) &&
				(position.getX()+ Math.cos(angle) * width  < e.getPosition().getX() + Constants.WALL_L) &&
				(position.getY()+ Math.sin(angle) * width  < e.getPosition().getY() + Constants.WALL_L) &&
				(position.getY()+ Math.sin(angle) * width  > e.getPosition().getY())) {
				destroy();
				return;
			}
		}
	}

	@Override
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
		
		
		g2d.setColor(color);
		g2d.drawLine((int) (position.getX() - testState.getCamera().getPosition().getX()),
				(int) (position.getY() - testState.getCamera().getPosition().getY()),
				(int) (position.getX() + Math.cos(angle) * width - testState.getCamera().getPosition().getX()),
				(int) (position.getY() + Math.sin(angle) * width - testState.getCamera().getPosition().getY()));
	}
	
}
