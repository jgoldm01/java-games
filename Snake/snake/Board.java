package snake;

import java.awt.Canvas;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Board extends JPanel {
	
	private int boardWidth = 400, boardHeight = 500;
	private int startTime = 600, refreshTime = 200;
	private Information info;
	
	public Board() {
		makeFrame();
		makeKeyListener();
		init();
	}
	private void init() {
		initInfoBody();

		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				info.move();
				if (!info.checkAlive()) {
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
						init();
						this.cancel();
					} else {
						System.exit(0);
					}
				}
				info.grow();
				repaint();
			}
		}, startTime, refreshTime);
	}

	
	public void initInfoBody() {
		this.info = new Information(boardWidth, boardHeight);
	}
	
	public void makeFrame() {
		JFrame frame = new JFrame("snakeee");
		frame.add(this);
		frame.setSize(boardWidth, boardHeight);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
	}
	
	public void makeKeyListener() {
		KeyListener listener = new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}
			
			public void keyPressed(KeyEvent e) {
				info.updateDirection(KeyEvent.getKeyText(e.getKeyCode()));
			}
			
			public void keyReleased(KeyEvent e) {
			}
		};
		addKeyListener(listener);
		setFocusable(true);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
							 RenderingHints.VALUE_ANTIALIAS_ON);
		info.paint(g2d);
	}
}
