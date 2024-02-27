package info.vladimirov.pluralsight.mockito.module3.model;


import info.vladimirov.pluralsight.mockito.module3.api.BankCustomer;
import lombok.Builder;

import java.util.HashMap;
import java.util.Map;

/**
 * Defined the account information of the bank customer
 */
@Builder
public class UserAccount implements BankCustomer {
    
    private String name;

    private String address;

    private boolean restricted;
    
    private String description;
    
    private Map<AccountCurrency, Double> accountBalance = new HashMap<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<AccountCurrency, Double> getAccountBalance() {
        return accountBalance;
    }

    public Double getAccountBalance(AccountCurrency currency) {
        return accountBalance.get(currency);
    }

    public void setDeposit(AccountCurrency accountCurrency, Double deposit) {
        accountBalance.put(accountCurrency, deposit);
    }

    public void setAccountBalance(Map<AccountCurrency, Double> accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isRestricted() {
        return restricted;
    }

    public void setRestricted(boolean restricted) {
        this.restricted = restricted;
    }

    public UserAccount getSelf() {
        return this;
    }

    public boolean isNameSet() {
        if(name != null) {
            System.out.println("Name was set");
            return true;
        } else {
            System.out.println("Name wasn't set");
            return false;
        }

    }

}
