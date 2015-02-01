package tetris;

import java.awt.Color;

public class O extends Piece{
	
	public O(Board board) {
		this.board = board;
		this.light = new Color(255, 255, 153);
		this.dark = new Color(204, 204, 0);
		for (int i = 0; i < 4; i++) {
			blocks[i].setColor(light);
		}
		calcBlocks();
	}
	
	void rotateBlocks() {
		for (int i = 0; i < 4; i++) {
			blocks[i].x = focus.x + 1 + i%2;
			blocks[i].y = focus.y - i/2;
		}
	}
}
