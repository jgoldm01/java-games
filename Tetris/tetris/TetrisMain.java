package tetris;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TetrisMain extends JPanel{
	private Board b;
	private Piece p;
	private Window w;
	private int startTime = 500, refreshTime = 200;
	
	public TetrisMain() {
		difficultyMenu();
		this.b = new Board();
		this.w = new Window(b);
		init();
	}
		
	private Piece generatePiece() {
		Random rand = new Random();
		int val = rand.nextInt(7);
		if (val == 0) 
			return new I(b);
		if (val == 1)
			return new J(b);
		if (val == 2)
			return new L(b);
		if (val == 3)
			return new O(b);
		if (val == 4)
			return new S(b);
		if (val == 5)
			return new T(b);
		else
			return new Z(b);
	}
	
	private void difficultyMenu() {
		String[] options = {"Easy", "Medium", "Hard"};
		int choice = JOptionPane.showOptionDialog(null, //Component parentComponent
                "Select Difficulty", //Object message,
                "New Game", //String title
                JOptionPane.DEFAULT_OPTION, //int optionType
                JOptionPane.PLAIN_MESSAGE, //int messageType
                null, //Icon icon,
                options, 
                "Yes");
		if (choice == 0) {
			refreshTime = 500;
		} else if (choice == 1){
			refreshTime = 250;
		} else if (choice == 2) {
			refreshTime = 100; 
		} else {
			refreshTime = 500;
		}
	}
	
	private void init() {
		b.reset();
		p = generatePiece();
		w.newPiece(p);
		w.repaint();
		Timer timer = new Timer();
		TimerTask task = makeLoop();
		timer.schedule(task, startTime, refreshTime);
	}
	
	private TimerTask makeLoop() {
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				if (p.moveable) {
					p.down();
					w.repaint();
				} else {
						b.checkRows();
						if (b.isOverTop()) {
							gameOverMenu();
							difficultyMenu();
							init();
							this.cancel();
						} else {
							p = generatePiece();
							w.newPiece(p);
							w.repaint();
						}
				}
			}
		};
		return task;
	}
	
	private void gameOverMenu() {
		String[] options = {"Yes", "No"};
		int choice = JOptionPane.showOptionDialog(null, //Component parentComponent
	            "Play Again?", //Object message,
	            "Game Over", //String title
	            JOptionPane.YES_NO_OPTION, //int optionType
	            JOptionPane.PLAIN_MESSAGE, //int messageType
	            null, //Icon icon,
	            options, 
	            "Yes");
		if (choice != 0) {
			System.exit(0);
		}
	}
	
	public static void main(String args[]) {
		TetrisMain t = new TetrisMain();
	}
	
}
