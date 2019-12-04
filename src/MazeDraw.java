import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.JPanel;

public class MazeDraw extends JPanel{
	
	BufferedImage maze;
	String difficulty;
	int[][] possibleLetterLocs;
	
	public MazeDraw(String diff) {
		difficulty = diff;
		int[][] pLL = {{2,2,0}, {11,2,0}, {20,2,0}, {18,3,0}, {3,4,0}, {9,5,0}, {13,5,0}, {15,5,0}, {5,7,0}, {5,9,0},
						{17,9,0}, {2,11,0}, {20,11,0}, {5,13,0}, {17,13,0}, {17,15,0}, {7,17,0}, {9,17,0}, {13,17,0}, 
						{19,18,0}, {4,19,0}, {2,20,0}, {11,20,0}, {20,20,0}};
		possibleLetterLocs = pLL;
	}
	
	protected void paintComponent(Graphics graph) {
		super.paintComponent(graph);
		
		maze = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
		
		Graphics g = maze.createGraphics();
		
		//draw walls everywhere
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 630, 630);
		
		//draw in the paths
		g.setColor(this.getBackground());
		
		g.fillRect(30, 30, 180, 30);
		g.fillRect(120, 60, 30, 150);
		g.fillRect(60, 90, 60, 60);
		g.fillRect(60, 150, 30, 240);
		g.fillRect(30, 210, 30, 60);
		g.fillRect(30, 300, 30, 30);
		g.fillRect(30, 420, 30, 180);
		g.fillRect(90, 360, 60, 90);
		g.fillRect(60, 420, 300, 30);
		g.fillRect(60, 480, 150, 30);
		g.fillRect(90, 510, 60, 60);
		g.fillRect(150, 540, 240, 30);
		g.fillRect(210, 570, 60, 30);
		g.fillRect(300, 570, 30, 30);
		g.fillRect(420, 570, 180, 30);
		g.fillRect(300, 30, 30, 570);
		g.fillRect(360, 30, 60, 30);
		g.fillRect(570, 30, 30, 180);
		g.fillRect(570, 300, 30, 30);
		g.fillRect(570, 360, 30, 60);
		g.fillRect(540, 240, 30, 300);
		g.fillRect(240, 60, 300, 30);
		g.fillRect(180, 60, 30, 300);
		g.fillRect(150, 120, 30, 90);
		g.fillRect(120, 240, 60, 30);
		g.fillRect(30, 300, 570, 30);
		g.fillRect(180, 330, 60, 30);
		g.fillRect(270, 270, 90, 90);
		g.fillRect(210, 90, 60, 60);
		g.fillRect(210, 210, 90, 30);
		g.fillRect(270, 180, 30, 30);
		g.fillRect(120, 450, 90, 30);
		g.fillRect(180, 330, 60, 30);
		g.fillRect(210, 360, 30, 60);
		g.fillRect(240, 450, 30, 60);
		g.fillRect(480, 210, 60, 60);
		g.fillRect(330, 180, 270, 30);
		g.fillRect(390, 210, 30, 90);
		g.fillRect(420, 270, 30, 30);
		g.fillRect(480, 90, 60, 60);
		g.fillRect(420, 120, 60, 60);
		g.fillRect(360, 120, 30, 60);
		g.fillRect(480, 150, 30, 30);
		g.fillRect(540, 120, 30, 30);
		g.fillRect(420, 330, 30, 240);
		g.fillRect(330, 390, 90, 30);
		g.fillRect(360, 480, 60, 60);
		g.fillRect(450, 360, 60, 30);
		g.fillRect(450, 420, 60, 90);
		g.fillRect(480, 480, 60, 60);
		g.fillRect(480, 540, 30, 30);
		
		placeLetters(g);
		
		graph.drawImage(maze, 0, 0, this);
		
	}
	
	private void placeLetters(Graphics g) {
		LetterGenerator word = new LetterGenerator(difficulty);
		
		int length = word.getWord().length();
		int r, x, y;
		
		g.setColor(Color.BLUE);
		g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 25));
		
		for (int i = 0; i < length; i++) {
			do {
				r = (int) (Math.random() * possibleLetterLocs.length);
			} while (possibleLetterLocs[r][2] != 0);
			
			x = (possibleLetterLocs[r][0] * 30) - 22;
			y = (possibleLetterLocs[r][1] * 30) - 8;
			g.drawString(word.getLetter(i), x, y);
			
			possibleLetterLocs[r][2]++;
		}
	}
}
