package tetris;

import java.awt.Color;

abstract class Piece {
	Block focus = new Block(3, 19, Color.black);
	Color light, dark;
	Block[] blocks = new Block[4];
	int rotation = 0;
	Board board;
	Boolean moveable = true;
	
	public Piece() {
		for (int i = 0; i < 4; i++) {
			blocks[i] = new Block();
		}
	}
	
	public void rotate90() {
		calcRotation();
		calcBlocks();
	}
	
	public Boolean right() {
		moveRight();
		if (board.check(blocks)) {
			return true;
		} else {
			moveLeft();
			return false;
		}
	}
	
	public Boolean left() {
		moveLeft();
		if (board.check(blocks)) {
			return true;
		} else {
			moveRight();
			return false;
		}
	}
	
	public Boolean down() {
		moveDown();
		if (board.check(blocks)) {
			return true;
		} else {
			moveUp();
			board.add(blocks);
			setUnmoveable();
			return false;
		}
	}
	
	//calculates the position of the blocks given the focus and the rotation
	abstract void rotateBlocks();
	
	protected void calcBlocks() {
		rotateBlocks();
		while (!board.check(blocks)) {
			moveLeft();
			if (board.check(blocks)) 
				break;
			else 
				moveRight();
			moveRight();
			if (board.check(blocks))
				break;
			else 
				moveLeft();
			moveUp();
		}
	}
	
	//changes boolean moveable to false
	//changes color of blocks to a darker hue
	private void setUnmoveable(){
		moveable = false;
		for (int i = 0; i < 4; i++) {
			blocks[i].setColor(dark);
		}
	}
	
	private void calcRotation() {
		if (rotation == 270) 
			rotation = 0;
		else 
			rotation += 90;
	}
	
	private void moveRight() {
		focus.x += 1; 
		for (int i = 0; i < 4; i++) {
			blocks[i].x += 1;
		}
	}
	
	private void moveLeft() {
		focus.x -= 1;
		for (int i = 0; i < 4; i++) {
			blocks[i].x -= 1;
		}
	}
	
	private void moveUp() {
		focus.y += 1;
		for (int i = 0; i < 4; i++) {
			blocks[i].y += 1;
		}
	}
	
	private void moveDown() {
		focus.y -= 1;
		for (int i = 0; i < 4; i++) {
			blocks[i].y -= 1;
		}
	}
	
	//for debugging
	public void printLoc() {
		for (int i = 0; i < 4; i++) {
			System.out.println(blocks[i].x);
		}
	}
}
