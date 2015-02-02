package tetris;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends JPanel{
	private int pieceSize = 25;
	private int boardWidth = 10, boardHeight = 20;
	private Board b;
	private Piece p;
	
	public Window(Board b) {
		this.b = b;
		makeFrame();
		makeKeyListener();
	}
	
	public void newPiece(Piece p) {
		this.p = p;
	}
	
	private void makeFrame() {
		JFrame frame = new JFrame("Tetris");
		frame.add(this);
		
		frame.setSize(boardWidth*pieceSize, boardHeight*pieceSize);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
//		frame.setResizable(false);
		frame.setVisible(true);
	}
	
	private void makeKeyListener() {
		KeyListener listener = new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}
			
			public void keyPressed(KeyEvent e) {
				if (KeyEvent.getKeyText(e.getKeyCode()) == "Up") 
					p.rotate90();
				if (KeyEvent.getKeyText(e.getKeyCode()) == "Down") 
					p.down();
				if (KeyEvent.getKeyText(e.getKeyCode()) == "Right") 
					p.right();
				if (KeyEvent.getKeyText(e.getKeyCode()) == "Left") 
					p.left();

				repaint();
			}
			
			public void keyReleased(KeyEvent e) {
			}
		};
		addKeyListener(listener);
		setFocusable(true);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		setBackground(Color.black);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
							 RenderingHints.VALUE_ANTIALIAS_ON);
		paintBoard(g2d); 
		if (p.moveable)
			paintPiece(g2d);
	}
	
	private void paintBoard(Graphics2D g) {
		for (int y = 0; y < 20; y++) {
			for (int x = 0; x < 10; x++) {
				if (b.grid[x][y] != null) { 
					g.setColor(b.grid[x][y].c);
					g.fillRoundRect(x*pieceSize, (19-y)*pieceSize, pieceSize-1, pieceSize-1, 10,10);
				}
			}
		}
		g.setColor(Color.red);
		g.fill3DRect(0, 20*pieceSize, 10*pieceSize, 5, true);
		g.fill3DRect(10*pieceSize, 0, 5, 20*pieceSize + 5, true);
	}
	
	private void paintPiece(Graphics2D g) {
		for (int i = 0; i < 4; i++) {
			g.setColor(p.blocks[i].c);
			g.fillRoundRect((p.blocks[i].x)*pieceSize, (19-p.blocks[i].y)*pieceSize, 
							pieceSize-1, pieceSize-1, 10, 10);
		}
	}
}
