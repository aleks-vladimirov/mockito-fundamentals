package info.vladimirov.pluralsight.mockito.module3.api;

import info.vladimirov.pluralsight.mockito.module3.model.AccountCurrency;

public interface BankCustomer {
    boolean isRestricted();

    void setDeposit(AccountCurrency accountCurrency, Double deposit);

    Double getAccountBalance(AccountCurrency currency);
}
