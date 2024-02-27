package info.vladimirov.pluralsight.mockito.module3.api;

import info.vladimirov.pluralsight.mockito.module3.model.UserAccount;

public interface DbConnector {

    UserAccount getBankAccount(String userName);

    void storeUpdate(Object userAccount);
}
