package info.vladimirov.pluralsight.mockito.module5.model;

public enum AccountCurrency {

    USD, GBP;

    @Override
    public String toString() {
        return "Account currency implementation for account " + this.name();
    }
}
