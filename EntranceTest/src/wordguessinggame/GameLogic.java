package wordguessinggame;

public class GameLogic {

    public enum GameState {
        GAME_LOST,
        GAME_WON,
        GAME_IN_PROCESS
    }

    private final GameInputImpl input;


    public GameLogic(GameInputImpl input){
        this.input = input;
    }
    public void start(int livesGiven){
        WordLogic wordLogic = new WordLogic(WordBank.getWords());
        GameState gameState;
        int livesRemaining = livesGiven;
        do{
            GameOutput.printUI( livesGiven,
                    livesRemaining,
                    wordLogic.getGuessedWord(),
                    wordLogic.getAreLettersGuessed(),
                    wordLogic.getWrongLetters()
            );
            GameOutput.printBeforeLetterEntered();
            char letter = 0;
            boolean inputPassed = false;
            while (!inputPassed) {
                try {
                    letter = input.readEnteredLetter();
                    boolean letterAlreadyOpened = wordLogic.checkIfLetterAlreadyOpened(letter);
                    if(letterAlreadyOpened){
                        GameOutput.reportEnteredAlreadyOpenedLetter();
                    }
                    inputPassed = !letterAlreadyOpened;
                } catch (InputException e) {
                    e.handleError(e.getErrorCode());
                }
            }
            if(!wordLogic.checkAndSortAnswer(letter)){
                livesRemaining--;
            }
            gameState = checkGameState(livesRemaining, wordLogic.areAllLettersGuessed());
        } while (gameState == GameState.GAME_IN_PROCESS);
        GameOutput.printGameResult(gameState, wordLogic.getGuessedWord(), livesGiven, livesRemaining);
    }
    public static GameState checkGameState(int livesRemaining, boolean allLettersGuessed){
        if (allLettersGuessed){
            return GameState.GAME_WON;
        } else {
            if (livesRemaining > 0){
                return GameState.GAME_IN_PROCESS;
            } else { return GameState.GAME_LOST; }
        }
    }
}
