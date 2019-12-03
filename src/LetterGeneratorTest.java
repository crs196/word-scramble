import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LetterGeneratorTest {

	@Test
	void checkLetterShouldGiveProperLetter() {
		LetterGenerator lg = new LetterGenerator("Easy");
		String word = lg.getWord();
		
		for (int i = 0; i < word.length(); i++) {
			assertEquals(word.charAt(i) + "", lg.getLetter(i));
		}
		
		lg = new LetterGenerator("Medium");
		word = lg.getWord();
		
		for (int i = 0; i < word.length(); i++) {
			assertEquals(word.charAt(i) + "", lg.getLetter(i));
		}
		
		lg = new LetterGenerator("Difficult");
		word = lg.getWord();
		
		for (int i = 0; i < word.length(); i++) {
			assertEquals(word.charAt(i) + "", lg.getLetter(i));
		}
	}

}
