package info.vladimirov.pluralsight.mockito.section2;


import info.vladimirov.pluralsight.mockito.section2.api.AuditReport;
import info.vladimirov.pluralsight.mockito.section2.api.Exchange;
import info.vladimirov.pluralsight.mockito.section2.api.MarketData;

public class TradingApp {

    private final AuditReport audit;
    private final MarketData marketData;
    private final Exchange exchange;

    public TradingApp(AuditReport audit, MarketData marketData, Exchange exchange) {
        this.audit = audit;
        this.marketData = marketData;
        this.exchange = exchange;
    }


    public boolean executeTradeGuarantee(String symbol, int qty, double price) {

        try {
            for(int i = 0; i < 10; i++) {
                boolean executionStatus = marketData.isCurrentOrderPossible(symbol, qty, price);

                if (executionStatus) {
                    //execute order
                    if(exchange.execute(symbol, qty, price)) {
                        audit.reportTrade("Trade for symbol " + symbol + " executed.", qty, price);
                        return true;
                    }
                }
                Thread.sleep(10);
            }

        } catch (InterruptedException e) {
            //intentionally left
        }
        audit.reportFailedTrade("Trade for symbol " + symbol + " failed.", qty, price);
        return false;

    }


}