package info.vladimirov.pluralsight.mockito.module3;


import info.vladimirov.pluralsight.mockito.module3.api.*;
import info.vladimirov.pluralsight.mockito.module3.model.BusinessCustomer;
import info.vladimirov.pluralsight.mockito.module3.model.UserAccount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


public class Example4_5 {

    /**
     * Stub with callback
     *
     */
    @Test
    void callbackStub() {
        DbConnector dbConnector = mock();
        ConversionRateService conversionRateService = mock();
        BankAuditService bankAuditService = mock();
        UserAccount accountToTransfer = mock();
        MoneyLaundryCheckerService moneyLService = mock();
        UserAccount userAccount = mock();

        BankAccountService bAccService = new BankAccountServiceImpl(dbConnector, conversionRateService,
                bankAuditService);

        final String accountName = "smith";
        final Double transferredFunds = 10000D;

        //configure mock
        when(dbConnector.getBankAccount(anyString())).thenReturn(userAccount);

        when(moneyLService.handleValidateAccount(any(), any(UserAccountVerifierHandler.class)))
                .thenAnswer(invocation -> {
                    UserAccountVerifierHandler<BankCustomer> verifyHandler = invocation.getArgument(1);
                    boolean outcome = verifyHandler.verify(userAccount);
                    Assertions.assertTrue(outcome);
                    return outcome;
                });

        Assertions.assertFalse(bAccService
                .transferMoney(accountName, transferredFunds, accountToTransfer, moneyLService));


    }



    @Test
    void callbackStubUsingArgumentCaptor() {
        DbConnector dbConnector = mock();
        ConversionRateService conversionRateService = mock();
        BankAuditService bankAuditService = mock();
        UserAccount accountToTransfer = mock();
        MoneyLaundryCheckerService moneyLService = mock();
        UserAccount userAccount = mock();

        BankAccountService bAccService = new BankAccountServiceImpl(dbConnector, conversionRateService,
                bankAuditService);

        final String accountName = "smith";
        final Double transferredFunds = 10000D;

        //configure mock
        when(dbConnector.getBankAccount(anyString())).thenReturn(userAccount);

        when(moneyLService.handleValidateAccount(any(), any(UserAccountVerifierHandler.class))).thenReturn(true);

        Assertions.assertFalse(bAccService
                .transferMoney(accountName, transferredFunds, accountToTransfer, moneyLService));

        ArgumentCaptor<UserAccountVerifierHandler> arg = ArgumentCaptor.forClass(UserAccountVerifierHandler.class);
        verify(moneyLService).handleValidateAccount(Mockito.anyString(), arg.capture());

        Assertions.assertTrue(arg.getValue().verify(userAccount));


    }
}
