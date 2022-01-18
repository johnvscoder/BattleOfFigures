package states;

import java.awt.Color;
import java.awt.Graphics;

import graphics.Assets;
import graphics.Sound;
import graphics.Text;
import main.Constants;
import math.Vector2D;
import ui.Button;
import ui.ButtonAction;

public class BeginState extends State {
	public static boolean music = false;
	
	private Button play;
	private Button howToPlay;
	private Button exit;
	
	public BeginState() {
		play = new Button(
				new Vector2D(Constants.WIDTH / 2 - Constants.BUTTON_WIDTH / 2, Constants.HEIGHT / 2 - 50),
				new Vector2D(Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT),
				Color.GRAY, Color.LIGHT_GRAY, Color.DARK_GRAY, "Play",
				new ButtonAction() {

					@Override
					public void action() {
						State.setCurrentState(new MainState());
					}
					
				}
				);
		howToPlay = new Button(
				new Vector2D(Constants.WIDTH / 2 - Constants.BUTTON_WIDTH / 2, Constants.HEIGHT / 2 - 12.5),
				new Vector2D(Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT),
				Color.GRAY, Color.LIGHT_GRAY, Color.DARK_GRAY, "How to play",
				new ButtonAction() {

					@Override
					public void action() {
						State.setCurrentState(new TestState(0));
					}
					
				}
				);
		exit = new Button(
				new Vector2D(Constants.WIDTH / 2 - Constants.BUTTON_WIDTH / 2, Constants.HEIGHT / 2 + 25),
				new Vector2D(Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT),
				Color.GRAY, Color.LIGHT_GRAY, Color.DARK_GRAY, "Exit",
				new ButtonAction() {

					@Override
					public void action() {
						System.exit(0);
					}
					
				}
				);
		
		if(!music) {
			music = true;
			State.setBackgroundMusic(new Sound(Assets.backgroundMusic, 0));
			State.getBackgroundMusic().changeVolume(-5f);
			State.getBackgroundMusic().loop();
		}
		
	}
	
	@Override
	public void update() {
		play.update();
		howToPlay.update();
		exit.update();
		
		
		
	}

	@Override
	public void draw(Graphics g) {
		play.draw(g);
		howToPlay.draw(g);
		exit.draw(g);
		
		Text.drawText(g, "BATTLE OF FIGURES", new Vector2D(Constants.WIDTH / 2, Constants.HEIGHT / 2 - 150),
				true, Color.WHITE, Assets.fontBig);
		
	}

}
