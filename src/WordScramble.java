import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WordScramble {

	private static JFrame menuFrame, gameFrame;
	private static JLabel logoLabel, difficultyLabel;
	private static JPanel buttonPanel, logoPanel;
	private static JPanel UIPanel;
	private static JButton startButton, exitButton;
	private static JComboBox<String> difficultySelector;
	private static String[] difficulties;
	private static String difficulty;

	public WordScramble() {
		initializeMenu();
		listen();
	}

	/*
	 * initialize menu JFrame
	 */
	private void initializeMenu() {
		// set up actual frame
		menuFrame = new JFrame("Word Scramble");
		menuFrame.setSize(500, 400);
		menuFrame.setLocationRelativeTo(null);
		menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menuFrame.setLayout(new BorderLayout(10, 10));

		// initialize panels
		buttonPanel = new JPanel();
		logoPanel = new JPanel();

		// initialize buttons/labels
		startButton = new JButton("Start");
		exitButton = new JButton("Exit");
		difficultyLabel = new JLabel("Choose Difficulty");

		// set up difficulty selector
		difficulties = new String[] { "Easy", "Medium", "Difficult" };
		difficultySelector = new JComboBox<String>(difficulties);
		difficultySelector.setSelectedIndex(0);
		difficulty = "Easy";

		// set up layout and add contents of button panel
		buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		buttonPanel.add(startButton);
		buttonPanel.add(difficultyLabel);
		buttonPanel.add(difficultySelector);
		buttonPanel.add(exitButton);

		// set up logo panel
		ImageIcon mIcon = new ImageIcon("UI/title.gif");
		logoLabel = new JLabel(mIcon);
		logoPanel.add(logoLabel);

		// add everything to menu frame
		menuFrame.add(buttonPanel, BorderLayout.SOUTH);
		menuFrame.add(logoPanel, BorderLayout.CENTER);
		menuFrame.setResizable(false);
		menuFrame.setVisible(true);
	}

	/*
	 * initialize game JFrame
	 */
	private void initializeGame() {
		gameFrame = new JFrame("Word Scramble");
		
		UIPanel = new JPanel();
		
		//create maze
		MazeDraw maze = new MazeDraw(difficulty, UIPanel);

		
		//add a keyListener to the game JFrame
		gameFrame.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			//move the player in the direction pressed
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()== KeyEvent.VK_RIGHT) {
		            maze.moveRight();
		            maze.repaint();
				} else if(e.getKeyCode()== KeyEvent.VK_LEFT) {
					maze.moveLeft();
		            maze.repaint();
				} else if(e.getKeyCode()== KeyEvent.VK_DOWN) {
					maze.moveDown();
		            maze.repaint();
				} else if(e.getKeyCode()== KeyEvent.VK_UP) {
					maze.moveUp();;
		            maze.repaint();
				}
				
			}
		});
		

		//add panels to and set up gameFrame
		gameFrame.add(maze, BorderLayout.CENTER);
		gameFrame.add(UIPanel, BorderLayout.SOUTH);
		gameFrame.setSize(635, 675);
		gameFrame.setLocationRelativeTo(null);
		gameFrame.setResizable(false);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setVisible(true);
		
	}

	/*
	 * listeners for menu events
	 */
	private void listen() {
		// when the start button is pressed, set up the game and make its frame visible
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				menuFrame.setVisible(false);
				initializeGame();
			}
		});

		// when the difficulty is changed, change it
		difficultySelector.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				difficulty = (String) difficultySelector.getSelectedItem();
			}
		});

		// close the game when the exit button is pressed
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				menuFrame.dispose();
			}
		});
	}

}