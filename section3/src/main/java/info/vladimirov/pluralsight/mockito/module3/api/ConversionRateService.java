package info.vladimirov.pluralsight.mockito.module3.api;

import info.vladimirov.pluralsight.mockito.module3.model.AccountCurrency;


public interface ConversionRateService {

    Double getConvertedRate(AccountCurrency currency, Double amount);
}
