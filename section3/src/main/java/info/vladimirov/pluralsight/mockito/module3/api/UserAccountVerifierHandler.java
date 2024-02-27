package info.vladimirov.pluralsight.mockito.module3.api;

public interface UserAccountVerifierHandler<T extends BankCustomer> {

    boolean verify(T userAccount);

}
