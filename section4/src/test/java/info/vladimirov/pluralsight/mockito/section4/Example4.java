package info.vladimirov.pluralsight.mockito.section4;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.math.BigDecimal;
import java.util.stream.Stream;


import static org.mockito.Mockito.*;


public class Example4 {


    @Spy
    private Algo algo;

    @Spy
    private Calculation2 calculation2;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void executeCalculationAdjustedPosition() {
        BigDecimal newCalculationInput = new BigDecimal(3.141592653589793238462643383279502884197);
        BigDecimal finalOutcome = new BigDecimal(1000.203);

        doReturn(newCalculationInput).when(calculation2).calculateMarketExpectations(any(), any(), anyDouble());
        doReturn(finalOutcome).when(algo).getCalculation(Mockito.eq(newCalculationInput), Mockito.any());


        BigDecimal result = calculation2.getAlgo1Calculation(new BigDecimal(100), algo);
        Assertions.assertEquals(finalOutcome, result);

        Mockito.verify(algo).getCalculation(Mockito.eq(newCalculationInput), Mockito.any());
    }

    @Test
    void executeCalculationNewPosition() {
        BigDecimal newCalculationInput = new BigDecimal(13.141592653589793238462643383279502884197);
        BigDecimal initialPosition = new BigDecimal(100);

        doReturn(newCalculationInput).when(calculation2).calculateMarketExpectations(any(), any(), anyDouble());

        BigDecimal result = calculation2.getAlgo1Calculation(initialPosition, algo);
        Assertions.assertEquals(initialPosition, result);

        Mockito.verify(algo, times(0)).getCalculation(Mockito.eq(newCalculationInput), Mockito.any());
    }


    private static Stream<Arguments> calculationParameter() {
        return Stream.of(
                Arguments.of(
                        new BigDecimal(13.14), new BigDecimal(100), 0),
                Arguments.of(new BigDecimal(8.14), new BigDecimal(8.14), 1)
        );
    }
    @ParameterizedTest
    @MethodSource("calculationParameter")
    void executeCalculationParameter(BigDecimal newCalculationInput, BigDecimal initialPosition,
                                     int algoTimesSeen) {

        doReturn(newCalculationInput).when(calculation2).calculateMarketExpectations(any(), any(), anyDouble());
        doReturn(initialPosition).when(algo).getCalculation(Mockito.eq(newCalculationInput), Mockito.any());

        BigDecimal result = calculation2.getAlgo1Calculation(initialPosition, algo);
        Assertions.assertEquals(initialPosition, result);

        Mockito.verify(algo, times(algoTimesSeen)).getCalculation(Mockito.eq(newCalculationInput), Mockito.any());
    }




















}
