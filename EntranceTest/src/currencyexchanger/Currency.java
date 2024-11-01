package currencyexchanger;

public enum Currency {
    USD("Доллар США"),
    EUR("Евро"),
    CNY("Китайский юань"),
    RUB("Российский рубль"),
    BYN("Белорусский рубль"),
    KZT("Казахстанский тенге");

    private final String currencyName;

    Currency(String currencyName){
        this.currencyName = currencyName;
    }
    public String getCurrencyName(){
        return currencyName;
    }
    public static int getNumOfCurrencies(){
        return Currency.values().length;
    }
}
