import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MyTests {
	
	
	@Test
	void testHangmanCat1() {
		HangmanLogic hm = new HangmanLogic(1);
		String word = hm.getWord();

		for(int x = 0; x < word.length(); x++) {
			char c = Character.toLowerCase(word.charAt(x));
			String cc = Character.toString(c);
			assertEquals(hm.takeAGuess(cc), true);
		}
		assertEquals(hm.checkWin(), true);
	}
	
	@Test
	void testHangmanCat2() {
		HangmanLogic hm = new HangmanLogic(2);
		String word = hm.getWord();

		for(int x = 0; x < word.length(); x++) {
			char c = Character.toLowerCase(word.charAt(x));
			String cc = Character.toString(c);
			assertEquals(hm.takeAGuess(cc), true);
		}
		assertEquals(hm.checkWin(), true);
	}
	
	@Test
	void testHangmanCat3() {
		HangmanLogic hm = new HangmanLogic(3);
		String word = hm.getWord();

		for(int x = 0; x < word.length(); x++) {
			char c = Character.toLowerCase(word.charAt(x));
			String cc = Character.toString(c);
			assertEquals(hm.takeAGuess(cc), true);
		}
		assertEquals(hm.checkWin(), true);
	}
	
	@Test
	void testLoseLife() {
		HangmanLogic hm = new HangmanLogic(1);
		hm.loseLife();
		assertEquals(hm.getLives(), 5);
		assertEquals(hm.checkWin(), false);
		hm.loseLife();
		hm.loseLife();
		hm.loseLife();
		hm.loseLife();
		hm.loseLife();
		assertEquals(hm.getLives(), 0);
		assertEquals(hm.getCat1(), 1);
		assertEquals(hm.getCat2(), 0);
	}
	
	@Test
	void testReset() {
		HangmanLogic hm = new HangmanLogic(3);
		String word = hm.getWord();

		for(int x = 0; x < word.length(); x++) {
			char c = Character.toLowerCase(word.charAt(x));
			String cc = Character.toString(c);
			assertEquals(hm.takeAGuess(cc), true);
		}
		assertEquals(hm.checkWin(), true);
		hm.reset();
		assertEquals(hm.getCat3(), 0);
	}
	
	@Test
	void testChangeCatagorey() {
		HangmanLogic hm = new HangmanLogic(3);
		assertEquals(hm.getTheme(), 3);
		String word = hm.getWord();
		hm.changeWordCatagorey(1);
		assertEquals(hm.getTheme(), 1);
		assertNotEquals(hm.getWord(), word);
	}
	
	@Test
	void testWinAll3() {
		HangmanLogic hm = new HangmanLogic(1);
		String word = hm.getWord();

		for(int x = 0; x < word.length(); x++) {
			char c = Character.toLowerCase(word.charAt(x));
			String cc = Character.toString(c);
			assertEquals(hm.takeAGuess(cc), true);
		}
		assertEquals(hm.checkWin(), true);
		hm.changeWordCatagorey(2);
		
		word = hm.getWord();
		for(int x = 0; x < word.length(); x++) {
			char c = Character.toLowerCase(word.charAt(x));
			String cc = Character.toString(c);
			assertEquals(hm.takeAGuess(cc), true);
		}
		assertEquals(hm.checkWin(), true);
		hm.changeWordCatagorey(3);
		word = hm.getWord();
		for(int x = 0; x < word.length(); x++) {
			char c = Character.toLowerCase(word.charAt(x));
			String cc = Character.toString(c);
			assertEquals(hm.takeAGuess(cc), true);
		}
		assertEquals(hm.checkWin(), true);
		
		assertEquals(hm.getWins(), 1);
		
	}
	
	@Test
	void testCheckLoss() {
		HangmanLogic hm = new HangmanLogic(3);
		assertEquals(hm.getLoser(), false);
		hm.loseLife();
		hm.loseLife();
		hm.loseLife();
		hm.loseLife();
		hm.loseLife();
		hm.loseLife();
		hm.setLives(6);
		hm.loseLife();
		hm.loseLife();
		hm.loseLife();
		hm.loseLife();
		hm.loseLife();
		hm.loseLife();
		hm.setLives(6);
		hm.loseLife();
		hm.loseLife();
		hm.loseLife();
		hm.loseLife();
		hm.loseLife();
		hm.loseLife();

		
		assertEquals(hm.getLoser(), true);
	}
	
	

}
