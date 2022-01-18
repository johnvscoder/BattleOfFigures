package main;

public class Constants {
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	
	//Player
	public static final int POINTS = 4;
	public static final double DELTA_ANGLE = Math.PI / 45;
	public static final double RADIO = 20;
	public static final double ACC = 0.25;
	public static final double FRICTION = 0.1;
	public static double MAX_VEL = 7;
	public static final int SHOOT_VEL = 15;
	
	//Bullet
	public static final double BULLET_WIDTH = 10;
	public static final double BULLET_SPEED = 10;
	public static final double BULLET_RADIO = 0.5;
	
	//Enemy
	public static final double ENEMY_DELTA_ANGLE = 2 * Math.PI / 180;
	public static final double ENEMY_ACC = 0.1;
	public static final double ENEMY_MAX_VEL = 3;
	public static final int ENEMY_POINTS = 5;
	public static final double ENEMY_RADIO = 20;
	public static final int ENEMY_SHOOT_VEL = 15;
	
	//Wall
	public static final double WALL_L = RADIO * 2;
	
	//Button
	public static final double BUTTON_WIDTH = 100;
	public static final double BUTTON_HEIGHT = 25;
	
	//Room
	public static final int ROOM_WIDTH = 1400;
	public static final int ROOM_HEIGHT = 1400;
	
	public static final int LOADING_BAR_WIDTH = 500;
	public static final int LOADING_BAR_HEIGHT = 50;
	
	
	
	
}
