package wordguessinggame;

import java.util.Scanner;

public class GameInputImpl implements GameInput{

    private final Scanner scanner = new Scanner(System.in);
    @Override
    public char readEnteredLetter() throws InputException {
        String input = scanner.nextLine();
        if (input.length() != 1) {
            throw new InputException(InputErrorCode.EMPTY_OR_LONG_INPUT);
        }
        if (!input.matches("[а-яА-Я]") && input.charAt(0) != 'ё') {
            throw new InputException(InputErrorCode.INVALID_CHARACTER);
        }
        System.out.println();
        return input.toLowerCase().charAt(0);
    }
}
