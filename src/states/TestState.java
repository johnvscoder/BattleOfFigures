package states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import graphics.Assets;
import graphics.Text;
import input.Keyboard;
import input.Mouse;
import main.Chronometer;
import main.Constants;
import math.Vector2D;
import objects.Camera;
import objects.Enemy;
import objects.GameMovingObject;
import objects.Player;
import objects.Wall;

public class TestState extends State {
	private ArrayList<GameMovingObject> gameObjects;
	private Player player;
	private ArrayList<Wall> walls;
	private int level;
	private boolean finished;
	private Chronometer end;
	private boolean endBegin;
	private boolean win;
	
	
	private Camera camera;
	
	public TestState(int level) {
		this.level = level;
		finished = false;
		endBegin = false;
		win = false;
		end = new Chronometer(180, false);
		/*player = new Player(
				0, new Vector2D(80, 80), new Vector2D(), new Vector2D(),
				false, this, 20, 4, Color.YELLOW, Color.GRAY, 7, 15, 10, 10, Math.PI / 36, 10,
				Color.WHITE
				);*/
		player = null;
		gameObjects = new ArrayList<GameMovingObject>();
		
		//gameObjects.add(player);
		
		/*gameObjects.add(new Enemy(
				0, new Vector2D(7 * 40, 7 * 40),
				new Vector2D(), new Vector2D(), false, this, 20,
				Color.GREEN, Color.BLACK, 5, 15, 10, 10, 50, Math.PI / 45, 10,
				Color.RED
				));*/
		
		walls = new ArrayList<Wall>();
		
		/*for(int i = 0; i < 5; i++)
			walls.add(new Wall(0, new Vector2D(300 + i * Constants.WALL_L, 300), this));
		for(int i = 0; i < 4; i++)
			walls.add(new Wall(0, new Vector2D(300 + 4 * Constants.WALL_L, 300 + (i + 1) * Constants.WALL_L), this));*/
		
		
		for(int i = 0; i < 35; i++)
			walls.add(new Wall(0, new Vector2D(i * Constants.WALL_L, 0), this));
		for(int i = 0; i < 35; i++)
			walls.add(new Wall(0, new Vector2D(i * Constants.WALL_L, 34 * Constants.WALL_L), this));
		
		for(int i = 1; i < 34; i++)
			walls.add(new Wall(0, new Vector2D(0, i * Constants.WALL_L), this));
		for(int i = 1; i < 34; i++)
			walls.add(new Wall(0, new Vector2D(34 * Constants.WALL_L, i * Constants.WALL_L), this));
		
		/*for(int i = 5; i < 344; i++) {
			for(int j = 5; j < 344; j++) {
				if((i % 5 == 0) && (j % 5 == 0)) {
					walls.add(new Wall(0, new Vector2D(i * Constants.WALL_L, j * Constants.WALL_L), this));
				}
			}
		}*/
		
		camera = new Camera(
				0, new Vector2D(0, 0), new Vector2D(), new Vector2D(), false, this, 0
				);
		
		
		createLevel();
			
		
		
		
	}
	
