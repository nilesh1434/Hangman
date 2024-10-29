import java.util.Random;



public class ListOfWords {
	private static String[] foodArr = new String[] {"Asparagus", "Burrito", "Brownie", "Cheesecake", "Dragonfruit",
			"Guacamole", "Doughnut", "Lasagna", "Lobster", "Noodles", "Oyster", "Pumpkin", "Salmon", "Turnip", "Turkey", "Waffle", 
			"Lettuce", "Mushroom", "Oatmeal", "Pomegranate"};
	private static String[] countriesArr = new String[] {"China", "Mexico", "Indonesia", "Singapore", "Tonga",
			"Grenada", "Korea", "Zimbabwe", "Canada", "Belgium", "Morocco", "Malaysia", "Russia", "Finland", "Uzbekistan", "Japan",
			"Switzerland", "Lithuania", "Kazakhstan", "America"};
	private static String[] sportArr = new String[] {"Soccer", "Cricket", "Basketball", "Baseball", "Volleyball",
			"Hockey", "Football", "Rugby", "Lacrosse", "Tennis", "Badminton", "Golf", "Boxing", "Swimming", "Cycling", "Track",
			"Bowling", "Gymnastics", "Paintball", "Esports"};
	
	public static String getRandomWord(int theme, int lastWord) {
		String randomWord = "";
		int randomNumber = lastWord;
		while (randomNumber == lastWord) {
			randomNumber = (int) (Math.random()*20);
			if(theme == 1) {
				randomWord = foodArr[randomNumber];
			}
			if(theme == 2) {
				randomWord = countriesArr[randomNumber];
			}
			if(theme == 3) {
				randomWord = sportArr[randomNumber];
			}
		}
		lastWord = randomNumber;
		return randomWord;
	}
};
