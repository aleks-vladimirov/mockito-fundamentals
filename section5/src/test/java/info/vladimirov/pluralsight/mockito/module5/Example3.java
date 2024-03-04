package info.vladimirov.pluralsight.mockito.module5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mockingDetails;


@MockitoSettings(strictness = Strictness.STRICT_STUBS)
public class Example3 {


    @Spy
    private BankAccountService1 bankAccountServiceSpy;

    @Mock
    private BankAccountService1 bankAccountServiceMock;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void mockDetails() {
        //To identify whether a particular object is a mock or a spy:
        System.out.println("Is it a mock " + mockingDetails(bankAccountServiceSpy).isSpy());
        System.out.println("Is it a spy " + mockingDetails(bankAccountServiceMock).isMock());

        System.out.println("Is it a spy (expected false)" + mockingDetails(bankAccountServiceMock).isSpy());
        System.out.println("Is it a spy (expected true)" + mockingDetails(bankAccountServiceSpy).isMock());

        //Getting details like type to mock or default answer:
        MockingDetails details = Mockito.mockingDetails(bankAccountServiceMock);
        System.out.println("Mock class should implement " +
                details.getMockCreationSettings().getTypeToMock());
        System.out.println("Mock strategy " + details.getMockCreationSettings().getDefaultAnswer());

        //Getting invocations and stubbing of the mock:
        System.out.println("Mock invocation and stubbing");
        System.out.println(details.getInvocations());
        System.out.println(details.getStubbings());

        lenient().when(bankAccountServiceMock.depositMoney(any(), any())).thenReturn(true);
        bankAccountServiceMock.getBankAccountName("John Smith", "pass");

        System.out.println("Mock invocation and stubbing");
        System.out.println(details.getInvocations());
        System.out.println(details.getStubbings());
        System.out.println("End Mock invocation and stubbing");


        //Printing all interactions (including stubbing, unused stubs)
        System.out.println("Print all mock interactions " + details.printInvocations());
    }

















}
