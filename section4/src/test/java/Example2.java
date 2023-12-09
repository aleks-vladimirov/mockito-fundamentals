import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

/**
 * Demonstrates the behavior when you try to create a spy from a Mock.
 */
public class Example2 {

    //Expected to throw an exception
    @Test
    void spyingMock() {
        List list = Mockito.mock(List.class);

        Mockito.spy(list);
    }
}
