import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

/**
 * When object is empty
 */
public class Example3 {

    //This class will fail
    @Test
    @DisplayName("Example 3a: Exception on missing object")
    void exceptionCaseOnSpyingObjectOnMissingValue() {
        List<String> list = Mockito.spy(new ArrayList());

        String expectReturnedValue = "value1";
        //there is no such value, will throw an exception
        Mockito.when(list.get(0)).thenReturn(expectReturnedValue);
        String realValue = list.get(0);
        Assertions.assertEquals(expectReturnedValue, realValue);
    }

    @Test
    @DisplayName("Example 3b: How to overcome missing object when partially mocking")
    void spyingObjectOnMissingValueCorrectly() {
        List<String> list = Mockito.spy(new ArrayList());

        String expectReturnedValue = "value1";

        Mockito.doReturn(expectReturnedValue).when(list).get(0);
        String realValue = list.get(0);
        Assertions.assertEquals(expectReturnedValue, realValue);
    }
}
