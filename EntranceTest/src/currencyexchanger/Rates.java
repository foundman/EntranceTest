package currencyexchanger;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.HashMap;
import java.util.Map;

import static currencyexchanger.Currency.*;

public class Rates {

    public static final BigDecimal EUR_TO_USD = new BigDecimal("1.09");
    public static final BigDecimal CBY_TO_USD = new BigDecimal("0.14041");
    public static final BigDecimal RUB_TO_USD = new BigDecimal("0.0103069");
    public static final BigDecimal BYN_TO_USD = new BigDecimal("0.30155");
    public static final BigDecimal KZT_TO_USD = new BigDecimal("0.00204447");


    public static Map<Currency, BigDecimal> exchangeRates = new HashMap<>();

    static {
        putDefaultRates();
    }
    public static void updateRates(BigDecimal[] rates) {
        if (rates.length != Currency.values().length) {
            throw new IllegalArgumentException("Неверное количество курсов в массиве");
        }
        for (int i = 1; i < Currency.values().length; i++) {
            try {
                exchangeRates.put(Currency.values()[i], rates[i]);
            } catch (NumberFormatException e) {
                System.err.println("Ошибка преобразования курса для " + Currency.values()[i].getCurrencyName() + ". Курс не обновлен.");
            }
        }
    }
    public static BigDecimal getRate(Currency initialCurrency, Currency finalCurrency) {
        if (initialCurrency == finalCurrency) {
            return BigDecimal.ONE;
        }
        return exchangeRates.get(finalCurrency).divide(exchangeRates.get(initialCurrency),
                MathContext.DECIMAL128);
    }
    private static void putDefaultRates(){
        exchangeRates.put(USD, new BigDecimal("1.0000"));
        exchangeRates.put(EUR, EUR_TO_USD);
        exchangeRates.put(CNY, CBY_TO_USD);
        exchangeRates.put(RUB, RUB_TO_USD);
        exchangeRates.put(BYN, BYN_TO_USD);
        exchangeRates.put(KZT, KZT_TO_USD);
    }
}
