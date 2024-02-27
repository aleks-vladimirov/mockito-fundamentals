package info.vladimirov.pluralsight.mockito.module3;

import info.vladimirov.pluralsight.mockito.module3.model.BusinessCustomer;
import info.vladimirov.pluralsight.mockito.module3.model.UserAccount;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;

public class Example4_6 {






    /**
     * 	Mockito in order verification
     */
    @Test
    void orderSensitiveVerification() {
        UserAccount userAccountMock = mock();
        BusinessCustomer businessCustomer = mock();

        final String accName = "Smith";
        final String accNameNew = "John";

        userAccountMock.setName(accName);
        businessCustomer.setName(accName);
        userAccountMock.setName(accNameNew);

        InOrder inOrder = Mockito.inOrder(userAccountMock, businessCustomer);

        inOrder.verify(userAccountMock).setName(accName);
        inOrder.verify(businessCustomer).setName(accName);
        inOrder.verify(userAccountMock).setName(accNameNew);
        inOrder.verifyNoMoreInteractions();
    }
}
