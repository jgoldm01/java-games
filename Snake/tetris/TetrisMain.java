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
	private int startTime = 500, refreshTime = 500;
	
	public TetrisMain() {
		this.b = new Board();
		this.p = generatePiece();
		this.w = new Window(b, p);
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
	
	private void init() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				if (p.moveable) {
					p.down();
					w.repaint();
				} else {
						b.checkRows();
						if (b.isOverTop()) {
							String[] options = {"Yes", "No"};
							int choice = JOptionPane.showOptionDialog(null, //Component parentComponent
		                            "Play Again?", //Object message,
		                            "Game Over", //String title
		                            JOptionPane.YES_NO_OPTION, //int optionType
		                            JOptionPane.PLAIN_MESSAGE, //int messageType
		                            null, //Icon icon,
		                            options, 
		                            "Yes");
							if (choice == 0) {
								b.reset();
								p = generatePiece();
								w.newPiece(p);
								w.repaint();
							} else {
								System.exit(0);
							}
						} else {
							p = generatePiece();
							w.newPiece(p);
							w.repaint();
						}
				}
			}
		}, startTime, refreshTime);
	}
	
	public static void main(String args[]) {
		TetrisMain t = new TetrisMain();
	}
	
}
