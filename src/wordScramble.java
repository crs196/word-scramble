import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class wordScramble {
	 /**
     * mJFrame is the window before the game, user can choose game difficulty level
     * jFrambe is the window after game started, user play the game in this window
     *
     */
    private static JFrame mJFrame, jFrame;
    private static JLabel mJLabel, mJLabel1;
    private static JPanel mJPanel, mJPanel2;
    private static JButton mJButton, mJButton2;
    private static JSpinner mJSpinner;
    private static String[] strings;
    private static int number = 20;
    
    public wordScramble() {
        init_J();
        mJFrame.setBounds(500, 200, 300, 450);
        mJFrame.setBackground(Color.BLUE);
        mJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mJFrame.setLayout(new BorderLayout(10, 10));
        mJPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        mJPanel.add(mJButton);
        mJPanel.add(mJLabel1);
        SpinnerModel model = new SpinnerListModel(strings);
        mJSpinner = new JSpinner(model);
        mJSpinner.setSize(50, 50);
        mJPanel.add(mJSpinner);
        mJPanel.add(mJButton2);
        ImageIcon mIcon = new ImageIcon("src/UI/title.png");
        mJLabel = new JLabel(mIcon);
        mJPanel2.add(mJLabel);
        mJFrame.add(mJPanel, BorderLayout.SOUTH);
        mJFrame.add(mJPanel2, BorderLayout.CENTER);
        mJFrame.setResizable(false);
        mJFrame.setVisible(true);
        Listen();
    }
    
    private void init_J() {
        mJFrame = new JFrame("Word Scramble");
        jFrame = new JFrame("Word Scramble");
        mJPanel = new JPanel();
        mJPanel2 = new JPanel();
        mJButton = new JButton("Start");
        mJLabel1 = new JLabel("Choose Difficulty");
        mJButton2 = new JButton("Exit");
        strings = new String[]{"Easy", "Midium", "Difficult"};
    }
    
    
    private void Listen() {
        mJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                mJFrame.setVisible(false);
                jFrame.getContentPane().setBackground(Color.CYAN);
                Player p = new Player();
                letterGenerator letter = new letterGenerator();
                jFrame.add(p);
                jFrame.setBounds(500, 200, 320, 480);
                jFrame.setResizable(false);
                jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                jFrame.setVisible(true);

            }
        });
        mJSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                switch (mJSpinner.getValue().toString()) {
                    case "Easy":
                        //need word.length < 5
                        break;
                    case "Midium":
                        //need word.length >= 5 && letter.length < 10
                        break;
                    case "Difficult":
                        //need word.length >= 10
                        break;
                    default:
                        break;
                }

            }
        });
        mJButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                mJFrame.dispose();
            }
        });
    }
}
