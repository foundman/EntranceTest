package passwordgenerator;

import java.security.SecureRandom;
import java.util.Scanner;

public class Main {

    public static final String LOWERCASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";
    public static final String UPPERCASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String DIGITS = "0123456789";
    public static final String SPECIAL_SYMBOLS = "!@#$%^&*()_+-={}[]|;':\",./<>?";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Добро пожаловать в генератор паролей!");

        while (true) {
            System.out.print("Введите желаемую длину пароля (от 8 до 12 символов): ");
            if (scanner.hasNextInt()) {
                int passwordLength = scanner.nextInt();
                scanner.nextLine();

                if (passwordLength >= 8 && passwordLength <= 12) {
                    String password = generatePassword(passwordLength);
                    System.out.println("Ваш сгенерированный пароль: " + password);

                    System.out.print("Хотите сгенерировать еще один пароль? (Да/Нет): ");
                    String answer = scanner.nextLine().trim().toLowerCase();
                    if (answer.equals("нет")) {
                        System.out.println("До свидания!");
                        break;
                    } else if (!answer.equals("да")) {
                        System.out.println("Некорректный ввод. Попробуйте снова.");
                    }
                } else {
                    System.out.println("Неверная длина пароля. Попробуйте снова.");
                }
            } else {
                System.out.println("Неверный ввод. Пожалуйста, введите целое число.");
                scanner.next();
            }
        }
    }

    private static String generatePassword(int length) {
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder password = new StringBuilder();
        // Чтобы хоть один символ из каждой категории пристутствовал в пароле
        password.append(getRandomCharFromString(LOWERCASE_LETTERS, secureRandom));
        password.append(getRandomCharFromString(UPPERCASE_LETTERS, secureRandom));
        password.append(getRandomCharFromString(DIGITS, secureRandom));
        password.append(getRandomCharFromString(SPECIAL_SYMBOLS, secureRandom));
        String symbolsBank = LOWERCASE_LETTERS + UPPERCASE_LETTERS + DIGITS + SPECIAL_SYMBOLS;
        for (int i = 4; i < length; i++) {
            password.append(getRandomCharFromString(symbolsBank, secureRandom));
        }
        fisherYatesShuffling(password);
        return password.toString();
    }
    // алгоритм тасовки Фишера-Йетса для увеличения безопасности пароля
    private static void fisherYatesShuffling(StringBuilder password) {
        SecureRandom securityRandom = new SecureRandom();
        for (int i = password.length() - 1; i > 0; i--) {
            int j = securityRandom.nextInt(i + 1);
            char temp = password.charAt(i);
            password.setCharAt(i, password.charAt(j));
            password.setCharAt(j, temp);
        }
    }
    // получаем рандомный символ для использования в пароле
    private static char getRandomCharFromString(String str, SecureRandom random) {
        int randomIndex = random.nextInt(str.length());
        return str.charAt(randomIndex);
    }
}

