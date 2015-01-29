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
import javax.swing.JPanel;

public class Controller extends JPanel{
	
	private Board b;
	private Information info;
	private int boardWidth = 400, boardHeight = 500;
	
	public Controller() {
		b = new Board();
		info = new Information(boardWidth, boardHeight);
		makeFrame();
		makeKeyListener();
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				info.move();
				info.checkAlive();
				info.grow();
				b.repaint();
			}
		}, 100, 200);
	}
	
	private void makeFrame() {
		JFrame frame = new JFrame("snakeee");
		frame.add(b);
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
	
	public static void main(String args[]) {
		Controller c = new Controller(); 
	}
	

}
