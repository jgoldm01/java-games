package tetris;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class test implements KeyListener{

	public test() {
		
	}
	static void main() {
		test t = new test();
	}
	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("hii");
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
