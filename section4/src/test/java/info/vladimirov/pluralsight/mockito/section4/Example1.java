package info.vladimirov.pluralsight.mockito.section4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.intThat;
import static org.mockito.Mockito.*;

/**
 * General Spy behavior demonstration
 */
public class Example1 {



    @Test
    void spyMockDemonstration() {
        List<String> accountList = new ArrayList<>();

        final String account0 = "John Smith";
        final String account2 = "Peter Pan";

        accountList.add(account0);
        List<String> list = spy(accountList);
        String accountIndex0 = list.get(0);

        Mockito.when(list.get(intThat(arg -> arg == 2))).thenReturn(account2);

        String accountIndex2 = list.get(2);
        Mockito.verify(list, times(2)).get(Mockito.anyInt());

        final String account3 = list.get(0);
        Assertions.assertEquals(account0, accountIndex0);
        Assertions.assertEquals(account0, account3);
        Assertions.assertEquals(account2, accountIndex2);
        System.out.println("Account0 = " + account0 +" and account2= " + account2);
    }
















    @Test
    void spyExplicitType() {

        Calculation2 calculation2 = spy();
        Assertions.assertNotNull(calculation2);
        System.out.println(calculation2);

        Calculation2 calculation2_1 = new Calculation2() {
            @Override
            public BigDecimal calculateMarketExpectations(BigDecimal initialPosition, Date currentDate, double marketVariable) {
                return null;
            }
        };

        System.out.println("New anonymous class " + calculation2_1);



    }























    @Test
    @DisplayName("Negative example")
    void spyExplicitTypeFailed() {
        Exception expectedException = null;
        try {
            Class<String> calculation2 = spy();


        } catch (Exception ex) {
            expectedException = ex;
        } finally {
            Assertions.assertNotNull(expectedException);
        }

    }













    @Test
    public void spyOnConstructorArg() {
        Calculation3 calculation3 = spy();


    }



    public class Calculation3 {

        private String name;
        public Calculation3(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
