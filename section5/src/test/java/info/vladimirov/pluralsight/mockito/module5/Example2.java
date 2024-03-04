package info.vladimirov.pluralsight.mockito.module5;


import info.vladimirov.pluralsight.mockito.module5.model.AccountCurrency;
import info.vladimirov.pluralsight.mockito.module5.model.UserAccount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;

public class Example2 {


    @Mock
    private DbConnector dbConnector;

    @Mock
    private ConversionRateService conversionRateService;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private UserAccount userAccount;

    @Mock
    private BankAuditService auditService;

    @InjectMocks
    private BankAccountService1Impl bankAccountService;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void checkMockInitialization() {
        assertNotNull(bankAccountService);
        assertNotNull(auditService);
        assertNotNull(userAccount);
        assertNotNull(conversionRateService);
        assertNotNull(dbConnector);

    }


    @Test
    void bankAccountServiceTest() {
        String accountName = "James";
        BigDecimal moneyToBeDeposited = new BigDecimal(20.0);
        BigDecimal deposit = new BigDecimal(20.0);

        AccountCurrency accountCurrency = AccountCurrency.USD;


        //Configure mocks
        Mockito.when(dbConnector.getBankAccount(accountName)).thenReturn(userAccount);
        Mockito.when(userAccount.getAccountBalance().get(accountCurrency)).thenReturn(deposit);

        Assertions.assertTrue(bankAccountService.depositMoney(accountName, moneyToBeDeposited));

        Mockito.verify(dbConnector,
                times(1)).getBankAccount(Mockito.anyString());

        Mockito.verify(dbConnector)
                .storeUpdate(Mockito.isNotNull());

    }



    @Test
    void mockitoReset() {
        String accountName = "James";

        //Configure mocks
        Mockito.when(dbConnector.getBankAccount(accountName)).thenReturn(userAccount);
        System.out.println("After stubbing the call, result=" + dbConnector.getBankAccount(accountName));

        Mockito.reset(dbConnector);

        System.out.println("After resetting the mock, result=" +dbConnector.getBankAccount(accountName));

    }

}
