package info.vladimirov.pluralsight.mockito.section4;

import java.math.BigDecimal;
import java.util.Date;

public abstract class Calculation3 {

    private final String traceMsg;
    public Calculation3(String traceMsg) {
        this.traceMsg = traceMsg;
    }
    public abstract BigDecimal calculateMarketExpectations(BigDecimal initialPosition,
                                                           Date currentDate, double marketVariable);

    public BigDecimal getAlgo1Calculation(BigDecimal initialPosition, Algo adjustmentAlgo) {
        BigDecimal algoPosition = calculateMarketExpectations(initialPosition, new Date(), 3.1);
        System.out.println(traceMsg);
        BigDecimal adjustedPosition = adjustmentAlgo.getCalculation(algoPosition);
        return adjustedPosition;
    }
}
