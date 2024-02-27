package info.vladimirov.pluralsight.mockito.module3.api;

import info.vladimirov.pluralsight.mockito.module3.MoneyLaundryCheckerService;
import info.vladimirov.pluralsight.mockito.module3.model.AccountCurrency;
import info.vladimirov.pluralsight.mockito.module3.model.UserAccount;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;


public interface BankAccountService {

    UserAccount getBankAccount(String userName, String password);

    boolean depositMoney(String accountNum, Double money, AccountCurrency currency);


    Double calculateAllBalance(String accountNum);

    <T extends BankCustomer> boolean  transferMoney(String userName,
                                                    Double money, T recipient,
                                                    MoneyLaundryCheckerService<T> moneyLaundryChecker);

    List<Future> batchTransfer(Map<UserAccount, Double> transfers);

}
