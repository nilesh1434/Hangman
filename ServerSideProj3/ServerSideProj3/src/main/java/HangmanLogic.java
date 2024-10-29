

public class HangmanLogic {
	private int lives;
	private String word;
	private int theme;
	private int lastWordGuess1 = -1;
	private int lastWordGuess2 = -1;
	private int lastWordGuess3 = -1;
	private String displayWord = "";
	private int wins = 0;
	private boolean wonCat1 = false;
	private boolean wonCat2 = false;
	private boolean wonCat3 = false;
	private boolean loser = false;
	private int cat1 = 0;
	private int cat2 = 0;
	private int cat3 = 0;
	
	HangmanLogic(int themeNum) {
		lives = 6;
		theme = themeNum;
		word = ListOfWords.getRandomWord(theme, -1);
		
		int wordLength = word.length();
		for(int x = 0; x < wordLength; x++) {
			displayWord += '_';
		}

	}
	
	public void loseLife() {
		lives--;
		if(lives == 0) {
		if(theme == 1) {
			cat1++;
		} else if (theme == 2) {
			cat2++;
		} else if (theme == 3) {
			cat3++;
		}
		
		if(cat1 == 3 || cat2 == 3 || cat3 == 3) {
			loser = true;
		}
		}
	}
	
	public int getLives() {
		return lives;
	}
	
	public void setLives(int i) {
		lives = i;
	}
	
	public String getWord() {
		return word;
	}
	
	public boolean getLoser() {
		return loser;
	}

	public int getWordS() {
		return word.length();
	}
	
	public String dispWord() {
		return displayWord;
	}
	
	public boolean takeAGuess(String choice) {
		char choiceC = choice.charAt(0);
		boolean safeGuess = false;
		
		for(int x = 0; x < word.length(); x++) {
			char wordCh = Character.toLowerCase(word.charAt(x));
			if(wordCh == choiceC) {
				safeGuess = true;
				displayWord = displayWord.substring(0,x) + choiceC + displayWord.substring(x+1);
			}
		}
		if(!safeGuess) {
			loseLife();
		}
		return safeGuess;
	}
	
	public boolean checkWin() {
		for(int x = 0; x < word.length(); x++) {
			if(displayWord.charAt(x) != '_') {
				continue;
			} else {
				return false;
			}
		}
		if(theme == 1) {
			wonCat1 = true;
		} else if (theme == 2) {
			wonCat2 = true;
		} else if (theme == 3) {
			wonCat3 = true;
		}
		if(wonCat1 && wonCat2 && wonCat3) {
			wins++;
		}
		return true;
	}
	
	public void changeWordCatagorey(int i) {
		int lastWordGuess = -1;
		if(i == 1) {
			lastWordGuess = lastWordGuess1;
		} else if(i == 2) {
			lastWordGuess = lastWordGuess2;
		} else if(i == 3) {
			lastWordGuess = lastWordGuess3;
		}
		word = ListOfWords.getRandomWord(i, lastWordGuess);
		int wordLength = word.length();
		theme = i;
		displayWord = "";
		for(int x = 0; x < wordLength; x++) {
			displayWord += '_';
		}
		lives = 6;
	}
	
	public int getWins() {
		return wins;
	}
	
	public int getTheme() {
		return theme;
	}
	
	public boolean getCat1b() {
		return wonCat1;
	}
	
	public boolean getCat2b() {
		return wonCat2;
	}
	
	public boolean getCat3b() {
		return wonCat3;
	}
	
	public int getCat1() {
		return cat1;
	}
	public int getCat2() {
		return cat2;
	}
	
	public int getCat3() {
		return cat3;
	}
	public void reset() {
		wonCat1 = false;
		wonCat2 = false;
		wonCat3 = false;
		loser = false;
		cat1 = 0;
		cat2 = 0;
		cat3 = 0;
		
	}
	
	
};
