package info.vladimirov.pluralsight.mockito.section2.api;

public interface MarketData {
    boolean isCurrentOrderPossible(String symbol, int qty, double price);
}
