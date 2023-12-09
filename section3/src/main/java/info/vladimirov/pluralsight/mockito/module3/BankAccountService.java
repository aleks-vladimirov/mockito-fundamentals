package info.vladimirov.pluralsight.mockito.module3;

import info.vladimirov.pluralsight.mockito.module3.model.AccountCurrency;
import info.vladimirov.pluralsight.mockito.module3.model.UserAccount;

import java.math.BigDecimal;

public interface BankAccountService {

    String getBankAccountName(String userName, String password);

    boolean depositMoney(String accountNum, BigDecimal money, AccountCurrency currency);


    BigDecimal calculateAllBalance(String accountNum);

    boolean transferMoney(String userName, String password, BigDecimal money, UserAccount recipient);
}
