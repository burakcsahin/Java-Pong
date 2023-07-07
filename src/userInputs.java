import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class userInputs implements KeyListener{
	
	private boolean pressedKey[] = new boolean[128];
	
	@Override
	public void keyTyped(KeyEvent e) {
	/*	 char keyChar = e.getKeyChar();
		 System.out.println("Key typed: " + keyChar); */
	}

	@Override
	public void keyPressed(KeyEvent e) {
		pressedKey[e.getKeyCode()] = true;
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		pressedKey[e.getKeyCode()] = false;
		
	}
	
	public boolean isKeyPressed(int key) {
		return pressedKey[key];
	}

}
