package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.sound.sampled.Clip;

import graphics.Loader;
import graphics.Sound;
import main.Chronometer;
import main.Constants;
import math.Vector2D;
import states.TestState;

public class Enemy extends GameMovingObject {
	private Vector2D[] points;
	private Chronometer chronometer;
	private HealthBar healthBar;
	
	private Color figure;
	private Color line;
	private double maxVel;
	private int shootRate;
	private double shootSpeed;
	private double bulletDamage;
	private final int numPoints;
	private double angleDelta;
	private double bulletWidth;
	private Color bulletColor;
	
	private Sound laser;
	private Sound lost;
	
	public Enemy(double angle, Vector2D position, 
			Vector2D velocity, Vector2D acceleration,
			boolean dir, TestState testState, double radio,
			Color figure, Color line, double maxVel, int shootRate, double shootSpeed,
			double bulletDamage, int numPoints, double angleDelta,
			double bulletWidth, Color bulletColor, Clip laserE, Clip lostE) {
		super(angle, position, velocity, acceleration, dir, testState, radio);
		
		this.figure = figure;
		this.line = line;
		this.maxVel = maxVel;
		this.shootRate = shootRate;
		this.shootSpeed = shootSpeed;
		this.bulletDamage = bulletDamage;
		this.numPoints = numPoints;
		this.angleDelta = angleDelta;
		
		this.bulletWidth = bulletWidth;
		this.bulletColor = bulletColor;
		
		laser = new Sound(laserE, 0);
		laser.changeVolume(-10f);
		
		lost = new Sound(lostE, 0);
		lost.changeVolume(0f);
		
		points = new Vector2D[numPoints];
		for(int i = 0;  i < numPoints; i++) {
			points[i] = new Vector2D();
		}
		
		chronometer = new Chronometer(shootRate, false);
		healthBar = new HealthBar(0, new Vector2D(position.getX(), position.getY()),
				new Vector2D(), new Vector2D(), false, testState, 0, this, 100, Color.RED);
	}
	
