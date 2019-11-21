import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class WordScramble {

	private static JFrame menuFrame, gameFrame;
	private static JLabel logoLabel, difficultyLabel;
	private static JPanel buttonPanel, logoPanel;
	private static JPanel letterPanel, playerPanel;
	private static JLayeredPane gamePane;
	private static JButton startButton, exitButton;
	private static JComboBox<String> difficultySelector;
	private static String[] difficulties;
	private static String difficulty;
	private List<JLabel> letters;
	private int currentLetter, lives;

	public WordScramble() {
		currentLetter = 0;
		lives = 3;
		initializeMenu();
		listen();
	}

	/*
	 * initialize menu JFrame
	 */
	private void initializeMenu() {
		// set up actual frame
		menuFrame = new JFrame("Word Scramble");
		menuFrame.setSize(350, 275);
		menuFrame.setLocationRelativeTo(null);
		menuFrame.setBackground(Color.BLUE);
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
		ImageIcon mIcon = new ImageIcon("UI/title.png");
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
		gameFrame.setSize(310, 310);
		gameFrame.getContentPane().setBackground(Color.CYAN);
		gameFrame.setLayout(new FlowLayout());

		letterPanel = new JPanel();
		letterPanel.setSize(300, 300);
		LetterGenerator word = new LetterGenerator(difficulty);
		letters = new ArrayList<JLabel>();

		// add letters to letterPanel
		letterPanel.setLayout(new GridBagLayout());
		GridBagConstraints lettersC = new GridBagConstraints();

		// for each letter in the goal word
		for (int i = 0; i < word.getWord().length(); i++) {
			letters.add(i, new JLabel(word.getLetter(i))); // make a JLabel with that letter in it
			letters.get(i).setPreferredSize(new Dimension(75, 75)); // set its preferred size larger
			letters.get(i).setFont(new Font("Helvetica", 1, 30)); // set the font, make it bold, and set font size
			lettersC.gridx = (int) (Math.random() * 35); // place it in a random spot in the GridBagLayout
			lettersC.gridy = (int) (Math.random() * 52);
			letterPanel.add(letters.get(i), lettersC);
		}

		for (JLabel let : letters) {
			let.addMouseListener(new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent arg0) {
					let.setVisible(false);
					String l = let.getText();
					if (!l.equals(word.getLetter(currentLetter))) {
						lives--;
						let.setVisible(true);
					} else {
						currentLetter++;
					}

					if (lives <= 0) {
						letterPanel.removeAll();
						letterPanel.add(new JLabel("Sorry, you've run out of lives. Try again next time."));
					}

					if (currentLetter >= word.getWord().length()) {
						letterPanel.add(new JLabel("Congratulations, you've won!"));
					}

				}

				@Override
				public void mouseEntered(MouseEvent arg0) {
				}

				@Override
				public void mouseExited(MouseEvent arg0) {
				}

				@Override
				public void mousePressed(MouseEvent arg0) {
				}

				@Override
				public void mouseReleased(MouseEvent arg0) {

				}

			});
		}

		playerPanel = new JPanel();
		Player player = new Player();
		JLabel playerIcon = new JLabel(player.getIcon());

		playerPanel.setLayout(new GridBagLayout());
		GridBagConstraints playerC = new GridBagConstraints();
		playerC.gridheight = lettersC.gridheight;
		playerC.gridwidth = lettersC.gridwidth;

		playerPanel.add(playerIcon, playerC);

		// WHAT NEEDS TO BE DONE HERE IS ADDING A KEYLISTENER THAT WILL MOVE THE PLAYER
		// ICON WHEN THE KEYS ARE PRESSED (BY CHANGING playerC.gridx AND playerC.gridy)
		// IN THAT KEYLISTENER, AFTER UPDATING THE PLAYER'S POSITION, CHECK TO SEE IF IT
		// OVERLAPS A LETTER. IF SO, CHECK IF IT'S THE RIGHT LETTER.
		// YES? KEEP GOING. NO? GAME OVER

		// letter overlap check
		/*
		 * for (JLabel let : letters) { if
		 * (playerIcon.getLocation().equals(let.getLocation())) { let.setVisible(false);
		 * if (let.getText().equals(word.getLetter(currentLetter))) { // THE CORRECT
		 * LETTER HAS BEEN CHOSEN. DO NOTHING } else { // THE INCORRECT LETTER HAS BEEN
		 * CHOSEN. END THE GAME } } }
		 */

		gameFrame.add(letterPanel);
		// gameFrame.add(playerPanel);
		gameFrame.pack();
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
