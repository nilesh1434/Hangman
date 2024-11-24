# Hangman Game 

This is a simple Hangman game implemented in Java. The game allows the player to guess letters to form a hidden word. If the player guesses incorrectly multiple times, they "lose" the game. The objective is to guess the word before running out of attempts.

---

## 🎮 About the Game  

Hangman is a classic word-guessing game. The player is given a word with some letters hidden, and they must guess the missing letters. For every incorrect guess, the player loses a life. The game ends when the player either guesses the word or loses all attempts.

This Java implementation of Hangman is a command-line version with a simple interface to interact with the game.

---

## ✨ Features  
- **Guess the Word**: The player tries to guess a hidden word one letter at a time.  
- **Limited Attempts**: The player has a set number of incorrect guesses before the game ends.  
- **Word List**: The game randomly selects a word from a predefined list of words.  
- **Progress Tracking**: The current state of the word is shown after each guess.  
- **User Input**: The player inputs a letter each turn and receives feedback on whether the guess was correct or not.  

---

## 🛠️ Tech Stack  
- **Language**: Java  
- **Libraries**: None (standard Java libraries only)  

---

### 📖 How It Works
Gameplay:

The program selects a word from a predefined list and displays underscores representing the letters.
The player guesses one letter at a time, and the word is updated with correct guesses.
If the player guesses a letter incorrectly, the number of remaining attempts decreases.
The game ends when the player either guesses the word correctly or runs out of attempts.<br><br>
Features:

The player can only guess letters that haven't been guessed before.
Incorrect guesses are shown, and the player can track how many guesses remain.
Winning Condition:

The player wins if they successfully guess the word before running out of attempts. <br><br>

Thank you for playing the Hangman game! 🎉

Happy Coding!!!
