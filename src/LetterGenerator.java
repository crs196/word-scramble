import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LetterGenerator {

	private List<String> words;
	private BufferedReader reader;
	private String chosenWord;

	public LetterGenerator(String diff) {

		//sets up reader to read from the dictionary of proper difficulty
		switch (diff) {
		case "Easy":
			try {
				reader = new BufferedReader(new FileReader("dictionary/easy.txt"));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			break;
		case "Medium":
			try {
				reader = new BufferedReader(new FileReader("dictionary/medium.txt"));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			break;
		case "Difficult":
			try {
				reader = new BufferedReader(new FileReader("dictionary/difficult.txt"));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			break;
		}

		// add words from chosen dictionary to arrayList

		words = new ArrayList<String>();
		String temp;

		try {
			while ((temp = reader.readLine()) != null) {
				words.add(temp.toUpperCase());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		//randomly chooses a word from the dictionary
		chosenWord = words.get((int) (Math.random() * words.size()));
	}

	public String getWord() {
		return chosenWord;
	}

	//returns the specified letter of the word
	public String getLetter(int index) {
		if (index >= chosenWord.length())
			return "";
		else
			return chosenWord.charAt(index) + "";
	}
}