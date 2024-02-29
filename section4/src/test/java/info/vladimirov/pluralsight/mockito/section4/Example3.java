package info.vladimirov.pluralsight.mockito.section4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.*;


public class Example3 {
    @Spy
    private List<String> data;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }





    @Test
    void mockingObjectWithAnnotation() {
        Assertions.assertNotNull(data);
    }


}
