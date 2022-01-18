package objects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import math.Vector2D;

public abstract class GameObject {
	protected Vector2D position;
	protected double angle;
	
	public GameObject(double angle, Vector2D position) {
		this.position = position;
		this.angle = angle;
	}
	
	public abstract void update();
	public abstract void draw(Graphics g);
	
	public Vector2D getPosition() {
		return position;
	}
	
	public double getAngle() {
		return angle;
	}
}
