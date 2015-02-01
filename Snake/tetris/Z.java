package tetris;

import java.awt.Color;

public class Z extends Piece{

	public Z(Board board) {
		this.board = board;
		this.light = new Color(153, 255, 153);
		this.dark = new Color(0, 204, 0);
		for (int i = 0; i < 4; i++) {
			blocks[i].setColor(light);
		}
		calcBlocks();
	}
	
	void rotateBlocks() {
		if (rotation == 0) {
			for (int i = 0; i < 4; i++) {
				blocks[i].x = focus.x + i%2 + i/2;
				blocks[i].y = focus.y - 1 + i/2;
			}
		} else if (rotation == 90) {
			for (int i = 0; i < 4; i++) {
				blocks[i].x = focus.x + 1 + i/2;
				blocks[i].y = focus.y - i%2 - i/2;
			}
		} else if (rotation == 180) {
			for (int i = 0; i < 4; i++) {
				blocks[i].x = focus.x + i%2 + i/2;
				blocks[i].y = focus.y - 2 + i/2;
			}
		} else if (rotation == 270) {
			for (int i = 0; i < 4; i++) {
				blocks[i].x = focus.x + i/2;
				blocks[i].y = focus.y - i%2 - i/2;
			}
		}
	}
}
