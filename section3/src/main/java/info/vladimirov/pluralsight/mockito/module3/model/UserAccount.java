package info.vladimirov.pluralsight.mockito.module3.model;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class UserAccount {
    
    private String name;
    
    private String description;
    
    private Map<AccountCurrency, BigDecimal> accountBalance = new HashMap<>();

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

    public Map<AccountCurrency, BigDecimal> getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Map<AccountCurrency, BigDecimal> accountBalance) {
        this.accountBalance = accountBalance;
    }
}
