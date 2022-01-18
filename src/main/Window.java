package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import graphics.Assets;
import graphics.Text;
import input.Keyboard;
import input.Mouse;
import math.Vector2D;
import states.LoadingState;
import states.State;

public class Window extends JFrame implements Runnable {
	
	private Canvas canvas;
	private Thread thread;
	private boolean running = false;
	
	private BufferStrategy bs;
	private Graphics g;
	
	private final int fps = 60;
	private double targetTime = 1000000000 / fps;
	private double delta = 0;
	private int averageFps = fps;
	private Keyboard keyboard;
	
	public Window() {
		setTitle("Battle of Figures");
		setSize(Constants.WIDTH, Constants.HEIGHT);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/logo.png")));
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
		canvas.setMaximumSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
		canvas.setMinimumSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
		canvas.setFocusable(true);
		
		keyboard = new Keyboard();
		canvas.addKeyListener(keyboard);
		Mouse mouse = new Mouse();
		canvas.addMouseListener(mouse);
		canvas.addMouseMotionListener(mouse);
		
		add(canvas);
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Window().start();
	}
	
	public void init() {
		
		Thread loadingThread = new Thread(new Runnable() {

			@Override
			public void run() {
				Assets.init();
			}
			
		});
		
		
		State.setCurrentState(new LoadingState(loadingThread));
		
		
	}
	
	public void update() {
		keyboard.update();
		State.getCurrentState().update();
	}
	
	public void draw() {
		bs = canvas.getBufferStrategy();
		
		if(bs == null) {
			canvas.createBufferStrategy(3);
			return;
		}
		
		g = bs.getDrawGraphics();
		
		//------------------------------
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Constants.WIDTH, Constants.HEIGHT);
		
		State.getCurrentState().draw(g);
		
		Text.drawText(g, "" + averageFps,
				new Vector2D(20, 30), 
				true, Color.WHITE, Assets.fontSmall);
		
		//------------------------------
		
		g.dispose();
		bs.show();
	}
	
	@Override
	public void run() {
		long now = 0;
		long lastTime = System.nanoTime();
		double time = 0;
		int frames = 0;
		
		init();
		
		while(running) {
			now = System.nanoTime();
			delta += (now - lastTime) / targetTime;
			time += now -lastTime;
			lastTime = now;
			
			if(delta >= 1) {
				update();
				draw();
				delta--;
				frames++;
			}
			
			if(time >= 1000000000) {
				averageFps = frames;
				time = 0;
				frames = 0;
			}
		}
		
		stop();
	}
	
	public void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public void stop() {
		try {
			thread.join();
			running = false;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
