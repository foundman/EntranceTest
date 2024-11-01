package currencyexchanger;

import java.math.BigDecimal;
import java.math.MathContext;

public class Main {

    public static final String INTRODUCTION = """
            Добро пожаловать в программу, позволяющую
            получать курс одной валюты относительно другой!
            """;
    public enum RepetitionType{
        SAME_CURRENCIES,
        NEW_CURRENCIES
    }
    public static void main(String[] args) {
        System.out.println(INTRODUCTION);
        IO.printAvailableCurrencies();
        if (IO.askForNewRates()) {
            BigDecimal[] newRates = IO.enterNewRates();
            Rates.updateRates(newRates);
            System.out.println("Курсы валют обновлены на введённые Вами.");
        } else {
            System.out.println("Используются стандартные курсы валют.");
        }
        boolean userLeft;
        do{
            Currency initialCurrency = IO.enterInitialCurrency();
            Currency finalCurrency = IO.enterFinalCurrency();
            boolean userEntersNewCurrencies;
            do {
                BigDecimal amount = IO.enterAmount();
                BigDecimal convertedAmount = convert(amount, Rates.getRate(initialCurrency, finalCurrency));
                BigDecimal convertedAmountReversed = convert(amount,
                        reverseRate(Rates.getRate(initialCurrency, finalCurrency)));
                IO.printResult(initialCurrency, finalCurrency, amount, convertedAmount, convertedAmountReversed);
                userEntersNewCurrencies = !IO.askRepetition(RepetitionType.SAME_CURRENCIES);
            } while (!userEntersNewCurrencies);
            userLeft = !IO.askRepetition(RepetitionType.NEW_CURRENCIES);
        } while (!userLeft);
    }

    public static BigDecimal convert(BigDecimal amount, BigDecimal rate) {
        if (amount == null || rate == null) {
            throw new IllegalArgumentException("Значения amount и rate не могут быть null.");
        }
        if (rate.compareTo(BigDecimal.ZERO) == 0) {
            throw new IllegalArgumentException("Курс не может быть равен нулю.");
        }
        return amount.divide(rate, MathContext.DECIMAL128);
    }
    public static BigDecimal reverseRate(BigDecimal Amount) {
        if (Amount.compareTo(BigDecimal.ZERO) != 0) {
            return BigDecimal.ONE.divide(Amount, MathContext.DECIMAL128);
        } else {
            return BigDecimal.ZERO;
        }
    }
}
