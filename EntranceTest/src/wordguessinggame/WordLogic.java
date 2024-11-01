package wordguessinggame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class WordLogic {


    private final String guessedWord;
    public String getGuessedWord(){
        return guessedWord;
    }

    public List<Character> getGuessedLetters() {
        return guessedLetters;
    }

    public List<Character> getWrongLetters() {
        return wrongLetters;
    }

    public boolean[] getAreLettersGuessed() {
        return areLettersGuessed;
    }

    private final List<Character> guessedLetters = new ArrayList<>();
    private final List<Character> wrongLetters = new ArrayList<>();

    private final boolean[] areLettersGuessed;

    public WordLogic(List<String> words){
        this.guessedWord = takeRandomWord(words);
        this.areLettersGuessed = new boolean[guessedWord.length()];
    }
    
    public String takeRandomWord(List<String> words){
        Random random = new Random();
        int randomWordIndex = random.nextInt(words.size());

        return words.get(randomWordIndex);
    }

    public boolean checkIfLetterAlreadyOpened(char enteredLetter){
        return guessedLetters.contains(enteredLetter) || wrongLetters.contains(enteredLetter);
    }
    public boolean areAllLettersGuessed() {
        for (boolean isLetterGuessed : areLettersGuessed) {
            if (!isLetterGuessed) {
                return false;
            }
        }
        return true;
    }
    public void sortAnswer(char enteredLetter, boolean isCorrect){
        if (isCorrect){
            guessedLetters.add(enteredLetter);
        } else {
            wrongLetters.add(enteredLetter);
        }
    }
    public boolean checkAndSortAnswer(char enteredLetter){
        boolean isLetterRight = false;
        char[] guessedWordSplited = guessedWord.toCharArray();
        for (int i = 0; i < guessedWordSplited.length; i++) {
            if (enteredLetter == guessedWordSplited[i]) {
                areLettersGuessed[i] = true;
                isLetterRight = true;
            }
        }
        sortAnswer(enteredLetter, isLetterRight);
        return isLetterRight;
    }
}
