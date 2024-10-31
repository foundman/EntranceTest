package wordguessinggame;

public class InputException extends Exception {
    private final InputErrorCode inputErrorCode;
    public InputException(InputErrorCode inputErrorCode) {
        this.inputErrorCode = inputErrorCode;
    }

    public InputErrorCode getErrorCode() {
        return inputErrorCode;
    }
    public void handleError(InputErrorCode errorCode){
        GameOutput.reportInputError(errorCode);
    }
}
