package info.vladimirov.pluralsight.mockito.module3;

import info.vladimirov.pluralsight.mockito.section2a.TradingApp;
import info.vladimirov.pluralsight.mockito.section2a.api.AuditReport;
import info.vladimirov.pluralsight.mockito.section2a.api.Exchange;
import info.vladimirov.pluralsight.mockito.section2a.api.MarketData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class Example4_1 {

    /**
     * Return values
     */

    @Test
    void executeTradeGuaranteeMultipleReturnValues() {
        AuditReport auditRepo = Mockito.mock(AuditReport.class);
        Exchange exchange = mock(Exchange.class);
        MarketData marketData = mock(MarketData.class);

        TradingApp tradingApp = new TradingApp(auditRepo, marketData, exchange);

        final String symbol = "PS";
        double px = 22.5;
        int qty = 100;

        Mockito.when(marketData.isCurrentOrderPossible(Mockito.anyString(),
                Mockito.anyInt(), Mockito.anyDouble())).thenReturn(true);

        Mockito.when(exchange.execute(
                        Mockito.anyString(), Mockito.anyInt(), Mockito.anyDouble()))
                //iterate the return values
                .thenReturn(false, false, true);


        Mockito.when(exchange.execute(
                        Mockito.anyString(), Mockito.anyInt(), Mockito.anyDouble()))
                //iterate the return values
                .thenReturn(false, false, true);

        boolean executionOutcome = tradingApp.executeTradeGuarantee(symbol, qty, px);
        Assertions.assertTrue(executionOutcome);

        Mockito.verify(marketData, atLeast(2)).isCurrentOrderPossible(Mockito.anyString(),
                Mockito.anyInt(), Mockito.anyDouble());

        Mockito.verify(auditRepo, times(2))
                .reportFailedTrade(Mockito.anyString(),
                        Mockito.anyInt(), Mockito.anyDouble());

        Mockito.verify(auditRepo).reportTrade(Mockito.anyString(),
                Mockito.anyInt(), Mockito.anyDouble());

    }


    @Test
    void executeTradeGuaranteeDoublePrecision() {
        AuditReport auditRepo = Mockito.mock(AuditReport.class);
        Exchange exchange = mock(Exchange.class);
        MarketData marketData = mock(MarketData.class);

        TradingApp tradingApp = new TradingApp(auditRepo, marketData, exchange);

        final String symbol = "PS";
        double px = 22.5;
        int qty = 100;

        Mockito.when(marketData.isCurrentOrderPossible(Mockito.anyString(),
                Mockito.anyInt(), Mockito.anyDouble())).thenReturn(true);

        Mockito.when(exchange.execute(
                        Mockito.anyString(), Mockito.anyInt(), Mockito.anyDouble()))
                //iterate the return values
                .thenReturn(false, false, true);


        Mockito.when(exchange.execute(
                        Mockito.anyString(), Mockito.anyInt(), Mockito.anyDouble()))
                //iterate the return values
                .thenReturn(false, false, true);

        boolean executionOutcome = tradingApp.executeTradeGuarantee(symbol, qty, px);
        Assertions.assertTrue(executionOutcome);

        Mockito.verify(marketData, atLeast(2)).isCurrentOrderPossible(Mockito.anyString(),
                Mockito.anyInt(), Mockito.eq(px));

        Mockito.verify(auditRepo, times(2))
                .reportFailedTrade(Mockito.anyString(),
                        Mockito.anyInt(), Mockito.eq(px));

        Mockito.verify(auditRepo).reportTrade(Mockito.anyString(),
                Mockito.anyInt(), Mockito.eq(px));

    }
    @Test
    void executeTradeGuaranteeMockWithRealData() {
        AuditReport auditRepo = Mockito.mock(AuditReport.class);
        Exchange exchange = mock(Exchange.class);
        MarketData marketData = mock(MarketData.class);

        TradingApp tradingApp = new TradingApp(auditRepo, marketData, exchange);

        final String symbol = "PS";
        double px = 22.5;
        int qty = 100;

        Mockito.when(marketData.isCurrentOrderPossible(symbol,
                qty, px)).thenReturn(true);



        Mockito.when(exchange.execute(
                        symbol, qty, px))
                //iterate the return values
                .thenReturn(false, false, true);

        Mockito.when(exchange.execute(
                        symbol, qty, px))
                //iterate the return values
                .thenReturn(false, false, true);

        boolean executionOutcome = tradingApp.executeTradeGuarantee(symbol, qty, px);
        Assertions.assertTrue(executionOutcome);

        Mockito.verify(marketData, atLeast(2)).isCurrentOrderPossible(symbol, qty, px);

        Mockito.verify(auditRepo, times(2))
                .reportTrade(Mockito.startsWith("Exchange"), Mockito.eq(qty), Mockito.eq(px));


        Mockito.verify(auditRepo, times(1))
                .reportTrade(Mockito.endsWith(symbol + " executed."), Mockito.eq(qty), Mockito.eq(px));

        //Mockito.verify(auditRepo).reportTrade(Mockito.contains(symbol), Mockito.eq(qty), Mockito.eq(px));

    }
}
