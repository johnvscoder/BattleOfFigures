package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import graphics.Assets;
import graphics.Sound;
import input.Keyboard;
import main.Chronometer;
import main.Constants;
import math.Vector2D;
import states.TestState;

public class Player extends GameMovingObject {
	private Vector2D[] points;
	private Chronometer chronometer;
	private HealthBar healthBar;
	private int numPoints;
	private Color back;
	private Color line;
	private double maxVel;
	private int shootRate;
	private double shootSpeed;
	private double bulletDamage;
	private double angleDelta;
	private double bulletWidth;
	private Color bulletColor;
	
	private Sound laser;
	private Sound lost;
	
	//private boolean isCollision = false;
	public Player(double angle, Vector2D position, 
			Vector2D velocity, Vector2D acceleration, boolean dir, TestState testState, double radio, int numPoints,
			Color back, Color line, double maxVel, int shootRate, double shootSpeed, double bulletDamage,
			double angleDelta, double bulletWidth, Color bulletColor) {
		super(angle, position, velocity, acceleration, dir, testState, radio);
		this.numPoints = numPoints;
		this.back = back;
		this.line = line;
		this.maxVel = maxVel;
		this.shootRate = shootRate;
		this.shootSpeed = shootSpeed;
		this.bulletDamage = bulletDamage;
		this.angleDelta  = angleDelta;
		this.bulletWidth = bulletWidth;
		this.bulletColor = bulletColor;
		
		laser = new Sound(Assets.laserPlayer, 0);
		laser.changeVolume(-10f);
		
		lost = new Sound(Assets.lostPlayer, 0);
		lost.changeVolume(0f);
		
		points = new Vector2D[numPoints];
		
		for(int i = 0;  i < numPoints; i++) {
			points[i] = new Vector2D();
		}
		chronometer = new Chronometer(shootRate, false);
		healthBar = new HealthBar(0, new Vector2D(position.getX(), position.getY()),
				new Vector2D(), new Vector2D(), false, testState, 0, this, 100, Color.GREEN);
		
	}
	
	public double getAngle() {
		return angle;
	}
	
	@Override
	public void update() {
		
		if(Keyboard.left)
			angle -= angleDelta;
		if(Keyboard.right)
			angle += angleDelta;
		
		if(angle < - 2 * Math.PI || angle > 2 * Math.PI)
			angle = 0;
		
		if(Keyboard.up && !Keyboard.down) {
			acceleration = new Vector2D(Math.cos(angle) * Constants.ACC,
										Math.sin(angle) * Constants.ACC);
			maxVel = 7;
		}
		if(!Keyboard.up && Keyboard.down) {
			acceleration = new Vector2D(-Math.cos(angle) * Constants.ACC,
										-Math.sin(angle) * Constants.ACC);
			maxVel = 3.5;
		}
		if((!Keyboard.up && !Keyboard.down) || (Keyboard.up && Keyboard.down)) {
			if(velocity.getMagnitude() != 0) {
				acceleration = velocity.scale(-Constants.FRICTION);
			}
		}
		
		if(Keyboard.space && !chronometer.isRunning()) {
			testState.getGameObjects().add(0, new Bullet(
					angle, new Vector2D(position.getX(), position.getY()),
					new Vector2D(Math.cos(angle) * shootSpeed, Math.sin(angle) * shootSpeed),
					new Vector2D(), false, testState, this, Constants.BULLET_RADIO, bulletDamage, bulletWidth,
					bulletColor
					));
			chronometer.run();
			
			laser.play();
		}
		
		velocity = velocity.add(acceleration);
		velocity = velocity.limit(maxVel);
		position = position.add(velocity);
		
		for(int i = 0; i < points.length; i++) {
			points[i] = position.subtract(testState.getCamera().getPosition()).add(
					new Vector2D(+
							Math.cos(-Math.PI / numPoints + i * (2 * Math.PI / numPoints) + angle) * radio,
							Math.sin(-Math.PI / numPoints + i * (2 * Math.PI / numPoints) + angle) * radio));
		}
		
		collision();
		chronometer.update();
		
		healthBar.update();
		
		if(healthBar.getHealth() <= 0) {
			if(!dead) {
			dead = true;
			lost.play(); 
			}
			int count = testState.getGameObjects().size();
			boolean enemiesAre = false;
			for(int i = 0; i < count; i++ ) {
				GameMovingObject e = testState.getGameObjects().get(i);
				if(e instanceof Enemy) {
					Enemy e2 = (Enemy) e;
					if(!e2.isDead())
						enemiesAre = true;
				}
				
			}
			
			if(enemiesAre) {
				testState.setFinished(true);
				testState.setWin(false);
			}
				
			
			//testState.setFinished(true);
		}
		
	}
	
