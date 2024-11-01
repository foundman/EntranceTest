package wordguessinggame;

import java.util.Scanner;

public class Main {
    // Указываем полный путь к файлу
    // количества жизней для различных уровней игры
    public enum Difficulty {
        EASY(8),
        MEDIUM(7),
        HARD(6);
        private final int lives;

        Difficulty(int lives) {
            this.lives = lives;
        }

        public int getLives() {
            return lives;
        }
    }

    public static final String GREETING = """
      Добро пожаловать в игру "Виселица"!
      ***********************************
      Вам необходимо отгадать случайное слово-существительное
      за ограниченное число попыток,
      если человечек полностью повиснет на верёвке, вы проиграете!
      Вводите за раз по букве.
      Удачи!
      ~~~~~
      """;
    public static final String OFFERING_REPLAY = "Хотите ли сыграть снова? Напишите в консоль (Д/Н)";
    public static final String FAREWELL = "Благодарим Вас за время, уделённое этой игре! Возвращайтесь в любое время!";
    public static final String CHOOSING_DIFFICULTY = """
      Выберете уровень сложности:
      %d — лёгкий уровень,
      %d — средний уровень,
      %d — сложный уровень.
      Напишите в консоль число жизней.
      \s""".formatted(Difficulty.EASY.getLives(), Difficulty.MEDIUM.getLives(), Difficulty.HARD.getLives());

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(GREETING);

        GameLogic gameLogic = new GameLogic(new GameInputImpl());
        boolean playerLeft;
        do {
            System.out.println(CHOOSING_DIFFICULTY);
            int livesGiven = getChosenDifficulty();
            gameLogic.start(livesGiven);
            System.out.println(OFFERING_REPLAY);
            playerLeft = getPlayerAnswer();
        } while (!playerLeft);
        System.out.println(FAREWELL);
        scanner.next();
    }

    public static int getChosenDifficulty() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            try {
                int difficulty = Integer.parseInt(input.trim());
                if (difficulty == Difficulty.EASY.getLives() ||
                        difficulty == Difficulty.MEDIUM.getLives() ||
                        difficulty == Difficulty.HARD.getLives()) {
                    return difficulty;
                } else {
                    System.out.println("Некорректный ввод. Введите 8, 7 или 6.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Некорректный ввод. Введите числа 8, 7 или 6..");
            }
        }
    }

    // принимаем ответ игрока с его желанием играть дальше
    public static boolean getPlayerAnswer() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String answer = scanner.nextLine().toUpperCase();
            if (answer.equals("Д")) {
                return false;
            } else if (answer.equals("Н")) {
                return true;
            } else {
                System.out.println("Некорректный ввод. Введите Д или Н.");
            }
        }
    }
}