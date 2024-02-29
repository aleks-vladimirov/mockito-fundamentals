package info.vladimirov.pluralsight.mockito.section4;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class Calculation3 {

    private final String traceMsg;
    public Calculation3(String traceMsg) {
        this.traceMsg = traceMsg;
        System.out.println(traceMsg);
    }
    public BigDecimal calculateMarketExpectations(BigDecimal initialPosition,
                                                  LocalDateTime currentDateTime, double marketVariable) {

       return null;


    }

    public BigDecimal getAlgo1Calculation(BigDecimal initialPosition, Algo adjustmentAlgo) {
        BigDecimal algoPosition = calculateMarketExpectations(initialPosition, LocalDateTime.now(), 3.1);
        System.out.println(traceMsg);
        BigDecimal adjustedPosition = adjustmentAlgo.getCalculation(algoPosition, null);
        return adjustedPosition;
    }
}
