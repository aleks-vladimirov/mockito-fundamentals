package info.vladimirov.pluralsight.mockito.module3;


import info.vladimirov.pluralsight.mockito.module3.api.BankAccountService;
import info.vladimirov.pluralsight.mockito.module3.api.BankAuditService;
import info.vladimirov.pluralsight.mockito.module3.api.ConversionRateService;
import info.vladimirov.pluralsight.mockito.module3.api.DbConnector;
import info.vladimirov.pluralsight.mockito.module3.model.AccountCurrency;
import info.vladimirov.pluralsight.mockito.module3.model.UserAccount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;


public class Example4_2 {

    /**
     * Mockito.same vs Mockito.eq
     */
    @Test
    void depositMoneyHappyPath() {
        DbConnector dbConnector = mock(DbConnector.class);
        ConversionRateService conversionRateService = mock(ConversionRateService.class);
        BankAuditService bankAuditService = mock(BankAuditService.class);
        UserAccount userAccount = Mockito.mock(UserAccount.class);

        BankAccountService bAccService = new BankAccountServiceImpl(dbConnector, conversionRateService,
                bankAuditService);

        String accountName = "James";
        Double deposit = 20.0;
        AccountCurrency accountCurrency = new AccountCurrency("USD");

        //Configure mocks
        Mockito.when(dbConnector.getBankAccount(accountName)).thenReturn(userAccount);
        Mockito.when(userAccount.getAccountBalance(accountCurrency)).thenReturn(deposit);

        Assertions.assertTrue(bAccService.depositMoney(accountName, deposit, accountCurrency));

        Mockito.verify(dbConnector,
                times(1)).getBankAccount(Mockito.anyString());

        Mockito.verify(userAccount)
                .setDeposit(Mockito.same(accountCurrency), Mockito.refEq(deposit + deposit));

        Mockito.verify(dbConnector)
                .storeUpdate(Mockito.isNotNull());

    }
}
