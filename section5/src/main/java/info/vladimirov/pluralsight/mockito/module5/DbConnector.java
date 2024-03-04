package info.vladimirov.pluralsight.mockito.module5;

import info.vladimirov.pluralsight.mockito.module5.model.UserAccount;

public interface DbConnector {

    UserAccount getBankAccount(String userName);

    void storeUpdate(UserAccount userAccount);
}
