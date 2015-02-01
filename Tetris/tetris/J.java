package tetris;

import java.awt.Color;

public class J extends Piece {

	public J(Board board) {
		this.board = board;
		this.light = new Color(153, 204, 255);
		this.dark = new Color(0, 102, 204);
		for (int i = 0; i < 4; i++) {
			blocks[i].setColor(light);
		}
		calcBlocks();
	}
	
	void rotateBlocks() {
		if (rotation == 0) {
			for (int i = 0; i < 3; i++) {
				blocks[i].x = focus.x + i;
				blocks[i].y = focus.y - 1;
			}
			blocks[3].x = focus.x;
			blocks[3].y = focus.y;
		} else if (rotation == 90) {
			for (int i = 0; i < 3; i++) {
				blocks[i].x = focus.x + 1;
				blocks[i].y = focus.y - i;
			}
			blocks[3].x = focus.x + 2;
			blocks[3].y = focus.y;
		} else if (rotation == 180) {
			for (int i = 0; i < 3; i++) {
				blocks[i].x = focus.x + i;
				blocks[i].y = focus.y - 1;
			}
			blocks[3].x = focus.x + 2;
			blocks[3].y = focus.y - 2;
		} else if (rotation == 270) {
			for (int i = 0; i < 3; i++) {
				blocks[i].x = focus.x + 1;
				blocks[i].y = focus.y - i;
			}
			blocks[3].x = focus.x;
			blocks[3].y = focus.y - 2;
		}
	}

}
