import info.vladimirov.pluralsight.mockito.section4.Algo;
import info.vladimirov.pluralsight.mockito.section4.Calculation2;
import info.vladimirov.pluralsight.mockito.section4.Calculation3;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Date;

import static org.mockito.Mockito.*;

public class Example4 {


    class SimpleAlgo implements Algo {

        @Override
        public BigDecimal getCalculation(BigDecimal initialInput) {
            int multiplicationFactor = LocalDate.now().getDayOfMonth() % 2 == 0 ? 2 : 1;
            return initialInput.multiply(BigDecimal.valueOf(multiplicationFactor));
        }
    }
    abstract class Calculation {


        abstract BigDecimal calculateMarketExpectations(BigDecimal initialPosition,
                                                        Date currentDate, double marketVariable);

        public BigDecimal getAlgo1Calculation(BigDecimal initialPosition, Algo adjustmentAlgo) {
            BigDecimal algoPosition = calculateMarketExpectations(initialPosition, new Date(), 3.1);
            BigDecimal adjustedPosition = adjustmentAlgo.getCalculation(algoPosition);
            return adjustedPosition;
        }


    }
    @Test
    void spyAbstractClass() {
        Calculation2 calculation = spy(Calculation2.class);
        BigDecimal initialPosition = BigDecimal.valueOf(24.0);
        doReturn(initialPosition).when(calculation).calculateMarketExpectations
                (Mockito.eq(initialPosition), Mockito.any(), Mockito.anyDouble());


        BigDecimal result = calculation.getAlgo1Calculation(initialPosition, new SimpleAlgo());
        Assertions.assertEquals(initialPosition.multiply(BigDecimal.valueOf(2)), result);
    }

    @Test
    void mockAbstractInnerClassFailed() {
        Calculation calculation = spy(Calculation.class);
        BigDecimal initialPosition = BigDecimal.valueOf(24.0);
        doReturn(initialPosition).when(calculation).calculateMarketExpectations
                (Mockito.eq(initialPosition), Mockito.any(), Mockito.anyDouble());


        BigDecimal result = calculation.getAlgo1Calculation(initialPosition, new SimpleAlgo());
        Assertions.assertEquals(initialPosition.multiply(BigDecimal.valueOf(2)), result);
    }

    @Test
    void mockAbstractInnerClassSuccess() {
        Calculation calculation = mock(Calculation.class, withSettings()
                .useConstructor().outerInstance(this).defaultAnswer(CALLS_REAL_METHODS));

        BigDecimal initialPosition = BigDecimal.valueOf(12.3);
        doReturn(initialPosition).when(calculation).calculateMarketExpectations
                (Mockito.eq(initialPosition), Mockito.any(), Mockito.anyDouble());


        BigDecimal result = calculation.getAlgo1Calculation(initialPosition, new SimpleAlgo());
        Assertions.assertEquals(initialPosition.multiply(BigDecimal.valueOf(2)), result);
    }

    @Test
    void mockAbstractClassWithArgConstructorSuccess() {
        Calculation3 calculation = mock(Calculation3.class, withSettings()
                .useConstructor("sample msg").defaultAnswer(CALLS_REAL_METHODS));

        BigDecimal initialPosition = BigDecimal.valueOf(12.3);
        doReturn(initialPosition).when(calculation).calculateMarketExpectations
                (Mockito.eq(initialPosition), Mockito.any(), Mockito.anyDouble());


        BigDecimal result = calculation.getAlgo1Calculation(initialPosition, new SimpleAlgo());
        Assertions.assertEquals(initialPosition.multiply(BigDecimal.valueOf(2)), result);
    }
}
