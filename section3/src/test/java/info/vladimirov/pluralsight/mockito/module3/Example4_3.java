package info.vladimirov.pluralsight.mockito.module3;

import info.vladimirov.pluralsight.mockito.module3.api.BankAccountService;
import info.vladimirov.pluralsight.mockito.module3.api.BankAuditService;
import info.vladimirov.pluralsight.mockito.module3.api.ConversionRateService;
import info.vladimirov.pluralsight.mockito.module3.api.DbConnector;
import info.vladimirov.pluralsight.mockito.module3.model.UserAccount;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static org.mockito.Mockito.*;


public class Example4_3 {

    /**
     * Verification timeout
     */
    @Test
    void verificationWithTimeout() throws InterruptedException, ExecutionException {
        DbConnector dbConnector = mock(DbConnector.class);
        ConversionRateService conversionRateService = mock(ConversionRateService.class);
        BankAuditService bankAuditService = mock(BankAuditService.class);

        BankAccountService bAccService = new BankAccountServiceImpl(dbConnector, conversionRateService,
                bankAuditService);

        Map<UserAccount, Double> batchTransfer = new HashMap();
        UserAccount userAcc1 = Mockito.mock(UserAccount.class);
        UserAccount userAcc2 = Mockito.mock(UserAccount.class);

        batchTransfer.put(userAcc1, 100D);
        batchTransfer.put(userAcc2, 1000D);

        //Configure mocks
        Mockito.when(userAcc1.getAccountBalance(Mockito.any())).thenReturn(200D);
        Mockito.when(userAcc2.getAccountBalance(Mockito.any())).thenReturn(2000D);

        //Execute the tested method
        List<Future> outcomes = bAccService.batchTransfer(batchTransfer);

        //Validate
        Mockito.verify(userAcc1,
                Mockito.timeout(200).times(1)).setDeposit(
                Mockito.any(), Mockito.any());

        Mockito.verify(userAcc2,
                after(2000).times(1)).setDeposit(
                Mockito.any(), Mockito.any());

        for(Future outcome : outcomes) {
            System.out.println("Thread execution done, the outcome is " + outcome.get());
        }
    }
}
