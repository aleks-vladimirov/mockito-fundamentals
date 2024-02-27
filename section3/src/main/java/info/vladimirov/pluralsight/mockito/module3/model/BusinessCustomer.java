package info.vladimirov.pluralsight.mockito.module3.model;

import java.util.Map;

public class BusinessCustomer extends UserAccount{

    public BusinessCustomer(String name, String address, boolean restricted, String description, Map<AccountCurrency, Double> accountBalance) {
        super(name, address, restricted, description, accountBalance);
    }

    public BusinessCustomer() {
        this(null, null, true, null, null);
    }
}
