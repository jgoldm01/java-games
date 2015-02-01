package tetris;

import java.awt.Color;

public class Block {
	public int x, y;
	public Color c;
	
	public Block() {
		x = 0; y = 0; c = Color.BLACK;
	}
	
	public Block(int x, int y, Color c) {
		this.x = x; 
		this.y = y;
		this.c = c;
	}
	
	public void setColor(Color c) {
		this.c = c;
	}
}
