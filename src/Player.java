import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Player {
	private int x, y;
	private ImageIcon icon;
	// player has to eat each letter of the word in sequence
	// check each letter once the player ate a letter
	// once the player ate the wrong letter, say "game over"

	public Player() {
		x = 30;
		y = 30;
		icon = new ImageIcon("UI/player.png");
	}

	/*
	 * listeners for player events
	 */
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			y += 5;
		} else if (e.getKeyCode() == KeyEvent.VK_UP) {
			y -= 5;
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			x -= 5;
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			x += 5;
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public ImageIcon getIcon() {
		return icon;
	}
}