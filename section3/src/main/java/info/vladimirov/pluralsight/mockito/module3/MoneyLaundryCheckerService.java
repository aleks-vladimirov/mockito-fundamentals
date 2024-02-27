package info.vladimirov.pluralsight.mockito.module3;

import info.vladimirov.pluralsight.mockito.module3.api.BankCustomer;
import info.vladimirov.pluralsight.mockito.module3.api.UserAccountVerifierHandler;

public interface MoneyLaundryCheckerService<T extends BankCustomer> {

     boolean handleValidateAccount(String transactionId, UserAccountVerifierHandler<T> accountVerifierHandler);


}
