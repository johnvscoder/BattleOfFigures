package states;

import java.awt.Color;
import java.awt.Graphics;

import main.Constants;
import math.Vector2D;
import ui.Button;
import ui.ButtonAction;

public class MainState extends State{
	private Button[] levelButtons;
	private Button back;
	
	
	public MainState() {
		levelButtons = new Button[10];
		
		for(int i = 0; i < levelButtons.length; i++) {
			final int i2 = i;
			levelButtons[i] = new Button(
					new Vector2D(50 + (i % 5) * 150, 12.5 + ((i  - (i %  5)) / 5) * 37.5),
					new Vector2D(Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT),
					Color.GRAY, Color.LIGHT_GRAY, Color.DARK_GRAY, "Level " + (i + 1),
					new ButtonAction() {

						@Override
						public void action() {
							State.setCurrentState(new TestState(i2 + 1));
						}
						
					}
					);
		}
		back = new Button(
				new Vector2D(770 - Constants.BUTTON_WIDTH - 12.5, 550 - Constants.BUTTON_HEIGHT - 12.5),
				new Vector2D(Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT),
				Color.GRAY, Color.LIGHT_GRAY, Color.DARK_GRAY, "Back",
				new ButtonAction() {

					@Override
					public void action() {
						State.setCurrentState(new BeginState());
					}
					
				}
				);
	}
	
	@Override
	public void update() {
		for(int i = 0; i < levelButtons.length; i++) {
			levelButtons[i].update();
		}
		back.update();
	}

	@Override
	public void draw(Graphics g) {
		for(int i = 0; i < levelButtons.length; i++) {
			levelButtons[i].draw(g);
		}
		back.draw(g);
	}

}
