package wordguessinggame;

import java.util.Arrays;
import java.util.List;


public class GameOutput {
    public static final char UNREVEALED_LETTER = '_';
    public static final String[] HANGMAN_ILLUSTRATIONS = {
                    """
         +_______+
         | /     |
         |/      O
         |      /|\\
         |      / \\
         |
         |
        ———""",
                    """
         +_______+
         | /     |
         |/      O
         |      /|\\
         |      /
         |
         |
        ———""",
                    """
         +_______+
         | /     |
         |/      O
         |      /|\\
         |
         |
         |
        ———""",
                    """
         +_______+
         | /     |
         |/      O
         |      /|
         |
         |
         |
        ———""",
                    """
         +_______+
         | /     |
         |/      O
         |       |
         |
         |
         |
        ———""",
                    """
         +_______+
         | /     |
         |/      O
         |
         |
         |
         |
        ———""",
                    """
         +_______+
         | /     |
         |/
         |
         |
         |
         |
        ———""",
                    """
         +_______+
         | /
         |/
         |
         |
         |
         |
        ———""",     """
         +
         |
         |
         |
         |
         |
         |
        ———"""
    };

    // обработка ошибок ввода
    static void reportInputError(InputErrorCode error){
        switch (error){
            case EMPTY_OR_LONG_INPUT -> System.out.println("Вы ввели больше одного символа, либо не ввели ничего.");
            case INVALID_CHARACTER -> System.out.println("Вы ввели символ, не являющийся русской буквой.");
        }
    }
    static void reportEnteredAlreadyOpenedLetter(){
        System.out.println("Вы ввели уже открытую букву!");
    }
    // метод возвращает строку, в которой буквы через пробел. Используется в отображении неправильных букв
    static String convertToString(List<Character> letters) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < letters.size(); i++) {
            builder.append(letters.get(i));
            if (i < letters.size() - 1) {
                builder.append(" ");
            }
        }
        return builder.toString();
    }
    static void printUI(int livesGiven,
                        int livesRemaining,
                        String guessedWord,
                        boolean[] areLettersGuessed,
                        List<Character> wrongLetters
                        )
    {
                printHangmanPic(livesGiven, livesRemaining);
                printRevealedWord(guessedWord, areLettersGuessed);
                printWrongLetters(wrongLetters);
    }
    static void printGameResult(GameLogic.GameState gameState, String guessedWord,int livesGiven, int livesRemaining){
        switch (gameState) {
            case GAME_LOST: {
                printHangmanPic(livesGiven, 0);
                printLostMessage(guessedWord);
                break;
            }
            case GAME_WON: {
                printHangmanPic(livesGiven, livesRemaining);
                printWonMessage(guessedWord, livesGiven, livesRemaining);
                break;
            }
        }
    }
    private static void printLostMessage(String guessedWord){
        System.out.println("Вы проиграли! Загаданное слово было «" + guessedWord + "».");
    }
    private static void printWonMessage(String guessedWord, int livesGiven, int livesRemaining){
        int livesWasted = livesGiven - livesRemaining;
        String attemptsWord = switch (livesWasted % 10) {
            case 1 -> "попытку";
            case 2, 3, 4 -> "попытки";
            default -> "попыток";
        };
        System.out.println("Бинго! Вы угадали слово «" + guessedWord + "» за " + livesWasted + " " + attemptsWord +  "!");
    }

    private static void printHangmanPic(int livesGiven, int livesRemaining){
        if (livesRemaining != 0) {
            System.out.println("Осталось попыток: " + livesRemaining + "/" + livesGiven);
        } else { System.out.println("У вас закончились жизни!"); }
        System.out.println(HANGMAN_ILLUSTRATIONS[livesRemaining]);
    }
    private static void printRevealedWord(String guessedWord, boolean[] areLettersRevealed){
        char[] revealedWord = new char[guessedWord.length()];
        Arrays.fill(revealedWord, UNREVEALED_LETTER);
        char[] guessedWordSplited = guessedWord.toCharArray();
        //char[] revealedWord = UNREVEALED_WORD;
        for (int i = 0; i < revealedWord.length; i++){
            if (areLettersRevealed[i]) revealedWord[i] = guessedWordSplited[i];
            System.out.print(revealedWord[i] + " ");
        }
    }
    private static void printWrongLetters(List<Character> wrongLetters){
        System.out.print("Неправильные буквы: " + convertToString(wrongLetters));
        System.out.println();
    }
    static void printBeforeLetterEntered(){
        System.out.print("Введите букву: ");
    }
}