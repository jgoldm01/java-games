package tetris;

import java.awt.Color;

public class I extends Piece {

	public I(Board board) {
		this.board = board;
		this.light = new Color(153, 255, 255);
		this.dark = new Color(0, 204, 204);
		for (int i = 0; i < 4; i++) {
			blocks[i].setColor(light);
		}
		calcBlocks();
	}

	void rotateBlocks() {
		if (rotation == 0) {
			for (int i = 0; i < 4; i++) {
				blocks[i].x = focus.x + i;
				blocks[i].y = focus.y - 1;
			}
		} else if (rotation == 90) {
			for (int i = 0; i < 4; i++) {
				blocks[i].x = focus.x + 2;
				blocks[i].y = focus.y - i;
			}
		} else if (rotation == 180) {
			for (int i = 0; i < 4; i++) {
				blocks[i].x = focus.x + i;
				blocks[i].y = focus.y - 2;
			}
		} else if (rotation == 270) {
			for (int i = 0; i < 4; i++) {
				blocks[i].x = focus.x + 1;
				blocks[i].y = focus.y - i;
			}
		}
	}
	
}