	@Override
	public void update() {
		
		/* No borres este codigo, calcula el angulo para apuntar al jugador
		Vector2D distance = testState.getPlayer().getPosition().subtract(position);
		
		if(distance.getMagnitude() != 0) {
			angle = Math.acos(distance.getX() / distance.getMagnitude());
			if(testState.getPlayer().getPosition().getY() < position.getY())
				angle += 2 * (Math.PI - angle);
		}*/
		
		
		Vector2D distance = testState.getPlayer().getPosition().subtract(position);
		
		if(distance.getMagnitude() != 0) {
			double distance_angle = Math.acos(distance.getX() / distance.getMagnitude());
			if(testState.getPlayer().getPosition().getY() < position.getY())
				distance_angle += 2 * (Math.PI - distance_angle);
			//if(Math.abs(angle - distance_angle) >  Constants.ENEMY_DELTA_ANGLE) {
			
			Vector2D enemyAngle = new Vector2D(Math.cos(angle), Math.sin(angle));
			Vector2D distanceAngle = new Vector2D(Math.cos(distance_angle), Math.sin(distance_angle));
			
			double angleFinal = Math.acos(
					(enemyAngle.getX() * distanceAngle.getX() 
					+ enemyAngle.getY() * distanceAngle.getY()) /
					(enemyAngle.getMagnitude() * distanceAngle.getMagnitude())
					);
			//if(angle < distance_angle - Constants.ENEMY_DELTA_ANGLE ||
			//		angle > distance_angle + Constants.ENEMY_DELTA_ANGLE) {
			
			
			
			
			
			if(angleFinal > angleDelta) {
				
				
				
				Vector2D perpen = new Vector2D(
						Math.cos(angle + Math.PI / 2),
						Math.sin(angle + Math.PI / 2));
				
				double angleFinal2 = Math.acos(
						(perpen.getX() * distanceAngle.getX() 
								+ perpen.getY() * distanceAngle.getY()) /
								(perpen.getMagnitude() * distanceAngle.getMagnitude())
								);
				
				if(angleFinal2 <= Math.PI / 2)
					angle += angleDelta;
				else
					angle -= angleDelta;
			}
			else {
				
				angle = distance_angle;
				if(!chronometer.isRunning()) {
					testState.getGameObjects().add(0, new Bullet(
						angle, new Vector2D(position.getX(), position.getY()),
						new Vector2D(Math.cos(angle) * shootSpeed, Math.sin(angle) * shootSpeed),
						new Vector2D(), false, testState, this, Constants.BULLET_RADIO, bulletDamage, bulletWidth,
						bulletColor
						));
					laser.play();
				}
				chronometer.run();
				
			}
			
			/*Vector2D eAngle = new Vector2D(Math.cos(angle), Math.sin(angle));
			//Y utilizamos distanceAngle
			
			angleFinal = Math.acos(
					(eAngle.getX() * distanceAngle.getX() 
					+ eAngle.getY() * distanceAngle.getY()) /
					(eAngle.getMagnitude() * distanceAngle.getMagnitude())
					);*/
			
			
			
		}
		
		
		
		
		acceleration = new Vector2D(
				Math.cos(angle) * Constants.ENEMY_ACC, Math.sin(angle) * Constants.ENEMY_ACC);
		
		velocity = velocity.add(acceleration);
		velocity = velocity.limit(maxVel);
		
		position = position.add(velocity);
		
		for(int i = 0; i < points.length; i++) {
			/*points[i] = position.add(
					new Vector2D(+
							Math.cos(Math.PI / 2 - Math.PI / Constants.ENEMY_POINTS + i * (2 * Math.PI / Constants.ENEMY_POINTS) + angle) * Constants.ENEMY_RADIO,
							Math.sin(Math.PI / 2 - Math.PI / Constants.ENEMY_POINTS + i * (2 * Math.PI / Constants.ENEMY_POINTS) + angle) * Constants.ENEMY_RADIO));*/
			points[i] = position.subtract(testState.getCamera().getPosition()).add(
					new Vector2D(+
							Math.cos(-Math.PI / numPoints + i * (2 * Math.PI / numPoints) + angle) * radio,
							Math.sin(-Math.PI / numPoints + i * (2 * Math.PI / numPoints) + angle) * radio));
		}
		
		collision();
		chronometer.update();
		
		healthBar.update();
		
		if(healthBar.getHealth() <= 0) {
			dead = true;
			lost.play();
			if(!testState.getPlayer().isDead()) {
				int count = testState.getGameObjects().size();
				int countEnemies = 0;
				for(int i = 0; i < count; i++ ) {
					GameMovingObject e = testState.getGameObjects().get(i);
					if(e instanceof Enemy) {
						Enemy e2 = (Enemy) e;
						if(!e2.isDead()) {
							countEnemies++;
						}
					}
				}
				if(countEnemies == 0) {
					testState.setFinished(true);
					testState.setWin(true);
				}
			}
		}
	}
	
	public void collision() {
		ArrayList<GameMovingObject> objects = testState.getGameObjects();
		for(int i = 0; i < objects.size(); i++) {
			GameMovingObject e = objects.get(i);
			if(this == e)
				continue;
			if(e instanceof Player)
				continue;
			if(e instanceof Bullet) {
				Bullet e2 = (Bullet) e;
				GameMovingObject e3 = e2.getSource();
				if(e3 instanceof Enemy)
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
		
		//g.setColor(new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255)));
		g2d.setColor(figure);
		g2d.fillPolygon(xPoints, yPoints, numPoints);
		
		g2d.setColor(line);
		g2d.drawLine((int) (position.getX() - testState.getCamera().getPosition().getX()),
				(int) (position.getY() - testState.getCamera().getPosition().getY()),
				(int) (position.getX() + Math.cos(angle) * (Math.cos(Math.PI / numPoints) * radio) - testState.getCamera().getPosition().getX()), 
				(int) (position.getY() + Math.sin(angle) * (Math.cos(Math.PI / numPoints) * radio) - testState.getCamera().getPosition().getY()));
		
		healthBar.draw(g);
		
	}

}
