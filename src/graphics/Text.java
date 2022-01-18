package graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import math.Vector2D;

public class Text {
	public static void drawText(Graphics g, String text, Vector2D position, 
			boolean center, Color color, Font font) {
		g.setColor(color);
		g.setFont(font);
		Vector2D newPosition = new Vector2D(position.getX(), position.getY());
		
		if(center) {
			FontMetrics fm = g.getFontMetrics();
			newPosition.setX(newPosition.getX() - fm.stringWidth(text) / 2);
			newPosition.setY(newPosition.getY() - fm.getHeight() / 2);
		}
		
		g.drawString(text, (int) newPosition.getX(), (int) newPosition.getY());
	}
}
