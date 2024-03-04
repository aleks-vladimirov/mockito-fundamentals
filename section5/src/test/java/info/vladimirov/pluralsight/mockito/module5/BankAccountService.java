package info.vladimirov.pluralsight.mockito.module5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.STRICT_STUBS)
class BankAccountService {



   /* @Test
    void getBankAccount() {

    }
*/
  /*  @Test
    void mockAbstractClassWithArgConstructorSuccess() {
      *//*  Calculation3 calculation = mock(Calculation3.class, withSettings()
                .useConstructor("sample msg").defaultAnswer(CALLS_REAL_METHODS));

        BigDecimal initialPosition = BigDecimal.valueOf(12.3);
        doReturn(initialPosition).when(calculation).calculateMarketExpectations
                (Mockito.eq(initialPosition), Mockito.any(), Mockito.anyDouble());


        BigDecimal result = calculation.getAlgo1Calculation(initialPosition, new SimpleAlgo());
        Assertions.assertEquals(initialPosition.multiply(BigDecimal.valueOf(2)), result);*//*
    }*/

}