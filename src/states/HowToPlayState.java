package states;

import java.awt.Color;
import java.awt.Graphics;

import main.Constants;
import math.Vector2D;
import ui.Button;
import ui.ButtonAction;

public class HowToPlayState extends State {
	private Button back;
	
	public HowToPlayState() {
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
		back.update();
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawString("Estás en el state de cómo jugar", 200, 200);
		back.draw(g);
	}

}