	public void collision() {
		ArrayList<GameMovingObject> objects = testState.getGameObjects();
		for(int i = 0; i < objects.size(); i++) {
			GameMovingObject e = objects.get(i);
			if(this == e)
				continue;
			if(e instanceof Enemy)
				continue;
			if(e instanceof Bullet) {
				Bullet e2 = (Bullet) e;
				GameMovingObject e3 = e2.getSource();
				if(e3 instanceof Player)
					continue;
				Vector2D distance = position.subtract(e.getPosition().add(new Vector2D(
						Math.cos(e.getAngle()) * e2.getWidth(),
						Math.sin(e.getAngle()) * e2.getWidth()
						
						)));
				if(distance.getMagnitude() < radio) {
					/*position = new Vector2D(Math.random() * Constants.WIDTH / 2 + 100,
							Math.random() * Constants.HEIGHT / 2 + 100);*/
					healthBar.setHealth(healthBar.getHealth() - e2.getDamage());
				}
			}
		}
		ArrayList<Wall> walls = testState.getWalls();
		for(int i = 0; i < walls.size(); i++) {
			Wall e = walls.get(i);
			if((position.getX() > e.getPosition().getX() - radio) &&
				(position.getX() < e.getPosition().getX() + Constants.WALL_L + radio) &&
				(position.getY() < e.getPosition().getY() + Constants.WALL_L + radio) &&
				(position.getY() > e.getPosition().getY() - radio)) {
				//isCollision = true;
				//break;
				
				if((position.getX() - velocity.getX() <= e.getPosition().getX() - radio) ||
					(position.getX() - velocity.getX() >= e.getPosition().getX() + Constants.WALL_L + radio)) {
					
					if(position.getX() - velocity.getX() <= e.getPosition().getX() - radio) {
						position.setX(e.getPosition().getX() - radio);
					}
					else {
						position.setX(e.getPosition().getX() + Constants.WALL_L + radio);
					}
					
					velocity.setX(-velocity.getX());
				}
				else {
					
					if(position.getY() - velocity.getY() <= e.getPosition().getY() - radio) {
						position.setY(e.getPosition().getY() - radio);
					}
					else {
						position.setY(e.getPosition().getY() + Constants.WALL_L + radio);
					}
					velocity.setY(-velocity.getY());
				}
			}
		}
	}

	@Override
	public void draw(Graphics g) {
		
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
		
		int[] xPoints = new int[numPoints];
		int[] yPoints = new int[numPoints];
		
		for(int i = 0; i < numPoints; i++) {
			xPoints[i] = (int) points[i].getX();
			yPoints[i] = (int) points[i].getY();
		}
		
		
		
		g2d.setColor(back);
		g2d.fillPolygon(xPoints, yPoints, numPoints);
		
		g2d.setColor(line);
		g2d.drawLine((int) (position.getX() - testState.getCamera().getPosition().getX()),
				(int) (position.getY() - testState.getCamera().getPosition().getY()),
				(int) (position.getX() + Math.cos(angle) * (Math.cos(Math.PI / numPoints) * radio ) - testState.getCamera().getPosition().getX()),
				(int) (position.getY() + Math.sin(angle) * (Math.cos(Math.PI / numPoints) * radio) - testState.getCamera().getPosition().getY()));
		
		
		
		healthBar.draw(g);
		
	}
	
}
