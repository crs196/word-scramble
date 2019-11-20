import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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
	private static JPanel letterPanel;
	private static JButton startButton, exitButton;
	private static JComboBox<String> difficultySelector;
	private static String[] difficulties;
	private static String difficulty;
	private List<JLabel> letters;

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
		gameFrame.getContentPane().setBackground(Color.CYAN);
		gameFrame.setLayout(new FlowLayout());

		letterPanel = new JPanel();
		Player player = new Player();
		LetterGenerator letter = new LetterGenerator(difficulty);
		JLabel[] letters = new JLabel[letter.getWord().length()];

		// add letters to letterPanel
		letterPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		// for each letter in the goal word
		for (int i = 0; i < letter.getWord().length(); i++) {
			letters[i] = new JLabel(letter.getLetter(i)); // make a JLabel with that letter in it
			letters[i].setPreferredSize(new Dimension(75, 75)); // set its preferred size larger
			letters[i].setFont(new Font("Helvetica", 1, 30)); // set the font, make it bold, and set font size
			c.gridx = (int) (Math.random() * 35); // place it in a random spot in the GridBagLayout
			c.gridy = (int) (Math.random() * 52);
			letterPanel.add(letters[i], c);
		}

		gameFrame.add(letterPanel);
		gameFrame.add(player);
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
