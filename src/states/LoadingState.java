package states;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import graphics.Assets;
import graphics.Loader;
import graphics.Sound;
import graphics.Text;
import main.Constants;
import math.Vector2D;

public class LoadingState extends State {
	private Thread loadingThread;
	private Font font;
	
	public LoadingState(Thread loadingThread) {
		this.loadingThread = loadingThread;
		this.loadingThread.start();
		font = Loader.loadFont("/fonts/KenneyFutureNarrow.ttf", 38);
	}
	
	@Override
	public void update() {
		if(Assets.end) {
			State.setCurrentState(new BeginState());
			try {
				loadingThread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void draw(Graphics g) {
		
		
		float percentage = (Assets.count / Assets.maxCount);
		
		g.setColor(Color.GRAY);
		
		g.fillRect(Constants.WIDTH / 2 - Constants.LOADING_BAR_WIDTH / 2,
				Constants.HEIGHT / 2 - Constants.LOADING_BAR_HEIGHT / 2,
				(int)(Constants.LOADING_BAR_WIDTH * percentage),
				Constants.LOADING_BAR_HEIGHT);
		
		g.drawRect(Constants.WIDTH / 2 - Constants.LOADING_BAR_WIDTH / 2,
				Constants.HEIGHT / 2 - Constants.LOADING_BAR_HEIGHT / 2,
				Constants.LOADING_BAR_WIDTH,
				Constants.LOADING_BAR_HEIGHT);
		
		Text.drawText(g, "BATTLE OF FIGURES", new Vector2D(Constants.WIDTH / 2, Constants.HEIGHT / 2 - 50),
				true, Color.WHITE, font);
		
		
		Text.drawText(g, "LOADING...", new Vector2D(Constants.WIDTH / 2, Constants.HEIGHT / 2 + 40),
				true, Color.WHITE, font);
	}

}
