package info.vladimirov.pluralsight.mockito.section4;

import java.math.BigDecimal;
import java.util.Date;

public abstract class Calculation2 {

    private MarketData marketData = new MarketData() {
    };

    public abstract BigDecimal calculateMarketExpectations(BigDecimal initialPosition,
                                                           Date currentDate, double marketVariable);

    public BigDecimal getAlgo1Calculation(BigDecimal initialPosition, Algo adjustmentAlgo) {
        BigDecimal algoPosition = calculateMarketExpectations(initialPosition, new Date(), 3.1);
        BigDecimal adjustedPosition;
        if(algoPosition.intValue() < 10) {
            adjustedPosition = adjustmentAlgo.getCalculation(algoPosition, marketData);
        } else {
            adjustedPosition = initialPosition;
        }
        return adjustedPosition;
    }

    @Override
    public String toString() {
        return "This is calculation2 implementation";
    }
}
