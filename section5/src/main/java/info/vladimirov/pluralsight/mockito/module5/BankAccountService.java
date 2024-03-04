package info.vladimirov.pluralsight.mockito.module5;

import info.vladimirov.pluralsight.mockito.module5.model.UserAccount;

import java.math.BigDecimal;

public interface BankAccountService {

    String getBankAccountName(String userName, String password);

    boolean depositMoney(String accountNum, BigDecimal money);


    BigDecimal calculateAllBalance(String accountNum);

    boolean transferMoney(String userName, String password, BigDecimal money, UserAccount recipient);
}
