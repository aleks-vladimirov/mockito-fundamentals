package info.vladimirov.pluralsight.mockito.section2;


import info.vladimirov.pluralsight.mockito.section2.api.AuditReport;
import info.vladimirov.pluralsight.mockito.section2.api.Exchange;
import info.vladimirov.pluralsight.mockito.section2.api.MarketData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
class TradingAppTest {

    @Test
    void executeTradeGuaranteeHappyPath() {
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
                Mockito.anyString(), Mockito.anyInt(), Mockito.anyDouble())).thenReturn(true);

        boolean executionOutcome = tradingApp.executeTradeGuarantee(symbol, qty, px);
        Assertions.assertTrue(executionOutcome);

        Mockito.verify(marketData, atLeast(2)).isCurrentOrderPossible(symbol, qty, px);

        Mockito.verify(auditRepo).reportTrade(Mockito.anyString(), Mockito.eq(qty), Mockito.eq(px));

    }


}