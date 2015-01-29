package snake;

import java.awt.Color;
import java.awt.Graphics2D;

public class Apple {
	
	public int x, y;
	
	public Apple(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void paint(Graphics2D g) {
		g.setColor(Color.red);
		g.fillRoundRect(x, y, 24, 24, 10,10);
	}
}
