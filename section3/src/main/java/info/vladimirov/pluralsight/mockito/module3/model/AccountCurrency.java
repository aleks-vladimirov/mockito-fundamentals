package info.vladimirov.pluralsight.mockito.module3.model;

import java.util.Objects;

public class AccountCurrency {

    private final String currency;
    public AccountCurrency(String currency) {
        this.currency = currency;
    }
    public String getCurrency() {
        return this.currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountCurrency that = (AccountCurrency) o;
        return Objects.equals(currency, that.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currency);
    }

    @Override
    public String toString() {
        return "AccountCurrency{" +
                "currency='" + currency + '\'' +
                '}';
    }
}
