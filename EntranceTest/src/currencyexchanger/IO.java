package currencyexchanger;

import java.math.BigDecimal;
import java.util.Scanner;
import java.util.stream.IntStream;

public class IO {
    public static void printAvailableCurrencies(){
        System.out.println("Доступные для перевода валюты: ");
        int counter = 1;
        for (Currency currency : Currency.values()){
            System.out.println(counter + " — " + currency.getCurrencyName());
            counter++;
        }
        System.out.println();
    }
    public static boolean askForNewRates() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                Хотите ввести собственные курсы валют? (Да/Нет)
                
                Обратите внимание, что в противном случае будует использоваться
                константные курсы валют, зафиксированные 01.11.2024 в 18:00,
                которые могут быть не актуальны на момент использования Вами программы.
                
                Вводите курсы валют онтосительно доллара, используя точку (к примеру, 0.01).
                
                """);
        String input = scanner.nextLine().trim().toLowerCase();
        if (input.equals("да")) {
            return true;
        } else if (input.equals("нет")) {
            return false;
        } else {
            System.out.println("Некорректный ввод. Пожалуйста, введите \"Да\" или \"Нет\".");
            return askForNewRates();
        }
    }
    public static BigDecimal[] enterNewRates() {
        Scanner scanner = new Scanner(System.in);
        BigDecimal[] newRates = new BigDecimal[Currency.values().length];
        IntStream.range(1, Currency.values().length)
                .forEach(i -> {
                    Currency currency = Currency.values()[i];
                    System.out.printf("Введите курс для %s: ", currency.getCurrencyName());
                    while (true) {
                        String rateInput = scanner.nextLine();
                        try {
                            newRates[i] = new BigDecimal(rateInput);
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Некорректный ввод. Пожалуйста, введите правильный курс.");
                        }
                    }
                });
        return newRates;
    }
    public static Currency enterCurrency() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String enteredCurrency = scanner.nextLine().toUpperCase();
            try {
                return Currency.valueOf(enteredCurrency);
            } catch (IllegalArgumentException e) {
                System.out.println("Неверный код валюты. Попробуйте снова.");
            }
        }
    }
    public static Currency enterInitialCurrency(){
        System.out.println("Введите валюту, ИЗ которой хотите перевести (к примеру, USD).");
        return enterCurrency();
    }
    public static Currency enterFinalCurrency(){
        System.out.println("Введите валюту, В которую хотите перевести (к примеру, BYN).");
        return enterCurrency();
    }
    public static BigDecimal enterAmount() {
        Scanner scanner = new Scanner(System.in);
        BigDecimal amount;

        while (true) {
            System.out.print("Введите количество валюты: ");
            String input = scanner.nextLine();

            try {
                amount = new BigDecimal(input);
                if (amount.signum() < 0) {
                    System.out.println("Неправильный ввод. Количество валюты не может быть отрицательным.");
                } else {
                    return amount;
                }
            } catch (NumberFormatException e) {
                System.out.println("Неправильный ввод. Неверный формат числа.");
            }
        }
    }
    public static void printResult(Currency initialCurrency, Currency finalCurrency, BigDecimal amount,
                                   BigDecimal convertedAmount, BigDecimal convertedAmountReversed) {
        System.out.println("Вот результаты конвертирования:");
        System.out.printf(" %.2f %s = %.2f %s%n",
                amount, initialCurrency, convertedAmount, finalCurrency);
        System.out.printf(" %.2f %s = %.2f %s%n",
                amount, finalCurrency, convertedAmountReversed, initialCurrency);
    }
    public static boolean askRepetition(Main.RepetitionType type) {
        Scanner scanner = new Scanner(System.in);
        switch (type) {
            case SAME_CURRENCIES:
                System.out.println("Хотите произвести конвертацию с теми же валютами? (Да/Нет)");
                break;
            case NEW_CURRENCIES:
                System.out.println("Хотите произвести конвертацию с другими валютами? (Да/Нет)");
                break;
        }
        String input = scanner.nextLine().trim().toLowerCase();
        if (input.equals("да")) {
            return true;
        } else if (input.equals("нет")) {
            return false;
        } else {
            System.out.println("Некорректный ввод. Пожалуйста, введите \"Да\" или \"Нет\".");
            return askRepetition(type);
        }
    }
}