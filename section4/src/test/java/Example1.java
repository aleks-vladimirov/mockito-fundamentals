import info.vladimirov.pluralsight.mockito.section4.Algo;
import info.vladimirov.pluralsight.mockito.section4.Calculation2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.intThat;
import static org.mockito.Mockito.*;

/**
 * General Spy behavior demonstration
 */
public class Example1 {


    private final String account0 = "John Smith";

    private final String account2 = "Peter Pan";

    @Test
    void spyMock() {
        List<String> accountList = new ArrayList<>();

        accountList.add(account0);
        List<String> list = spy(accountList);
        String accountIndex0 = list.get(0);

        Mockito.when(list.get(intThat(arg -> arg == 2))).thenReturn(account2);

        String accountIndex2 = list.get(2);
        Mockito.verify(list, atLeast(2)).get(Mockito.anyInt());

        Assertions.assertEquals(account0, accountIndex0);
        Assertions.assertEquals(account2, accountIndex2);
    }


    @Test
    void spyExplicitType() {
        Calculation2 calculation2 = spy();

        Assertions.assertNotNull(calculation2);
        System.out.println(calculation2.toString());
    }

    @Test
    @DisplayName("Negative example")
    void spyExplicitTypeFailed() {
        Class<String> calculation2 = spy();

        Assertions.assertNotNull(calculation2);
        System.out.println(calculation2.toString());
    }
}
