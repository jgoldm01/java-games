package snake;

import java.awt.Graphics2D;
import java.util.Random;

import javax.swing.JOptionPane;

public class Information {
	
	public String direction, lastMove;
	public Boolean alive;
	//the location for new body object, should growth be incurred
	public int ghostX, ghostY;
	public Body head, tail;
	private Apple apple;
	private int growCount = 0;
	public int boardWidth, boardHeight;
	
	//initializes the information class, which in turn initializes the snake
	public Information(int boardWidth, int boardHeight) {
		direction = "Down";
		alive = true;
		apple = new Apple(150, 150);
		
		this.boardWidth = boardWidth;
		this.boardHeight = boardHeight;
		 
		this.head = new Body(25, 75, null, this);
		this.tail = new Body(25, 50, head, this);
		this.tail = new Body(25, 25, tail, this);
	}
	
	//moves the snake
	public void move() {
		ghostX = tail.x;
		ghostY = tail.y;
		tail.move();
		lastMove = direction;
	}
	
	//checks if the snake is alive
	public Boolean checkAlive() {
		return tail.isAlive();
	}
	
	//checks if the head is on the apple
	//grows if need be and may generate new apple location
	public void grow() {
		Boolean moveApple = false;
		if (head.x == apple.x && head.y == apple.y) {
			growCount += 5;
			moveApple = true;
		}
		if (growCount > 0) {
			growCount --;
			tail = new Body(ghostX, ghostY, tail, this);
		}
		if (moveApple) {
			generateAppleLoc();
		}
	}
	
	public void generateAppleLoc() {
		Random rand = new Random();
		int appleX = rand.nextInt(boardWidth/25)*25;
		int appleY = rand.nextInt(boardHeight/25)*25;
		while (tail.isOverSnake(appleX, appleY)) {
			appleX = rand.nextInt(boardWidth/25)*25;
			appleY = rand.nextInt(boardHeight/25)*25;
		}
		apple.setLocation(appleX, appleY);
	}
	
	//paints the frame
	public void paint(Graphics2D g) {
		tail.paint(g);
		apple.paint(g);
	}
	
	//called by the head of the snake in isAlive
	public Boolean inBounds() {
		if (head.x >= 0 && head.x < boardWidth && head.y >= 0 && head.y < boardHeight-25)
			return true;
		else
			return false;
	}
	
	//updates direction, prevents head from hitting previous body segment
	public void updateDirection(String d) {
		if (lastMove == "Up" && d == "Down")
			return;
		if (lastMove == "Down" && d == "Up")
			return;
		if (lastMove == "Left" && d == "Right")
			return;
		if (lastMove == "Right" && d == "Left")
			return;
		direction = d;
	}

}
