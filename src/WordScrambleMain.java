import javax.swing.JFrame;

public class WordScrambleMain {
	public static void main(String[] strings) {
		//WordScramble wsgame = new WordScramble();
		JFrame f = new JFrame();
		MazeDraw md = new MazeDraw("Difficult");
		f.add(md);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(646, 669);
	    f.setLocationRelativeTo(null);
		f.setVisible(true);
	}

}