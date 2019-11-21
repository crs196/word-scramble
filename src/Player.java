import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class Player extends Component {
	int x, y = 30;
	// player has to eat each letter of the word in sequence
	// check each letter once the player ate a letter
	// once the player ate the wrong letter, say "game over"
	/*
	 * listeners for player events
	 */
	private void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			y += 5;
		}else if(e.getKeyCode() == KeyEvent.VK_UP) {
			y -= 5;
		}else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			x -= 5;
		}else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			x += 5;
		}
	}
}