	public void createLevel() {
		switch(level) {
			case 0:
				walls.add(new Wall(0, new Vector2D(10 * Constants.WALL_L, 10 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(20 * Constants.WALL_L, 10 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(30 * Constants.WALL_L, 10 * Constants.WALL_L), this));
				
				walls.add(new Wall(0, new Vector2D(10 * Constants.WALL_L, 20 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(20 * Constants.WALL_L, 20 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(30 * Constants.WALL_L, 20 * Constants.WALL_L), this));
				
				walls.add(new Wall(0, new Vector2D(10 * Constants.WALL_L, 30 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(20 * Constants.WALL_L, 30 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(30 * Constants.WALL_L, 30 * Constants.WALL_L), this));
				
				player = new Player(
						0, new Vector2D(2 * Constants.WALL_L, 2 * Constants.WALL_L), new Vector2D(), new Vector2D(),
						false, this, 20, 4, Color.YELLOW, Color.DARK_GRAY, 7, 15, 10, 10, Math.PI / 36, 10,
						Color.WHITE
						);
				gameObjects.add(player);
				
				gameObjects.add(new Enemy(
						0, new Vector2D(7 * Constants.WALL_L, 7 * Constants.WALL_L),
						new Vector2D(), new Vector2D(), false, this, 20,
						Color.GREEN, Color.BLUE, 5, 15, 10, 10, 4, Math.PI / 45, 10,
						Color.RED, Assets.laserEnemy1, Assets.lostEnemy1
						));
				
				break;
			case 1:
				walls.add(new Wall(0, new Vector2D(12 * Constants.WALL_L, 12 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(13 * Constants.WALL_L, 12 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(12 * Constants.WALL_L, 13 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(13 * Constants.WALL_L, 13 * Constants.WALL_L), this));
				
				walls.add(new Wall(0, new Vector2D(23 * Constants.WALL_L, 12 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(24 * Constants.WALL_L, 12 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(23 * Constants.WALL_L, 13 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(24 * Constants.WALL_L, 13 * Constants.WALL_L), this));
				
				walls.add(new Wall(0, new Vector2D(12 * Constants.WALL_L, 23 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(13 * Constants.WALL_L, 23 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(12 * Constants.WALL_L, 24 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(13 * Constants.WALL_L, 24 * Constants.WALL_L), this));
				
				walls.add(new Wall(0, new Vector2D(23 * Constants.WALL_L, 23 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(24 * Constants.WALL_L, 23 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(23 * Constants.WALL_L, 24 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(24 * Constants.WALL_L, 24 * Constants.WALL_L), this));
				
				player = new Player(
						0, new Vector2D(18 * Constants.WALL_L, 18 * Constants.WALL_L), new Vector2D(), new Vector2D(),
						false, this, 20, 4, Color.YELLOW, Color.DARK_GRAY, 7, 15, 10, 10, Math.PI / 36, 10,
						Color.WHITE
						);
				gameObjects.add(player);
				
				gameObjects.add(new Enemy(
						Math.PI, new Vector2D(7 * Constants.WALL_L, 13 * Constants.WALL_L),
						new Vector2D(), new Vector2D(), false, this, 20,
						Color.GREEN, Color.BLUE, 5, 15, 10, 10, 4, Math.PI / 45, 10,
						Color.RED, Assets.laserEnemy1, Assets.lostEnemy1
						));
				
				gameObjects.add(new Enemy(
						Math.PI, new Vector2D(7 * Constants.WALL_L, 23 * Constants.WALL_L),
						new Vector2D(), new Vector2D(), false, this, 20,
						Color.GREEN, Color.BLUE, 5, 15, 10, 10, 4, Math.PI / 45, 10,
						Color.RED, Assets.laserEnemy2, Assets.lostEnemy2
						));
				
				gameObjects.add(new Enemy(
						Math.PI, new Vector2D(29 * Constants.WALL_L, 13 * Constants.WALL_L),
						new Vector2D(), new Vector2D(), false, this, 20,
						Color.RED, Color.BLACK, 5, 15, 10, 5, 3, Math.PI / 45, 10,
						Color.RED, Assets.laserEnemy3, Assets.lostEnemy3
						));
				gameObjects.add(new Enemy(
						Math.PI, new Vector2D(29 * Constants.WALL_L, 23 * Constants.WALL_L),
						new Vector2D(), new Vector2D(), false, this, 20,
						Color.RED, Color.BLACK, 5, 15, 10, 5, 3, Math.PI / 45, 10,
						Color.RED, Assets.laserEnemy4, Assets.lostEnemy4
						));
				break;
			case 2:
				walls.add(new Wall(0, new Vector2D(12 * Constants.WALL_L, 12 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(13 * Constants.WALL_L, 12 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(12 * Constants.WALL_L, 13 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(13 * Constants.WALL_L, 13 * Constants.WALL_L), this));
				
				walls.add(new Wall(0, new Vector2D(23 * Constants.WALL_L, 12 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(24 * Constants.WALL_L, 12 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(23 * Constants.WALL_L, 13 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(24 * Constants.WALL_L, 13 * Constants.WALL_L), this));
				
				walls.add(new Wall(0, new Vector2D(12 * Constants.WALL_L, 23 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(13 * Constants.WALL_L, 23 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(12 * Constants.WALL_L, 24 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(13 * Constants.WALL_L, 24 * Constants.WALL_L), this));
				
				walls.add(new Wall(0, new Vector2D(23 * Constants.WALL_L, 23 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(24 * Constants.WALL_L, 23 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(23 * Constants.WALL_L, 24 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(24 * Constants.WALL_L, 24 * Constants.WALL_L), this));
				
				
				walls.add(new Wall(0, new Vector2D(9 * Constants.WALL_L, 9 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(10 * Constants.WALL_L, 9 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(9 * Constants.WALL_L, 10 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(10 * Constants.WALL_L, 10 * Constants.WALL_L), this));
				
				walls.add(new Wall(0, new Vector2D(26 * Constants.WALL_L, 9 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(27 * Constants.WALL_L, 9 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(26 * Constants.WALL_L, 10 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(27 * Constants.WALL_L, 10 * Constants.WALL_L), this));
				
				walls.add(new Wall(0, new Vector2D(9 * Constants.WALL_L, 26 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(10 * Constants.WALL_L, 26 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(9 * Constants.WALL_L, 27 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(10 * Constants.WALL_L, 27 * Constants.WALL_L), this));
				
				walls.add(new Wall(0, new Vector2D(26 * Constants.WALL_L, 26 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(27 * Constants.WALL_L, 26 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(26 * Constants.WALL_L, 27 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(27 * Constants.WALL_L, 27 * Constants.WALL_L), this));
				
				player = new Player(
						0, new Vector2D(3 * Constants.WALL_L, 3 * Constants.WALL_L), new Vector2D(), new Vector2D(),
						false, this, 20, 4, Color.YELLOW, Color.DARK_GRAY, 7, 15, 10, 10, Math.PI / 36, 10,
						Color.WHITE
						);
				
				gameObjects.add(player);
				
				gameObjects.add(new Enemy(
						Math.PI, new Vector2D(32 * Constants.WALL_L, 5 * Constants.WALL_L),
						new Vector2D(), new Vector2D(), false, this, 20,
						Color.ORANGE, Color.RED, 5, 15, 10, 20, 5, Math.PI / 45, 10,
						Color.RED, Assets.laserEnemy1, Assets.lostEnemy1
						));
				
				gameObjects.add(new Enemy(
						Math.PI, new Vector2D(32 * Constants.WALL_L, 31 * Constants.WALL_L),
						new Vector2D(), new Vector2D(), false, this, 20,
						Color.ORANGE, Color.RED, 5, 15, 10, 20, 5, Math.PI / 45, 10,
						Color.RED, Assets.laserEnemy2, Assets.lostEnemy2
						));
				
				gameObjects.add(new Enemy(
						Math.PI, new Vector2D(24 * Constants.WALL_L, 18 * Constants.WALL_L),
						new Vector2D(), new Vector2D(), false, this, 20,
						Color.MAGENTA, Color.BLACK, 5, 15, 10, 15, 4, Math.PI / 45, 10,
						Color.RED, Assets.laserEnemy3, Assets.lostEnemy3
						));
				
				gameObjects.add(new Enemy(
						Math.PI, new Vector2D(12 * Constants.WALL_L, 18 * Constants.WALL_L),
						new Vector2D(), new Vector2D(), false, this, 20,
						Color.GREEN, Color.RED, 5, 15, 10, 5, 3, Math.PI / 45, 10,
						Color.RED, Assets.laserEnemy4, Assets.lostEnemy4
						));
				
				gameObjects.add(new Enemy(
						Math.PI, new Vector2D(6 * Constants.WALL_L, 31 * Constants.WALL_L),
						new Vector2D(), new Vector2D(), false, this, 20,
						Color.GREEN, Color.RED, 5, 15, 10, 5, 3, Math.PI / 45, 10,
						Color.RED, Assets.laserEnemy5, Assets.lostEnemy5
						));
				
				break;
			case 3:
				walls.add(new Wall(0, new Vector2D(7 * Constants.WALL_L, 7 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(8 * Constants.WALL_L, 7 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(9 * Constants.WALL_L, 7 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(7 * Constants.WALL_L, 8 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(7 * Constants.WALL_L, 9 * Constants.WALL_L), this));
				
				walls.add(new Wall(0, new Vector2D(29 * Constants.WALL_L, 7 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(28 * Constants.WALL_L, 7 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(27 * Constants.WALL_L, 7 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(29 * Constants.WALL_L, 8 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(29 * Constants.WALL_L, 9 * Constants.WALL_L), this));
				
				walls.add(new Wall(0, new Vector2D(29 * Constants.WALL_L, 29 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(28 * Constants.WALL_L, 29 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(27 * Constants.WALL_L, 29 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(29 * Constants.WALL_L, 28 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(29 * Constants.WALL_L, 27 * Constants.WALL_L), this));
				
				walls.add(new Wall(0, new Vector2D(7 * Constants.WALL_L, 29 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(8 * Constants.WALL_L, 29 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(9 * Constants.WALL_L, 29 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(7 * Constants.WALL_L, 28 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(7 * Constants.WALL_L, 27 * Constants.WALL_L), this));
				
				
				walls.add(new Wall(0, new Vector2D(11 * Constants.WALL_L, 11 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(12 * Constants.WALL_L, 11 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(13 * Constants.WALL_L, 11 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(11 * Constants.WALL_L, 12 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(11 * Constants.WALL_L, 13 * Constants.WALL_L), this));
				
				walls.add(new Wall(0, new Vector2D(25 * Constants.WALL_L, 11 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(24 * Constants.WALL_L, 11 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(23 * Constants.WALL_L, 11 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(25 * Constants.WALL_L, 12 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(25 * Constants.WALL_L, 13 * Constants.WALL_L), this));
				
				walls.add(new Wall(0, new Vector2D(25 * Constants.WALL_L, 25 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(24 * Constants.WALL_L, 25 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(23 * Constants.WALL_L, 25 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(25 * Constants.WALL_L, 24 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(25 * Constants.WALL_L, 23 * Constants.WALL_L), this));
				
				walls.add(new Wall(0, new Vector2D(11 * Constants.WALL_L, 25 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(12 * Constants.WALL_L, 25 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(13 * Constants.WALL_L, 25 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(11 * Constants.WALL_L, 24 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(11 * Constants.WALL_L, 23 * Constants.WALL_L), this));
				
				walls.add(new Wall(0, new Vector2D(18 * Constants.WALL_L, 18 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(17 * Constants.WALL_L, 18 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(19 * Constants.WALL_L, 18 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(18 * Constants.WALL_L, 17 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(18 * Constants.WALL_L, 19 * Constants.WALL_L), this));
				
				player = new Player(
						Math.PI, new Vector2D(32 * Constants.WALL_L, 32 * Constants.WALL_L), new Vector2D(), new Vector2D(),
						false, this, 20, 4, Color.YELLOW, Color.DARK_GRAY, 7, 15, 10, 10, Math.PI / 36, 10,
						Color.WHITE
						);
				gameObjects.add(player);
				
				gameObjects.add(new Enemy(
						Math.PI, new Vector2D(7 * Constants.WALL_L, 13 * Constants.WALL_L),
						new Vector2D(), new Vector2D(), false, this, 20,
						Color.RED, Color.WHITE, 5, 15, 10, 10, 4, Math.PI / 30, 10,
						Color.RED, Assets.laserEnemy1, Assets.lostEnemy1
						));
				
				gameObjects.add(new Enemy(
						Math.PI, new Vector2D(7 * Constants.WALL_L, 23 * Constants.WALL_L),
						new Vector2D(), new Vector2D(), false, this, 20,
						Color.RED, Color.WHITE, 5, 15, 10, 10, 4, Math.PI / 30, 10,
						Color.RED, Assets.laserEnemy2, Assets.lostEnemy2
						));
				
				gameObjects.add(new Enemy(
						Math.PI, new Vector2D(29 * Constants.WALL_L, 13 * Constants.WALL_L),
						new Vector2D(), new Vector2D(), false, this, 20,
						Color.ORANGE, Color.RED, 5, 15, 10, 25, 6, Math.PI / 45, 10,
						Color.RED, Assets.laserEnemy3, Assets.lostEnemy3
						));
				
				gameObjects.add(new Enemy(
						Math.PI, new Vector2D(29 * Constants.WALL_L, 23 * Constants.WALL_L),
						new Vector2D(), new Vector2D(), false, this, 20,
						Color.ORANGE, Color.RED, 5, 15, 10, 25, 6, Math.PI / 45, 10,
						Color.RED, Assets.laserEnemy4, Assets.lostEnemy4
						));
				
				
				break;
			case 4:
				
				
				
				for(int i = 0; i < 11; i++) {
					walls.add(new Wall(0, new Vector2D(17 * Constants.WALL_L, (i + 4) * Constants.WALL_L), this));
					walls.add(new Wall(0, new Vector2D(17 * Constants.WALL_L, (i + 20) * Constants.WALL_L), this));
					walls.add(new Wall(0, new Vector2D((i + 4) * Constants.WALL_L, 17 * Constants.WALL_L), this));
					walls.add(new Wall(0, new Vector2D((i + 20) * Constants.WALL_L, 17 * Constants.WALL_L), this));
				}
				
				walls.add(new Wall(0, new Vector2D(10 * Constants.WALL_L, 10 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(9 * Constants.WALL_L, 9 * Constants.WALL_L), this));
				
				walls.add(new Wall(0, new Vector2D(24 * Constants.WALL_L, 10 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(25 * Constants.WALL_L, 9 * Constants.WALL_L), this));
				
				walls.add(new Wall(0, new Vector2D(24 * Constants.WALL_L, 24 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(25 * Constants.WALL_L, 25 * Constants.WALL_L), this));
				
				walls.add(new Wall(0, new Vector2D(10 * Constants.WALL_L, 24 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(9 * Constants.WALL_L, 25 * Constants.WALL_L), this));
				
				player = new Player(
						0, new Vector2D(3 * Constants.WALL_L + 20, 3 * Constants.WALL_L + 20), new Vector2D(), new Vector2D(),
						false, this, 20, 4, Color.YELLOW, Color.DARK_GRAY, 7, 15, 10, 10, Math.PI / 36, 10,
						Color.WHITE
						);
				gameObjects.add(player);
				
				gameObjects.add(new Enemy(
						0, new Vector2D(12 * Constants.WALL_L, 12 * Constants.WALL_L),
						new Vector2D(), new Vector2D(), false, this, 20,
						Color.GREEN, Color.BLUE, 5, 10, 10, 20, 4, Math.PI / 45, 10,
						Color.RED, Assets.laserEnemy1, Assets.lostEnemy1
						));
				
				gameObjects.add(new Enemy(
						0, new Vector2D(22 * Constants.WALL_L, 12 * Constants.WALL_L),
						new Vector2D(), new Vector2D(), false, this, 20,
						Color.GREEN, Color.BLUE, 5, 10, 10, 20, 4, Math.PI / 45, 10,
						Color.RED, Assets.laserEnemy2, Assets.lostEnemy2
						));
				
				gameObjects.add(new Enemy(
						0, new Vector2D(12 * Constants.WALL_L, 22 * Constants.WALL_L),
						new Vector2D(), new Vector2D(), false, this, 10,
						Color.RED, Color.BLUE, 5, 10, 10, 30, 3, Math.PI / 45, 10,
						Color.RED, Assets.laserEnemy3, Assets.lostEnemy3
						));
				
				gameObjects.add(new Enemy(
						0, new Vector2D(22 * Constants.WALL_L, 22 * Constants.WALL_L),
						new Vector2D(), new Vector2D(), false, this, 10,
						Color.RED, Color.BLUE, 5, 10, 10, 30, 3, Math.PI / 45, 10,
						Color.RED, Assets.laserEnemy4, Assets.lostEnemy4
						));
				
				
				break;
			case 5:
				for(int i = 0; i < 4; i++)
					walls.add(new Wall(0, new Vector2D(6 * Constants.WALL_L, (i + 6) * Constants.WALL_L), this));
				
				for(int i = 0; i < 4; i++)
					walls.add(new Wall(0, new Vector2D(6 * Constants.WALL_L, (i + 27) * Constants.WALL_L), this));
				
				for(int i = 0; i < 4; i++)
					walls.add(new Wall(0, new Vector2D(30 * Constants.WALL_L, (i + 6) * Constants.WALL_L), this));
				
				for(int i = 0; i < 4; i++)
					walls.add(new Wall(0, new Vector2D(30 * Constants.WALL_L, (i + 27) * Constants.WALL_L), this));
				
				
				
				for(int i = 0; i < 15; i++)
					walls.add(new Wall(0, new Vector2D((11 + i) * Constants.WALL_L, 8 * Constants.WALL_L), this));
				
				for(int i = 0; i < 15; i++)
					walls.add(new Wall(0, new Vector2D((11 + i) * Constants.WALL_L, 28 * Constants.WALL_L), this));
				
				
				for(int i = 0; i < 7; i++)
					walls.add(new Wall(0, new Vector2D(18 * Constants.WALL_L, (i + 9) * Constants.WALL_L), this));
				
				for(int i = 0; i < 7; i++)
					walls.add(new Wall(0, new Vector2D(18 * Constants.WALL_L, (i + 21) * Constants.WALL_L), this));
				
				player = new Player(
						0, new Vector2D(18 * Constants.WALL_L + 20, 18 * Constants.WALL_L + 20), new Vector2D(), new Vector2D(),
						false, this, 20, 4, Color.YELLOW, Color.DARK_GRAY, 7, 15, 10, 10, Math.PI / 36, 10,
						Color.WHITE
						);
				gameObjects.add(player);
				
				
				gameObjects.add(new Enemy(
						0, new Vector2D(3 * Constants.WALL_L, 3 * Constants.WALL_L),
						new Vector2D(), new Vector2D(), false, this, 20,
						Color.CYAN, Color.RED, 5, 10, 10, 25, 4, Math.PI / 45, 10,
						Color.RED, Assets.laserEnemy1, Assets.lostEnemy1
						));
				
				gameObjects.add(new Enemy(
						0, new Vector2D(3 * Constants.WALL_L, 33 * Constants.WALL_L),
						new Vector2D(), new Vector2D(), false, this, 20,
						Color.CYAN, Color.RED, 5, 10, 10, 25, 4, Math.PI / 45, 10,
						Color.RED, Assets.laserEnemy2, Assets.lostEnemy2
						));
				
				gameObjects.add(new Enemy(
						Math.PI, new Vector2D(33 * Constants.WALL_L, 3 * Constants.WALL_L),
						new Vector2D(), new Vector2D(), false, this, 20,
						Color.WHITE, Color.DARK_GRAY, 5, 15, 10, 45, 5, Math.PI / 45, 10,
						Color.YELLOW, Assets.laserEnemy3, Assets.lostEnemy3
						));
				
				gameObjects.add(new Enemy(
						Math.PI, new Vector2D(33 * Constants.WALL_L, 33 * Constants.WALL_L),
						new Vector2D(), new Vector2D(), false, this, 20,
						Color.WHITE, Color.DARK_GRAY, 5, 15, 10, 45, 5, Math.PI / 45, 10,
						Color.YELLOW, Assets.laserEnemy4, Assets.lostEnemy4
						));
				
				gameObjects.add(new Enemy(
						Math.PI * 1.5, new Vector2D(18 * Constants.WALL_L, 6 * Constants.WALL_L),
						new Vector2D(), new Vector2D(), false, this, 20,
						Color.RED, Color.GREEN, 5, 10, 10, 10, 7, Math.PI / 45, 10,
						Color.WHITE, Assets.laserEnemy5, Assets.lostEnemy5
						));
				
				gameObjects.add(new Enemy(
						Math.PI * 0.5, new Vector2D(18 * Constants.WALL_L, 30 * Constants.WALL_L),
						new Vector2D(), new Vector2D(), false, this, 20,
						Color.RED, Color.GREEN, 5, 10, 10, 10, 7, Math.PI / 45, 10,
						Color.WHITE, Assets.laserEnemy6, Assets.lostEnemy6
						));
				
				break;
			case 6:
				
				for(int i = 0; i < 5; i++) {
					for(int j = 0; j < 5; j++) {
						walls.add(new Wall(0, new Vector2D((5 + 6 * i) * Constants.WALL_L, (5 + 6 * j) * Constants.WALL_L), this));
						
						walls.add(new Wall(0, new Vector2D((5 + 6 * i) * Constants.WALL_L, (4 + 6 * j) * Constants.WALL_L), this));
						walls.add(new Wall(0, new Vector2D((5 + 6 * i) * Constants.WALL_L, (6 + 6 * j) * Constants.WALL_L), this));
						walls.add(new Wall(0, new Vector2D((4 + 6 * i) * Constants.WALL_L, (5 + 6 * j) * Constants.WALL_L), this));
						walls.add(new Wall(0, new Vector2D((6 + 6 * i) * Constants.WALL_L, (5 + 6 * j) * Constants.WALL_L), this));
					}
				}
				
				player = new Player(
						Math.PI, new Vector2D(32 * Constants.WALL_L + 20, 32 * Constants.WALL_L + 20), new Vector2D(), new Vector2D(),
						false, this, 20, 4, Color.YELLOW, Color.DARK_GRAY, 7, 15, 10, 10, Math.PI / 36, 10,
						Color.WHITE
						);
				gameObjects.add(player);
				
				gameObjects.add(new Enemy(
						0, new Vector2D(2 * Constants.WALL_L, 2 * Constants.WALL_L),
						new Vector2D(), new Vector2D(), false, this, 20,
						Color.RED, Color.BLUE, 5, 15, 10, 10, 4, Math.PI / 45, 20,
						Color.RED, Assets.laserEnemy1, Assets.lostEnemy1
						));
				
				gameObjects.add(new Enemy(
						0, new Vector2D(32 * Constants.WALL_L, 2 * Constants.WALL_L),
						new Vector2D(), new Vector2D(), false, this, 20,
						Color.GREEN, Color.RED, 5, 15, 10, 10, 4, Math.PI / 45, 20,
						Color.GREEN, Assets.laserEnemy2, Assets.lostEnemy2
						));
				
				gameObjects.add(new Enemy(
						0, new Vector2D(2 * Constants.WALL_L, 32 * Constants.WALL_L),
						new Vector2D(), new Vector2D(), false, this, 20,
						Color.YELLOW, Color.RED, 5, 15, 10, 10, 4, Math.PI / 45, 20,
						Color.YELLOW, Assets.laserEnemy3, Assets.lostEnemy3
						));
				
				gameObjects.add(new Enemy(
						0, new Vector2D(14 * Constants.WALL_L, 14 * Constants.WALL_L),
						new Vector2D(), new Vector2D(), false, this, 10,
						Color.MAGENTA, Color.YELLOW, 5, 10, 10, 30, 6, Math.PI / 45, 10,
						Color.RED, Assets.laserEnemy4, Assets.lostEnemy4
						));
			
				
				
				break;
			case 7:
				
				for(int i = 0; i < 12; i++) {
					walls.add(new Wall(0, new Vector2D((4 + i) * Constants.WALL_L, 4 * Constants.WALL_L), this));
					walls.add(new Wall(0, new Vector2D((19 + i) * Constants.WALL_L, 4 * Constants.WALL_L), this));
					
					walls.add(new Wall(0, new Vector2D((4 + i) * Constants.WALL_L, 30 * Constants.WALL_L), this));
					walls.add(new Wall(0, new Vector2D((19 + i) * Constants.WALL_L, 30 * Constants.WALL_L), this));
				}
				for(int i = 0; i < 11; i++) {
					walls.add(new Wall(0, new Vector2D(4 * Constants.WALL_L, (5 + i) * Constants.WALL_L), this));
					walls.add(new Wall(0, new Vector2D(4 * Constants.WALL_L, (19 + i) * Constants.WALL_L), this));
					
					walls.add(new Wall(0, new Vector2D(30 * Constants.WALL_L, (5 + i) * Constants.WALL_L), this));
					walls.add(new Wall(0, new Vector2D(30 * Constants.WALL_L, (19 + i) * Constants.WALL_L), this));
				}
				
				
				for(int i = 0; i < 5; i++) {
					walls.add(new Wall(0, new Vector2D((15 + i) * Constants.WALL_L, 14 * Constants.WALL_L), this));
					walls.add(new Wall(0, new Vector2D((15 + i) * Constants.WALL_L, 20 * Constants.WALL_L), this));
					walls.add(new Wall(0, new Vector2D(15 * Constants.WALL_L, (15 + i) * Constants.WALL_L), this));
				}
				
				walls.add(new Wall(0, new Vector2D(19 * Constants.WALL_L, 15 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(19 * Constants.WALL_L, 19 * Constants.WALL_L), this));
				
				player = new Player(
						0, new Vector2D(17 * Constants.WALL_L + 20, 17 * Constants.WALL_L + 20), new Vector2D(), new Vector2D(),
						false, this, 20, 4, Color.YELLOW, Color.DARK_GRAY, 7, 15, 10, 10, Math.PI / 36, 10,
						Color.WHITE
						);
				gameObjects.add(player);
				
				gameObjects.add(new Enemy(
						0, new Vector2D(2 * Constants.WALL_L, 2 * Constants.WALL_L),
						new Vector2D(), new Vector2D(), false, this, 20,
						Color.BLUE, Color.BLACK, 5, 10, 10, 15, 4, Math.PI / 45, 20,
						Color.BLUE, Assets.laserEnemy1, Assets.lostEnemy1
						));
				
				gameObjects.add(new Enemy(
						0, new Vector2D(2 * Constants.WALL_L, 32 * Constants.WALL_L),
						new Vector2D(), new Vector2D(), false, this, 20,
						Color.BLUE, Color.BLACK, 5, 10, 10, 15, 4, Math.PI / 45, 20,
						Color.BLUE, Assets.laserEnemy2, Assets.lostEnemy2
						));
				
				gameObjects.add(new Enemy(
						Math.PI, new Vector2D(32 * Constants.WALL_L, 2 * Constants.WALL_L),
						new Vector2D(), new Vector2D(), false, this, 20,
						Color.ORANGE, Color.BLACK, 5, 10, 10, 15, 4, Math.PI / 45, 20,
						Color.ORANGE, Assets.laserEnemy3, Assets.lostEnemy3
						));
				
				gameObjects.add(new Enemy(
						Math.PI, new Vector2D(32 * Constants.WALL_L, 32 * Constants.WALL_L),
						new Vector2D(), new Vector2D(), false, this, 20,
						Color.ORANGE, Color.BLACK, 5, 10, 10, 15, 4, Math.PI / 45, 20,
						Color.ORANGE, Assets.laserEnemy4, Assets.lostEnemy4
						));
				
				gameObjects.add(new Enemy(
						0, new Vector2D(6 * Constants.WALL_L, 6 * Constants.WALL_L),
						new Vector2D(), new Vector2D(), false, this, 20,
						Color.RED, Color.CYAN, 5, 10, 15, 25, 3, Math.PI / 45, 20,
						Color.CYAN, Assets.laserEnemy5, Assets.lostEnemy5
						));
				
				gameObjects.add(new Enemy(
						0, new Vector2D(6 * Constants.WALL_L, 28 * Constants.WALL_L),
						new Vector2D(), new Vector2D(), false, this, 20,
						Color.RED, Color.CYAN, 5, 10, 15, 25, 3, Math.PI / 45, 20,
						Color.CYAN, Assets.laserEnemy6, Assets.lostEnemy6
						));
				
				gameObjects.add(new Enemy(
						0, new Vector2D(28 * Constants.WALL_L, 6 * Constants.WALL_L),
						new Vector2D(), new Vector2D(), false, this, 20,
						Color.WHITE, Color.BLACK, 5, 10, 20, 30, 5, Math.PI / 45, 20,
						Color.WHITE, Assets.laserEnemy7, Assets.lostEnemy7
						));
				
				gameObjects.add(new Enemy(
						0, new Vector2D(28 * Constants.WALL_L, 28 * Constants.WALL_L),
						new Vector2D(), new Vector2D(), false, this, 20,
						Color.WHITE, Color.BLACK, 5, 10, 20, 30, 5, Math.PI / 45, 20,
						Color.WHITE, Assets.laserEnemy8, Assets.lostEnemy8
						));
				
				break;
			case 8:
				
				walls.add(new Wall(0, new Vector2D(5 * Constants.WALL_L, 5 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(6 * Constants.WALL_L, 6 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(11 * Constants.WALL_L, 11 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(12 * Constants.WALL_L, 12 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(22 * Constants.WALL_L, 22 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(23 * Constants.WALL_L, 23 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(28 * Constants.WALL_L, 28 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(29 * Constants.WALL_L, 29 * Constants.WALL_L), this));
				
				walls.add(new Wall(0, new Vector2D(29 * Constants.WALL_L, 5 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(28 * Constants.WALL_L, 6 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(23 * Constants.WALL_L, 11 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(22 * Constants.WALL_L, 12 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(12 * Constants.WALL_L, 22 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(11 * Constants.WALL_L, 23 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(6 * Constants.WALL_L, 28 * Constants.WALL_L), this));
				walls.add(new Wall(0, new Vector2D(5 * Constants.WALL_L, 29 * Constants.WALL_L), this));
				
				walls.add(new Wall(0, new Vector2D(17 * Constants.WALL_L, 17 * Constants.WALL_L), this));
				
				player = new Player(
						0, new Vector2D(22 * Constants.WALL_L + 20, 17 * Constants.WALL_L + 20), new Vector2D(), new Vector2D(),
						false, this, 20, 4, Color.YELLOW, Color.DARK_GRAY, 7, 15, 10, 10, Math.PI / 36, 10,
						Color.WHITE
						);
				gameObjects.add(player);
				
				gameObjects.add(new Enemy(
						0, new Vector2D(8 * Constants.WALL_L, 8 * Constants.WALL_L),
						new Vector2D(), new Vector2D(), false, this, 20,
						Color.BLUE, Color.BLACK, 5, 10, 20, 30, 4, Math.PI / 45, 20,
						Color.WHITE, Assets.laserEnemy1, Assets.lostEnemy1
						));
				
				gameObjects.add(new Enemy(
						0, new Vector2D(8 * Constants.WALL_L, 26 * Constants.WALL_L),
						new Vector2D(), new Vector2D(), false, this, 20,
						Color.BLUE, Color.BLACK, 5, 10, 20, 30, 4, Math.PI / 45, 20,
						Color.WHITE, Assets.laserEnemy2, Assets.lostEnemy2
						));
				
				gameObjects.add(new Enemy(
						0, new Vector2D(8 * Constants.WALL_L, 17 * Constants.WALL_L),
						new Vector2D(), new Vector2D(), false, this, 10,
						Color.RED, Color.YELLOW, 5, 10, 30, 10, 3, Math.PI / 45, 10,
						Color.MAGENTA, Assets.laserEnemy3, Assets.lostEnemy3
						));
				
				gameObjects.add(new Enemy(
						0, new Vector2D(26 * Constants.WALL_L, 8 * Constants.WALL_L),
						new Vector2D(), new Vector2D(), false, this, 20,
						Color.LIGHT_GRAY, Color.DARK_GRAY, 10, 10, 10, 15, 5, 2 * Math.PI / 45, 20,
						Color.GRAY, Assets.laserEnemy4, Assets.lostEnemy4
						));
				
				gameObjects.add(new Enemy(
						0, new Vector2D(26 * Constants.WALL_L, 26 * Constants.WALL_L),
						new Vector2D(), new Vector2D(), false, this, 20,
						Color.LIGHT_GRAY, Color.DARK_GRAY, 10, 10, 10, 15, 5, 2 * Math.PI / 45, 20,
						Color.GRAY, Assets.laserEnemy5, Assets.lostEnemy5
						));
				
				
				break;
			case 9:
				
				for(int i = 0; i < 5; i++) {
					walls.add(new Wall(0, new Vector2D((4 + i * 2) * Constants.WALL_L, (11 + i) * Constants.WALL_L), this));
					walls.add(new Wall(0, new Vector2D((5 + i * 2) * Constants.WALL_L, (11 + i) * Constants.WALL_L), this));
					
					walls.add(new Wall(0, new Vector2D((21 + i * 2) * Constants.WALL_L, (19 + i) * Constants.WALL_L), this));
					walls.add(new Wall(0, new Vector2D((22 + i * 2) * Constants.WALL_L, (19 + i) * Constants.WALL_L), this));
					
					walls.add(new Wall(0, new Vector2D((23 - i) * Constants.WALL_L, (5 + i * 2) * Constants.WALL_L), this));
					walls.add(new Wall(0, new Vector2D((23 - i) * Constants.WALL_L, (6 + i * 2) * Constants.WALL_L), this));
					
					walls.add(new Wall(0, new Vector2D((15 - i) * Constants.WALL_L, (21 + i * 2) * Constants.WALL_L), this));
					walls.add(new Wall(0, new Vector2D((15 - i) * Constants.WALL_L, (22 + i * 2) * Constants.WALL_L), this));
				}
				
				player = new Player(
						0, new Vector2D(12 * Constants.WALL_L + 20, 6 * Constants.WALL_L + 20), new Vector2D(), new Vector2D(),
						false, this, 20, 4, Color.YELLOW, Color.DARK_GRAY, 7, 15, 10, 10, Math.PI / 36, 10,
						Color.WHITE
						);
				gameObjects.add(player);
				
				gameObjects.add(new Enemy(
						0, new Vector2D(28 * Constants.WALL_L, 11 * Constants.WALL_L),
						new Vector2D(), new Vector2D(), false, this, 10,
						Color.RED, Color.BLACK, 5, 10, 10, 50, 6, Math.PI / 45, 10,
						Color.MAGENTA, Assets.laserEnemy1, Assets.lostEnemy1
						));
				
				gameObjects.add(new Enemy(
						0, new Vector2D(22 * Constants.WALL_L, 27 * Constants.WALL_L),
						new Vector2D(), new Vector2D(), false, this, 10,
						Color.RED, Color.BLACK, 5, 10, 10, 50, 6, Math.PI / 45, 10,
						Color.MAGENTA, Assets.laserEnemy2, Assets.lostEnemy2
						));
				
				gameObjects.add(new Enemy(
						0, new Vector2D(32 * Constants.WALL_L, 32 * Constants.WALL_L),
						new Vector2D(), new Vector2D(), false, this, 20,
						Color.WHITE, Color.CYAN, 5, 10, 20, 25, 4, Math.PI / 45, 20,
						Color.WHITE, Assets.laserEnemy3, Assets.lostEnemy3
						));
				
				gameObjects.add(new Enemy(
						0, new Vector2D(6 * Constants.WALL_L, 24 * Constants.WALL_L),
						new Vector2D(), new Vector2D(), false, this, 20,
						Color.BLUE, Color.GREEN, 5, 10, 10, 20, 3, Math.PI / 45, 20,
						Color.GREEN, Assets.laserEnemy4, Assets.lostEnemy4
						));
				
				
				break;
			case 10:
				
				for(int i = 0; i < 8; i++) {
					for(int j = 0; j < 8; j++) {
						walls.add(new Wall(0, new Vector2D((3 + 4 * i) * Constants.WALL_L, (3 + 4 * j) * Constants.WALL_L), this));
					}
				}
				
				player = new Player(
						0, new Vector2D(17 * Constants.WALL_L + 20, 18 * Constants.WALL_L + 20), new Vector2D(), new Vector2D(),
						false, this, 20, 4, Color.YELLOW, Color.DARK_GRAY, 7, 15, 10, 10, Math.PI / 36, 10,
						Color.WHITE
						);
				gameObjects.add(player);
				
				
				
				gameObjects.add(new Enemy(
						0, new Vector2D(17 * Constants.WALL_L, 2 * Constants.WALL_L),
						new Vector2D(), new Vector2D(), false, this, 20,
						Color.BLUE, Color.GRAY, 5, 10, 20, 20, 5, Math.PI / 45, 20,
						Color.BLUE, Assets.laserEnemy1, Assets.lostEnemy1
						));
				
				gameObjects.add(new Enemy(
						0, new Vector2D(17 * Constants.WALL_L, 30 * Constants.WALL_L),
						new Vector2D(), new Vector2D(), false, this, 20,
						Color.MAGENTA, Color.GRAY, 5, 10, 25, 25, 6, Math.PI / 45, 20,
						Color.MAGENTA, Assets.laserEnemy2, Assets.lostEnemy2
						));
				
				gameObjects.add(new Enemy(
						0, new Vector2D(30 * Constants.WALL_L, 2 * Constants.WALL_L),
						new Vector2D(), new Vector2D(), false, this, 20,
						Color.ORANGE, Color.BLACK, 5, 10, 10, 10, 7, Math.PI / 45, 20,
						Color.ORANGE, Assets.laserEnemy3, Assets.lostEnemy3
						));
				
				gameObjects.add(new Enemy(
						0, new Vector2D(29 * Constants.WALL_L, 18 * Constants.WALL_L),
						new Vector2D(), new Vector2D(), false, this, 20,
						Color.ORANGE, Color.BLACK, 5, 10, 10, 10, 7, Math.PI / 45, 20,
						Color.ORANGE, Assets.laserEnemy4, Assets.lostEnemy4
						));
				
				gameObjects.add(new Enemy(
						0, new Vector2D(29 * Constants.WALL_L, 30 * Constants.WALL_L),
						new Vector2D(), new Vector2D(), false, this, 10,
						Color.CYAN, Color.BLACK, 5, 10, 20, 20, 8, Math.PI / 45, 10,
						Color.CYAN, Assets.laserEnemy5, Assets.lostEnemy5
						));
				
				
				break;
		}
	}
	
	public void setFinished(boolean finished) {
		this.finished = finished;
	}
	
	public void setWin(boolean win) {
		this.win = win;
	}
	
	public Camera getCamera() {
		return camera;
	}
	
	@Override
	public void update() {
		camera.update();
		/*for(int i = 0; i < gameObjects.size(); i++) {
			GameMovingObject e = gameObjects.get(i);
			if(e instanceof Bullet) {
				Bullet e2 = (Bullet) e;
				if(e2.isDead()) {
					gameObjects.remove(e);
				}
			}
			
		}*/
		
		for(int i = 0; i < gameObjects.size(); i++) {
			GameMovingObject e = gameObjects.get(i);
			if(e.isDead()) {
				gameObjects.remove(e);
			}
		}
		
		
		for(int i = 0; i < gameObjects.size(); i++) {
			gameObjects.get(i).update();
		}
		
		
		
		for(int i = 0; i < walls.size(); i++) {
			walls.get(i).update();
		}
		
		
		if(Keyboard.q)
			State.setCurrentState(new MainState());
		
		
		
		if(finished) {
			if(!endBegin) {
				end.run();
				endBegin = true;
			}
			
		}
		end.update();
		if(endBegin) {
			if(!end.isRunning()) {
				if(win) {
					if(level < 10) {
						State.setCurrentState(new TestState(level + 1));
					}
					else {
						State.setCurrentState(new MainState());
					}
				}
				else {
					State.setCurrentState(new TestState(level));
				}
				
			}
		}
		
		
		
	}

	@Override
	public void draw(Graphics g) {
		
		
		
		for(int i = 0; i < gameObjects.size(); i++) {
			gameObjects.get(i).draw(g);
		}
		for(int i = 0; i < walls.size(); i++) {
			walls.get(i).draw(g);
		}
		
		g.setColor(Color.RED);
		Text.drawText(g, "Level " + level,
				new Vector2D(720, 30), 
				true, Color.WHITE, Assets.fontSmall);
		
		
		if(level == 0) {
			Text.drawText(g, "Use the keyboard arrows to move",
					new Vector2D(400, 70), 
					true, Color.WHITE, Assets.fontSmall);
			
			Text.drawText(g, "Press space bar to shoot",
					new Vector2D(400, 90), 
					true, Color.WHITE, Assets.fontSmall);
			Text.drawText(g, "Press Q to go to the level menu",
					new Vector2D(400, 110), 
					true, Color.WHITE, Assets.fontSmall);
		}
	}
	
	public ArrayList<GameMovingObject> getGameObjects() {
		return gameObjects;
	}
	
	public ArrayList<Wall> getWalls() {
		return walls;
	}
	
	public Player getPlayer() {
		return player;
	}

}
