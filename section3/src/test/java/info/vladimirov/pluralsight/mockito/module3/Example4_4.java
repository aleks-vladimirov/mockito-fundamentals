package info.vladimirov.pluralsight.mockito.module3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;


import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.times;
public class Example4_4 {

    /**
     * Stubbing void method
     */

    @Test
    void throwException() {
        List data = Mockito.mock(List.class);

        Mockito.doThrow(new UnsupportedOperationException("Unsupported operation")).when(data).clear();

        UnsupportedOperationException exception = null;
        try{
        data.clear();
        } catch (UnsupportedOperationException ex) {
            exception = ex;

        }
        Assertions.assertNotNull(exception);

    }

    @Test
    void doAnswer() {
        List data = Mockito.mock(List.class);

        Mockito.doAnswer(invocation -> {System.out.println(Arrays.toString(invocation.getArguments()));
                return null;}).when(data).add(Mockito.eq(1), Mockito.any());

        data.add(1, new Example4_4());

        Mockito.verify(data).add(Mockito.eq(1), Mockito.any());


    }

    @Test
    void voidMethodStub() {
        List list = Mockito.mock(List.class);

        Mockito.doNothing().when(list).clear();

        list.clear();

        Mockito.verify(list, times(1)).clear();

    }
}
