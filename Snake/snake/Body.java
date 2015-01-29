package snake;

import java.awt.Color;
import java.awt.Graphics2D;

public class Body {
	int x, y;
	private Body next;
	private Information info;

	
	public Body(int x, int y, Body n, Information info) {
		this.x = x;
		this.y = y;
		this.next = n;
		this.info = info;
	}
	
	public void move() {
		if (next != null) {
			x = next.x;
			y = next.y; 
			next.move();
		} else {
			if (info.direction == "Up") {
				y -= 25;
			} else if (info.direction == "Down") {
				y += 25;
			} else if (info.direction == "Left") {
				x -= 25;
			} else if (info.direction == "Right") {
				x += 25;
			}
		}
	}
	
	public Boolean isAlive() {
		//head is not over body, but is it in board?
		if (this.next == null) 
			return info.inBounds();
		//is this body segment in the same location as head?
		else if (info.head.x == x && info.head.y == y) 
			return false;
		else 
			return this.next.isAlive();
	}
	
	//returns true if the coordinates are currently occupied by the snake
	public Boolean isOverSnake (int locX, int locY) {
		if (locX == x && locY == y) {
			return true;
		} else if (this.next != null) {
			return next.isOverSnake(locX, locY);
		} else {
			return false;
		}
	}
	
	public int countSegments() {
		if (this.next == null)
			return 1;
		else 
			return 1 + next.countSegments();
	}
	
	public void paint(Graphics2D g) {
		g.fillRoundRect(x, y, 24, 24, 10,10);
		if (next != null) {
			next.paint(g);
		} else {
			g.setColor(Color.red);
			g.fillOval(x+16, y+5, 5, 5);
			g.fillOval(x+3, y+5, 5, 5);
			g.setColor(Color.white);
			int[] xArr = {x+7, x+15, x+11};
			int[] yArr = {y+18, y+18, y+22};
			g.fillPolygon(xArr, yArr, 3);
		}
	}
}
