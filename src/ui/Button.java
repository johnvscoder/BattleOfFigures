package ui;

import java.awt.Color;
import java.awt.Graphics;

import graphics.Assets;
import graphics.Text;
import input.Mouse;
import math.Vector2D;

public class Button {
	private Vector2D position;
	private Vector2D dimensions;
	private Color color1;
	private Color color2;
	private Color color3;
	private String text;
	private ButtonAction action;
	private boolean mouseEntered;
	
	public Button(Vector2D position, Vector2D dimensions,
			Color color1, Color color2, Color color3, String text, ButtonAction action) {
		this.position = position;
		this.dimensions = dimensions;
		this.color1 = color1;
		this.color2 = color2;
		this.color3 = color3;
		this.text = text;
		this.action = action;
		mouseEntered = false;
	}
	
	public void update() {
		if((Mouse.mouseX > position.getX()) &&
			(Mouse.mouseX < position.getX() + dimensions.getX()) &&
			(Mouse.mouseY > position.getY()) &&
			(Mouse.mouseY < position.getY() + dimensions.getY())) {
			mouseEntered = true;
			if(Mouse.left) {
				action.action();
			}
		}
		else {
			mouseEntered = false;
		}
	}
	
	public void draw(Graphics g) {
		if(mouseEntered) {
			g.setColor(color2);
		}
		else {
			g.setColor(color1);
		}
		
		g.fillRect((int) position.getX(), (int) position.getY(), (int) dimensions.getX(), (int) dimensions.getY());
		g.setColor(color3);
		//g.setFont(Assets.smallFont);
		Text.drawText(g, text,
				new Vector2D(position.getX() + dimensions.getX() / 2, position.getY() + dimensions.getY()), 
				true, Color.WHITE, Assets.fontSmall);
	}
}
