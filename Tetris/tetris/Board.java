package tetris;

public class Board {
	public Block[][] grid = new Block[10][24]; 
	
	public Board() {
		reset();
	}
	
	public Boolean check(Block[] b) {
		int x, y;
		for (int i = 0; i < 4; i++) {
			x = b[i].x;
			y = b[i].y;
			if (!checkBorders(x, y)) 
				return false;
			if (grid[x][y] != null) 
				return false;
		}
		return true;
	}
	
	public void add(Block[] b) {
		for (int i = 0; i < 4; i++) {
			grid[b[i].x][b[i].y] = b[i];
		}
	}
	
	//checks if there are any rows that should be deleted
	public void checkRows() {
		int filled;
		for (int y = 0; y < 24; y++) {
			filled = 0;
			for (int x = 0; x < 10; x++) {
				if (grid[x][y] != null) {
					filled++;
				}
			}
			if (filled == 10) {
				elimRow(y);
				y--;
			}
		}
	}
	
	public Boolean isOverTop() {
		for (int y = 20; y < 24; y++) {
			for (int x = 0; x < 10; x++) {
				if (grid[x][y] != null) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void reset() {
		for (int y = 0; y < 24; y++) {
			for (int x = 0; x < 10; x++) {
				grid[x][y] = null;
			}
		}
	}
	
	private void elimRow(int y) {
		for (int x = 0; x < 10; x++) {
			grid[x][y] = null;
		}
		for (y = y+1; y < 24; y++) {
			for (int x = 0; x < 10; x++) {
				if (grid[x][y] != null) {
					grid[x][y-1] = grid[x][y];
					grid[x][y] = null;
				}
			}
		}
	}
	

	
	private Boolean checkBorders(int x, int y) {
		if (x < 0 || x >= 10)
			return false;
		if (y < 0 || y >= 24)
			return false;
		return true;
	}
	
	
}
