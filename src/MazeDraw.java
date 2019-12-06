import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MazeDraw extends JPanel{
	
	private BufferedImage maze;
	private String difficulty, gameOverText, collectedLetters;
	private int[][] possibleLetterLocs;
	private List<Letter> letters;
	private int playerX, playerY, moveSpeed, currentLetter, lives;
	private boolean choseWord, gameOver;
	private LetterGenerator word;
	private JPanel UIPanel, restartPanel;
	private JLabel UIText;
	private JButton exitButton, restartButton;
	private Timer timer;
	private long startTime, elapsedTime;
	private Graphics g;
	
	public MazeDraw(String diff, JPanel UIP) {
		this.setLayout(new BorderLayout());
		
		difficulty = diff;
		collectedLetters = "";
		
		int[][] pLL = {{2,2,0}, {11,2,0}, {20,2,0}, {18,3,0}, {3,4,0}, {9,5,0}, {13,5,0}, {15,5,0}, {5,7,0}, {5,9,0},
						{17,9,0}, {2,11,0}, {20,11,0}, {5,13,0}, {17,13,0}, {17,15,0}, {7,17,0}, {9,17,0}, {13,17,0}, 
						{19,18,0}, {4,19,0}, {2,20,0}, {11,20,0}, {20,20,0}};
		possibleLetterLocs = pLL;
		
		letters = new ArrayList<Letter>();
		
		playerX = 300;
		playerY = 300;
		
		choseWord = false;
		moveSpeed = 5;
		currentLetter = 0;
		
		restartButton = new JButton("Restart");
		exitButton = new JButton("Exit");
		
		restartButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				WordScramble ws = new WordScramble();
			}
			
		});
		
		exitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
				
			}
			
		});
		
		restartPanel = new JPanel();
		
		restartPanel.setBackground(Color.BLACK);
		restartPanel.add(restartButton);
		restartPanel.add(exitButton);
		
		UIPanel = UIP;
		UIText = new JLabel();
		
		lives = 3;
		
		startTime = System.currentTimeMillis();
		
		timer = new Timer(100, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				elapsedTime = System.currentTimeMillis() - startTime;
				UIText.setText("TIME ELAPSED: " + (double)((int)(((double) elapsedTime / 1000.0) * 10)) / 10.0 + "s " + collectedLetters + " LIVES: " + lives);
			}
			
		});
		timer.start();
		
		setupUI();
		
		this.add(UIPanel, BorderLayout.NORTH);
		
		//create BufferedImage to draw on
		maze = new BufferedImage(630, 650, BufferedImage.TYPE_INT_RGB);
				
		g = maze.createGraphics();
		
	}
	
	protected void paintComponent(Graphics graph) {
		super.paintComponent(graph);

		//draw walls everywhere
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 630, 630);
		
		//draw in the paths
		g.setColor(Color.WHITE);
		
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
		
		//place letters on the image
		placeLetters(g);
		
		//draw the player at the spawn
		g.setColor(Color.GREEN);
		g.fillRect(playerX, playerY, 15, 15);
		
		//put the BufferedImage on the MazeDraw object
		graph.drawImage(maze, 0, 0, this);
			
		if (gameOver) {
			UIText.setText(gameOverText);
			UIText.setFont(new Font(Font.MONOSPACED, Font.BOLD, 12));
			this.add(restartPanel, BorderLayout.SOUTH);
		}
	}
	
	private void placeLetters(Graphics g) {
		if (!choseWord)
			word = new LetterGenerator(difficulty); //create a new word
		
		int length = word.getWord().length();
		int r, x, y;
		
		g.setColor(Color.BLUE);
		g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 25));
		
		if(!choseWord) {
			//for each letter in the word, choose a random letter spawn point to place it at
			for (int i = 0; i < length; i++) {
				do {
					r = (int) (Math.random() * possibleLetterLocs.length);
				} while (possibleLetterLocs[r][2] != 0);
				
				x = (possibleLetterLocs[r][0] * 30) - 22;
				y = (possibleLetterLocs[r][1] * 30) - 8;
				g.drawString(word.getLetter(i), x, y);
				
				//add this letter to the list keeping track of them
				letters.add(new Letter(word.getLetter(i), x, y));
				
				possibleLetterLocs[r][2]++; //mark that that spawn point has been used
			}
		} else //just redraw the letters, don't replace them
			for (Letter l : letters)
				if (l.getDraw())
					g.drawString(l.getLetter(), l.getX(), l.getY());
		
		
		choseWord = true;
	}
	
	/*
	 * these methods move the player in the specified direction only if the player won't move into a wall
	 */
	
	private void setupUI() {
		//add UI to UIPanel
		UIPanel.setBackground(Color.BLACK);
		UIText.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		UIText.setForeground(Color.WHITE);
		UIPanel.add(UIText);
	}

	public void moveUp() {
		if(!gameOver) {
			if (!checkCollide(0)) {
				playerY -= moveSpeed;
			}
			collectLetters(0);
		}
	}
	
	public void moveDown() {
		if(!gameOver) {
			if (!checkCollide(2)) {
				playerY += moveSpeed;
			}
			collectLetters(2);
		}
	}
	
	public void moveRight() {
		if(!gameOver) {
			if (!checkCollide(1)) {
				playerX += moveSpeed;
			}
			collectLetters(1);
		}
	}
	
	public void moveLeft() {
		if(!gameOver) {
			if (!checkCollide(3)) {
				playerX -= moveSpeed;
			}
			collectLetters(3);
		}
	}
	
	//checks to see if moving in the given direction would collide with a wall
	//0 means up, 1 means right, 2 means down, 3 means left
	private boolean checkCollide(int direction) {

		boolean collide = false;
		
		//each of these check the edge of the player that could hit a wall depending on the direction
		// and if the space they're about to move to is black, don't do it
		if (direction == 0) {
			for (int i = playerX; i < playerX + 15; i++) {
				if(maze.getRGB(i, playerY - moveSpeed) == -16777216) {
					collide = true;
					break;
				}
			}
		} else if (direction == 1) {
			for (int i = playerY; i < playerY + 15; i++) {
				if(maze.getRGB(playerX + 15, i) == -16777216) {
					collide = true;
					break;
				}
			}
		} else if (direction == 2) {
			for (int i = playerX; i < playerX + 15; i++) {
				if(maze.getRGB(i, playerY + 15) == -16777216) {
					collide = true;
					break;
				}
			}
		} else if (direction == 3) {
			for (int i = playerY; i < playerY + 15; i++) {
				if(maze.getRGB(playerX - moveSpeed, i) == -16777216) {
					collide = true;
					break;
				}
			}
		}
		
		return collide;
	}
	
	//checks to see if the player is overlapping a letter and should collect it
	private void collectLetters(int dir) {
		
		for (int i = 0; i < letters.size(); i++) {
			if (letters.get(i).getDraw()) {
				if ((playerX >= letters.get(i).getX() - (8 + 15)) && (playerX <= letters.get(i).getX() + 30 - 8) &&	//if player is in x-range
						(playerY >= letters.get(i).getY() - (22 + 15)) && (playerY <= letters.get(i).getY() + 30 - 22)) {	//and y-range of the letter
						
					//if the letter collected is the correct next one, collect it
					if(letters.get(i).getLetter().equalsIgnoreCase(word.getLetter(currentLetter))) {
						letters.get(i).setDraw(false);
						collectedLetters += letters.get(i).getLetter();
						if(++currentLetter >= word.getWord().length())
							gameOver(true);
					} else {
						if (--lives == 0)
							gameOver(false);
						switch(dir) {
						case 0:
							moveDown();
							break;
						case 1:
							moveLeft();
							break;
						case 2:
							moveUp();
							break;
						case 3:
							moveRight();
							break;
						}
					}
					break;
				}
			}
		}
	}
	
	//called to show the game end screen
	//if type==true, player won. if type==false, player lost
	private void gameOver(boolean type) {
		timer.stop();
		
		gameOver = true;
		
		if(type) {
			gameOverText = "Congratulations! You've won!";
		} else {
			gameOverText = "Sorry, you've lost. Better luck next time.";
		}
		
		gameOverText += " Your time was " + (double)((int)(((double) elapsedTime / 1000.0) * 10)) / 10.0 + 
				"s. The word was " + word.getWord();
	}
	
	public int getPlayerX() {
		return playerX;
	}
	
	public int getPlayerY() {
		return playerY;
	}
}